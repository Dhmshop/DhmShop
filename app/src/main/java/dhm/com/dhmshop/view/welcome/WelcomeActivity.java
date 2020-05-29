package dhm.com.dhmshop.view.welcome;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.BaseActiity;
import dhm.com.dhmshop.view.main.MainActivity;

public class WelcomeActivity extends BaseActiity {

    @Override
    protected int getLayout() {
//        //隐藏标题栏以及状态栏 tobo 这里一定要注意 这些操作要在setContentView方法前操作
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        /*标题是属于View的 所以窗口所有的修饰部分被隐藏后标题依然有效 需要去掉标题*/
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        //两秒之后自动关闭
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //执行在主线程

                //启动主页面
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                //关闭当前页面
                finish();
            }
        },1000);
    }
}
