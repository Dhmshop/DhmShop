package dhm.com.dhmshop.framework.network;

/**
 * Created by wjw on 2018/3/27.
 */
public interface OnSuccessAndFaultListener<T> {
    void onSuccess(T result);

    void onFault(String errorMsg);
}
