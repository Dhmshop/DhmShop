package dhm.com.dhmshop.base.netWork;

import android.content.Context;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class LoginContract {
    public interface MyCallBack{
        void CallBack(Object data);

        void fail(String error);
    }
    public interface Model {
        void sendMessage(Context context, String path, Map<String, String> map, Class clazz, MyCallBack myCallBack);
        void uploadPost(Context context, String url, Map<String, RequestBody> partMap, List<MultipartBody.Part> file, Class clazz, MyCallBack myCallBack);
        void getImage(Context context, String path, MyCallBack myCallBack);
        void requestDataGet(Context context, String path, final Class clazz, final MyCallBack myCallBack);

        void requestPut(Context context, String tobushop, Map<String, String> map, Class clazz, MyCallBack myCallBack);

        void requestDelete(Context context, String quxiao, Map<String, String> map, Class clazz, MyCallBack myCallBack);

        void imagePost(Context context, String url, Map<String, String> map, Class clazz, MyCallBack myCallBack);
    }

    public interface IView {
        void requesta(Object data);

        void fail(String error);
    }


}
