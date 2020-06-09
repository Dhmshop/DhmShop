package dhm.com.dhmshop.view.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.BaseActiity;
import dhm.com.dhmshop.utils.StringUtils;

public class ForgetPwdActivity extends BaseActiity {

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.userphone)
    EditText userphone;
    @BindView(R.id.verification)
    EditText verification;
    @BindView(R.id.get_verification)
    TextView getVerification;

    private int djs=60;
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getVerification.setClickable(false);
            djs--;
            if (djs==0){
                getVerification.setText("重新获取验证码");
                getVerification.setClickable(true);
            }else {
                getVerification.setText(djs+"秒");
                handler.sendEmptyMessageDelayed(0,1000);
            }


        }
    };
    @Override
    protected int getLayout() {
        return R.layout.activity_forget_pwd;
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

    @OnClick({R.id.back, R.id.get_verification, R.id.sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.get_verification:
                handler.sendEmptyMessageDelayed(0,1000);
                break;
            case R.id.sure:
                String name = username.getText().toString();
                String phone = userphone.getText().toString();
                String verifications = verification.getText().toString();
                if (name==null||name.equals("")||phone==null||phone.equals("")||verifications==null||verifications.equals("")){
                    Toast.makeText(this, "请检查数据", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (StringUtils.isRegistMobileNO1(name)){
                    Toast.makeText(this, "用户名格式错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtils.isRegistMobileNO(phone)){
                    Toast.makeText(this, "手机号格式错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(ForgetPwdActivity.this, ReSetActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }
}
