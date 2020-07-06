package dhm.com.dhmshop.framework.network;


import android.util.Log;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import dhm.com.dhmshop.base.netWork.Constant;
import dhm.com.dhmshop.framework.base.BaseConstant;
import dhm.com.dhmshop.framework.base.HttpApi;
import dhm.com.dhmshop.framework.base.LenientGsonConverterFactory;
import dhm.com.dhmshop.framework.base.URLConstant;
import dhm.com.dhmshop.view.App;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by  on 2018/3/27.
 * 封装Retrofit配置
 */

public class RetrofitFactory {
    public String TAG = "RetrofitFactory";
    private static final String CACHE_NAME = "com.tortoise.merchant";
    private static String BASE_URL = Constant.PATH;
    private static final int DEFAULT_CONNECT_TIMEOUT = 30;
    private static final int DEFAULT_WRITE_TIMEOUT = 30;
    private static final int DEFAULT_READ_TIMEOUT = 30;
    private Retrofit retrofit;
    private HttpApi httpApi;
    /**
     * 请求失败重连次数
     */
    private int RETRY_COUNT = 0;
    private OkHttpClient.Builder okHttpBuilder;

    //构造方法私有
    private RetrofitFactory() {
        //手动创建一个OkHttpClient并设置超时时间
        okHttpBuilder = new OkHttpClient.Builder();
        /**
         * 设置缓存
         */
        File cacheFile = new File(App.getContext().getExternalCacheDir(), CACHE_NAME);
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (!NetUtil.isNetworkConnected()) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }
                Response response = chain.proceed(request);
                if (!NetUtil.isNetworkConnected()) {
                    int maxAge = 0;
                    // 有网络时 设置缓存超时时间0个小时
                    response.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .removeHeader(CACHE_NAME)// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                            .build();
                } else {
                    // 无网络时，设置超时为4周
                    int maxStale = 60 * 60 * 24 * 28;
                    response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader(CACHE_NAME)
                            .build();
                }
                return response;
            }
        };
        okHttpBuilder.cache(cache).addInterceptor(cacheInterceptor);


        /**
         * 设置头信息
         */
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();
                HttpUrl url=chain.request().url().newBuilder()
                        .addQueryParameter("token", Constant.TOKEN)
                        .addQueryParameter("uid",  BaseConstant.ID)
                        .build();
                Request.Builder requestBuilder = originalRequest.newBuilder()
//                        .addHeader("Accept-Encoding", "gzip")
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json; charset=utf-8")
                        .url(url)
                        .method(originalRequest.method(), originalRequest.body());
                String method = originalRequest.method();
                //接口规则规定了请求方式为 Form 表单，这里只对Post进行处理
                if ("POST".equals(method) && !originalRequest.body().contentType().type().equals("multipart")) {  //POST 请求
                    RequestBody body = originalRequest.body();
                    FormBody.Builder builder = new FormBody.Builder();
                    FormBody formBody = (FormBody) body;

                    int size = formBody.size();
                    //循环将传入的 表单添加到新的表单
                    for (int i = 0; i < size; i++) {
                        String name = formBody.name(i);
                        String value = formBody.value(i);
                        builder.add(name, value);
                    }
                    builder.add("token", Constant.TOKEN);
                    builder.add("uid", BaseConstant.ID);
                    formBody = builder.build();
                    //重新构建 request
                    Request request = requestBuilder
                            .post(formBody)
                            .build();
                    return chain.proceed(request);
                }
                //添加请求参数，此处是以豆瓣api为例，下面会贴出Base_url
//                requestBuilder.addHeader("token", BaseConstant.TOKEN);//添加请求头信息，服务器进行token有效性验证
//                requestBuilder.addHeader("userId", BaseConstant.id);//添加请求头信息，服务器进行ID有效性验证
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };
        okHttpBuilder.addInterceptor(headerInterceptor);


        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> {
            Log.e("HttpLoggingInterceptor",message);
        });
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //设置 Debug Log 模式
        okHttpBuilder.addInterceptor(loggingInterceptor);

         //设置超时和重新连接
        okHttpBuilder.connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.readTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);
        okHttpBuilder.writeTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS);
        //错误重连
        okHttpBuilder.retryOnConnectionFailure(true);


        retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .addConverterFactory(LenientGsonConverterFactory.create(new Gson()))//json转换成JavaBean
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        httpApi = retrofit.create(HttpApi.class);
    }

    //获取单例
    public static RetrofitFactory getInstance() {
        return new RetrofitFactory();
    }

    /**
     * 获取retrofit
     *
     * @return
     */
    public Retrofit getRetrofit() {
        return retrofit;
    }

    public void changeBaseUrl(String baseUrl) {
        retrofit = new Retrofit.Builder()
                .client(okHttpBuilder.build())
                .addConverterFactory(LenientGsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
                .build();
        httpApi = retrofit.create(HttpApi.class);
    }

    /**
     * 获取httpService
     *
     * @return
     */
    public HttpApi getHttpApi() {
        return httpApi;
    }

    /**
     * 设置订阅 和 所在的线程环境
     */
    public <T> void toSubscribe(Observable<T> o, DisposableObserver<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retry(RETRY_COUNT)//请求失败重连次数
                .subscribe(s);
    }
}