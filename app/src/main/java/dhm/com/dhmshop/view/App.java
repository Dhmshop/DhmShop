package dhm.com.dhmshop.view;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends MultiDexApplication {


    /** 主线程ID */
    private static Thread mMainThread;
    /**
     * context 全局唯一的上下文
     */
    private static Context context;
    /** 主线程Handler */
    private static Handler mMainThreadHandler;
    /** 主线程ID */
    private static int mMainThreadId = -1;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        context = this;
        mMainThreadHandler = new Handler();
        mMainThread = Thread.currentThread();
        mMainThreadId = android.os.Process.myTid();
    }

    public static Context getContext() {
        return context;
    }

    /** 获取主线程 */
    public static Thread getMainThread() {
        return mMainThread;
    }
    /** 获取主线程ID */

    /** 获取主线程ID */
    public static int getMainThreadId() {
        return mMainThreadId;
    }
    /** 获取主线程的handler */
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
