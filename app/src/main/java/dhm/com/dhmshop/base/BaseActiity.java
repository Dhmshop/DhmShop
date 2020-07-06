package dhm.com.dhmshop.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import dhm.com.dhmshop.utils.StatusBarUtil;

import static android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;

public abstract class BaseActiity extends AppCompatActivity {
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getLayout();
            setContentView(layoutId);

//        Window window = getWindow();
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//        // 状态栏（以上几行代码必须，参考setStatusBarColor|setNavigationBarColor方法源码）
//        window.setNavigationBarColor(Color.BLACK);
        Window window = getWindow();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        // 状态栏（以上几行代码必须，参考setStatusBarColor|setNavigationBarColor方法源码）
        window.setNavigationBarColor(Color.BLACK);
        initView();
        stateNetWork();
        initData();

    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setNavigationBarColor(Color.parseColor("#000000"));
    }

    protected abstract int getLayout();

    protected abstract void initView();

    protected abstract void initData();

    //动态注册权限
    private void stateNetWork() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] mStatenetwork = new String[]{
                    //写的权限
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    //读的权限
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    //入网权限
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    //WIFI权限
                    Manifest.permission.ACCESS_WIFI_STATE,
                    //读手机权限
                    Manifest.permission.READ_PHONE_STATE,
                    //网络权限
                    Manifest.permission.INTERNET,
                    //相机
                    Manifest.permission.CAMERA,
                    Manifest.permission.WRITE_APN_SETTINGS,
                    Manifest.permission.ACCESS_NETWORK_STATE,
            };
            ActivityCompat.requestPermissions(this, mStatenetwork, 100);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasPermission = false;
        if (requestCode == 100) {
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == -1) {
                    hasPermission = true;
                }
            }
        }
    }

    public void exit() {    //将所有的Activity全部销毁
        finish();
    }
}
