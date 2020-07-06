package dhm.com.dhmshop.fromwork.utils;

import android.util.Log;

import dhm.com.dhmshop.view.App;


/**
 * @describe 日志工具类
 * @date 2020/5/8
 * @author wjw
 */
public class LogUtil {
    private static boolean isOpenLog = true;
    private static String TAG = App.class.getSimpleName();
    public static void errorMsg(String message){
        if (isOpenLog){
            Log.e(TAG,StringUtil.preventNull(message));
        }
    }
    public static void errorMsg(Object...str){
        if (isOpenLog){
            Log.e(TAG,StringUtil.buildString(str));
        }
    }
    public static void debugMsg(String message){
        if (isOpenLog){
            Log.d(TAG,StringUtil.preventNull(message));
        }
    }
    public static void infoMsg(String message){
        if (isOpenLog){
            Log.i(TAG,StringUtil.preventNull(message));
        }
    }
}
