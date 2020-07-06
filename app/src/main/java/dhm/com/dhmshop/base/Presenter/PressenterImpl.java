package dhm.com.dhmshop.base.Presenter;

import android.content.Context;
import android.util.Log;

import java.util.List;
import java.util.Map;

import dhm.com.dhmshop.base.BasePresenter;
import dhm.com.dhmshop.base.Model.ModelImpl;
import dhm.com.dhmshop.base.netWork.LoginContract;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class PressenterImpl extends BasePresenter<LoginContract.IView> {

    private ModelImpl model;

    public PressenterImpl() {
        model=new ModelImpl();
    }


    public void sendMessage(Context context, String path, Map<String, String> map, Class clazz) {
        model.sendMessage(context, path, map, clazz, new LoginContract.MyCallBack() {
                    @Override
                    public void CallBack(Object data) {
                        getView().requesta(data);
                    }

                    @Override
                    public void fail(String error) {
                        getView().fail(error);
                    }
                }
        );
    }


    public void getoneCategory(Context context, String path, Map<String, String> map, Class clazz) {

        model.getoneCategory(context, path, map, clazz, new LoginContract.MyCallBack() {
                    @Override
                    public void CallBack(Object data) {
                        getView().requesta(data);

                    }

                    @Override
                    public void fail(String error) {
                        getView().fail(error);
                    }
                }
        );
    }

    public void sendGet(Context context,String url, Class clazz) {
        model.requestDataGet(context,url, clazz, new LoginContract.MyCallBack() {
            @Override
            public void CallBack(Object data) {
                getView().requesta(data);
            }

            @Override
            public void fail(String error) {
                getView().fail(error);
            }
        });
    }

    public void onPutStartRequest(Context context,String tobushop, Map<String, String> map, Class clazz) {
        model.requestPut(context,tobushop,map,clazz, new LoginContract.MyCallBack() {
            @Override
            public void CallBack(Object data) {
                getView().requesta(data);
            }

            @Override
            public void fail(String error) {
                getView().fail(error);
            }
        });
    }

    public void uploadPost(Context context, String url, Map<String, RequestBody> partMap, List<MultipartBody.Part> file, Class clazz) {
        model.uploadPost(context,url,partMap,file,clazz, new LoginContract.MyCallBack() {
            @Override
            public void CallBack(Object data) {
                getView().requesta(data);
            }

            @Override
            public void fail(String error) {
                getView().fail(error);
            }
        });
    }

    public void sendMessageDelete(Context context,String quxiao, Map<String, String> map, Class clazz) {
        model.requestDelete(context,quxiao,map,clazz, new LoginContract.MyCallBack() {
            @Override
            public void CallBack(Object data) {
                getView().requesta(data);
            }

            @Override
            public void fail(String error) {
                getView().fail(error);
            }
        });
    }


    public void imagePost(Context context, String url, Map<String, String> map, Class clazz) {
        model.imagePost(context,url,map,clazz, new LoginContract.MyCallBack() {
            @Override
            public void CallBack(Object data) {
                getView().requesta(data);
            }

            @Override
            public void fail(String error) {
                getView().fail(error);
            }
        });
    }

    public void destory() {
        if (model!=null){
            model=null;
        }
    }
    /**
     * 检查格式
     *
     * @param name
     * @param password
     */
//    public void checkFormat(String name, String password) {
//        if (TextUtils.isEmpty(name)) {
//            getView().onCheckFormatFail("请输入用户名");
//        } else if (TextUtils.isEmpty(password)) {
//            getView().onCheckFormatFail("请输入密码");
//        } else if (password.length() < 6 || password.length() > 18) {
//            getView().onCheckFormatFail("密码格式不正确");
//        } else {
//            getView().onCheckFormatSuccess();
//            login(name, password);
//        }
//    }
}
