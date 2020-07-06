package dhm.com.dhmshop.fromwork.base;


import android.content.Context;


public class BaseInfo<T> {

    /**
     * ret : 200
     * data : {"user_id":4318,"token":"c0dda436647e3e8c7344a33a03377c1f"}
     * msg :
     */

    private int ret;
    private T data;
    private String msg;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess(Context context){
        if (data == null){
            return false;
        }
        if (data instanceof String && data.equals("")){
            return false;
        }

        if (ret == 401){
            return false;
        }
        if (ret == 200){
            return true;
        }else {
            return false;
        }

    }

}
