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

public class SettingActivity extends BaseActiity {
    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @SuppressLint("NewApi")
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        getWindow().setStatusBarColor(Color.LTGRAY);

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.back, R.id.address, R.id.safe, R.id.about, R.id.edit_login})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.address:
                intent=new Intent(SettingActivity.this,AddressActivity.class);
                startActivity(intent);
                break;
            case R.id.safe:
                intent=new Intent(SettingActivity.this,SafeActivity.class);
                startActivity(intent);
                break;
            case R.id.about:
                intent=new Intent(SettingActivity.this,AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.edit_login:
                break;
            default:
        }
    }
}
