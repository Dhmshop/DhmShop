package dhm.com.dhmshop.fromwork.utils;

import android.annotation.SuppressLint;
import android.widget.Toast;

import dhm.com.dhmshop.view.App;


public class ToastUtil {
    private static Toast mToast;

    @SuppressLint("ShowToast")
    public static void show(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(App.getContext(), msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(TextUtil.judgeNull(msg));
        }
        mToast.show();
    }
}
