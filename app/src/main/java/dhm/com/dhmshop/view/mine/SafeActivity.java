package dhm.com.dhmshop.view.mine;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.BaseActiity;

public class SafeActivity extends BaseActiity {

    @Override
    protected int getLayout() {
        return R.layout.activity_safe;
    }

    @SuppressLint("NewApi")
    @Override
    protected void initView() {
        getWindow().setStatusBarColor(Color.LTGRAY);

    }

    @Override
    protected void initData() {

    }
}
