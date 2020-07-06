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

public class AboutActivity extends BaseActiity {

    @Override
    protected int getLayout() {
        return R.layout.activity_about;
    }

    @SuppressLint("NewApi")
    @Override
    protected void initView() {
        getWindow().setStatusBarColor(Color.LTGRAY);
        ButterKnife.bind(this);

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.back, R.id.address, R.id.safe})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.address:
                intent=new Intent(AboutActivity.this,QuanActivity.class);
                intent.putExtra("name","版本信息");
                startActivity(intent);
                break;
            case R.id.safe:
                intent=new Intent(AboutActivity.this,QuanActivity.class);
                intent.putExtra("name","服务协议");
                startActivity(intent);
                break;
            default:
        }
    }
}
