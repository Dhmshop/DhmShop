package dhm.com.dhmshop.framework.base;

import android.app.Activity;

import dhm.com.dhmshop.framework.network.RetrofitFactory;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class BaseModel<V extends BaseView> {

    public Activity context;
    public V mView;
    private CompositeDisposable mCompositeDisposable;

    public void onAttachedView(V v, Activity context) {
        this.context = context;
        this.mView = v;
    }

    /**
     * @describe 避免内存泄漏
     */
    void onDetached() {
        mView = null;
    }

    /**
     * @describe 获取HttpAPi对象(这里创建了Retrofit对象)
     */
    public HttpApi observable() {
        return RetrofitFactory.getInstance().getHttpApi();
    }

    /**
     * @param observable 0
     * @param subscriber 1
     * @describe 发起数据请求
     */
    public <T> void requestData(Observable<BaseInfo<T>> observable, DisposableObserver<BaseInfo<T>> subscriber) {
        addSubscription(subscriber);
        RetrofitFactory.getInstance().toSubscribe(observable, subscriber);
    }

    //RxJava取消注册，以避免内存泄露
    void onUnSubscribe() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
    }


    private <T> void addSubscription(DisposableObserver<BaseInfo<T>> observer) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(observer);
    }

}
