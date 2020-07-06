package dhm.com.dhmshop.framework.base;


import android.content.Context;

public class BaseInfo<T> {
    private int code;
    private T data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess(Context context){
        if (data == null){
            return false;
        }
        if (data instanceof String && data.equals("")){
            return false;
        }
        return true;
    }

}
