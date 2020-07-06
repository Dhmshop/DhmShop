package dhm.com.dhmshop.view.mine;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.BaseActiity;
import dhm.com.dhmshop.view.main.ReSetActivity;

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
        ButterKnife.bind(this);

    }

    @OnClick({R.id.back, R.id.nackName, R.id.sex, R.id.area})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.nackName:
                break;
            case R.id.sex:
                intent=new Intent(SafeActivity.this, PhoneActivity.class);
                startActivity(intent);
                break;
            case R.id.area:
                intent=new Intent(SafeActivity.this, ReSetActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }
}
