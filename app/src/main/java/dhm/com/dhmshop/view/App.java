package dhm.com.dhmshop.view;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.Objects;

import dhm.com.dhmshop.framework.base.BaseConstant;
import dhm.com.dhmshop.utils.SpUtils;

public class App extends Application {
    public static App context;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        context = this;
        BaseConstant.ID = "1";//SpUtils.getString(this, "uid");
    }

    public static App getContext() {
        return context;
    }
}
