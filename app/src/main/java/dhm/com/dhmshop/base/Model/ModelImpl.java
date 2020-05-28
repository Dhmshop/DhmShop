package dhm.com.dhmshop.base.Model;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

import dhm.com.dhmshop.base.netWork.LoginContract;
import dhm.com.dhmshop.base.netWork.RetrofitManager;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ModelImpl implements LoginContract.Model {
    @Override
    public void sendMessage(Context context, String path, Map<String, String> map, final Class clazz, final LoginContract.MyCallBack myCallBack) {
        RetrofitManager.getInstance().post(path, map, new RetrofitManager.HttpListener() {
                @Override
                public void onSuccess(String data) {
                    Object o = new Gson().fromJson(data, clazz);
                    myCallBack.CallBack(o);
                }

            @Override
            public void onSuccess(Bitmap data) {

            }

            @Override
                public void onFail(String error) {
                    myCallBack.fail(error);
                }
            });
    }

    @Override
    public void requestDataGet(Context context, String path,final Class clazz, final LoginContract.MyCallBack myCallBack) {
        RetrofitManager.getInstance().get(path, new RetrofitManager.HttpListener() {
                @Override
                public void onSuccess(String data) {
                    Object o = new Gson().fromJson(data, clazz);
                    myCallBack.CallBack(o);
                }

            @Override
            public void onSuccess(Bitmap data) {

            }

            @Override
                public void onFail(String error) {
                    myCallBack.fail(error);
                }
            });
    }
    @Override
    public void getImage(Context context, String path, final LoginContract.MyCallBack myCallBack) {
        RetrofitManager.getInstance().getImage(path, new RetrofitManager.HttpListener() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
                public void onSuccess(Bitmap data) {
                    myCallBack.CallBack(data);
                }
                @Override
                public void onFail(String error) {
                    myCallBack.fail(error);
                }
            });
    }

    @Override
    public void requestPut(Context context, String tobushop, Map<String, String> map, final Class clazz, final LoginContract.MyCallBack myCallBack) {
        RetrofitManager.getInstance().put(tobushop, map, new RetrofitManager.HttpListener() {
                @Override
                public void onSuccess(String data) {
                    Object o = new Gson().fromJson(data, clazz);
                    myCallBack.CallBack(o);
                }

            @Override
            public void onSuccess(Bitmap data) {

            }

            @Override
                public void onFail(String error) {
                    myCallBack.fail(error);
                }
            });
    }

    @Override
    public void requestDelete(Context context, String quxiao, Map<String, String> map, final Class clazz, final LoginContract.MyCallBack myCallBack) {
        RetrofitManager.getInstance().delete(quxiao, map, new RetrofitManager.HttpListener() {
                @Override
                public void onSuccess(String data) {
                    Object o = new Gson().fromJson(data, clazz);
                    myCallBack.CallBack(o);
                }

            @Override
            public void onSuccess(Bitmap data) {

            }

            @Override
                public void onFail(String error) {
                    myCallBack.fail(error);
                }
            });
    }

    @Override
    public void imagePost(Context context, String url, Map<String, String> map, final Class clazz, final LoginContract.MyCallBack myCallBack) {
        RetrofitManager.getInstance().imagePost(url, map, new RetrofitManager.HttpListener() {
                @Override
                public void onSuccess(String data) {
                    Object o = new Gson().fromJson(data, clazz);
                    myCallBack.CallBack(o);
                }

            @Override
            public void onSuccess(Bitmap data) {

            }

            @Override
                public void onFail(String error) {
                    myCallBack.fail(error);
                }
            });
    }


    @Override
    public void uploadPost(Context context, String url, Map<String, RequestBody> partMap, List<MultipartBody.Part> file, final Class clazz, final LoginContract.MyCallBack myCallBack) {
        RetrofitManager.getInstance().uploadPost(url, partMap,file, new RetrofitManager.HttpListener() {
                @Override
                public void onSuccess(String data) {
                    Object o = new Gson().fromJson(data, clazz);
                    myCallBack.CallBack(o);
                }

            @Override
            public void onSuccess(Bitmap data) {

            }

            @Override
                public void onFail(String error) {
                    myCallBack.fail(error);
                }
            });
    }
}
