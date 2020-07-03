package dhm.com.dhmshop.view.mine;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.BaseActiity;
import dhm.com.dhmshop.base.Presenter.PressenterImpl;
import dhm.com.dhmshop.base.netWork.Constant;
import dhm.com.dhmshop.base.netWork.LoginContract;
import dhm.com.dhmshop.entity.Bean;
import dhm.com.dhmshop.utils.SpUtils;
import dhm.com.dhmshop.utils.StringUtils;
import dhm.com.dhmshop.view.main.ForgetPwdActivity;

public class PhoneActivity extends BaseActiity implements LoginContract.IView {

    @BindView(R.id.username)
    EditText userphone;
    @BindView(R.id.verification)
    EditText verification;
    @BindView(R.id.get_verification)
    TextView getVerification;
    @BindView(R.id.sure)
    Button sure;

    private boolean isphone=false;
    private boolean isver=false;
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

    private PressenterImpl pressenter;
    private String uid;

    @Override
    protected int getLayout() {
        return R.layout.activity_phone;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        uid = SpUtils.getString(this, "uid");

        pressenter=new PressenterImpl();
        pressenter.attachView(this);
    }

    @Override
    protected void initData() {
        userphone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = userphone.getText().toString();
                if (isver){
                    if (s.isEmpty()||s.equals("")){
                        sure.setClickable(false);
                        sure.setBackgroundResource(R.drawable.back_registn);
                        isphone=false;
                    }else {
                        sure.setClickable(true);
                        sure.setBackgroundResource(R.drawable.back_main);
                        isphone=true;
                    }
                }else {
                    sure.setClickable(false);
                    if (s.isEmpty()||s.equals("")){
                        isphone=false;
                    }else {
                        isphone=true;
                    }
                    sure.setBackgroundResource(R.drawable.back_registn);
                }

            }
        });

        verification.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = verification.getText().toString();
                if (isphone){
                    if (s.isEmpty()||s.equals("")){
                        sure.setClickable(false);
                        sure.setBackgroundResource(R.drawable.back_registn);
                        isver=false;
                    }else {
                        sure.setClickable(true);
                        sure.setBackgroundResource(R.drawable.back_main);
                        isver=true;
                    }
                }else {
                    sure.setClickable(false);
                    if (s.isEmpty()||s.equals("")){
                        isver=false;
                    }else {
                        isver=true;
                    }
                    sure.setBackgroundResource(R.drawable.back_registn);
                }
            }
        });


    }

    @OnClick({R.id.get_verification, R.id.sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.get_verification:
                String phones = userphone.getText().toString();
                if (phones.isEmpty()||phones.equals("")){
                    Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtils.isRegistMobileNO(phones)){
                    Toast.makeText(this, "手机号格式错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                //提交服务器
                Map<String,String> maps=new HashMap<>();
                maps.put("mobile",phones);
                maps.put("uid",uid);
                maps.put("token", Constant.TOKEN);
                pressenter.sendMessage(PhoneActivity.this,Constant.SendMessage,maps, Bean.class);
                break;
            case R.id.sure:
                String phone = userphone.getText().toString();
                String verifications = verification.getText().toString();
                if (phone.isEmpty()||phone.equals("")||verifications.isEmpty()||verifications.equals("")){
                    Toast.makeText(this, "请检查数据", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtils.isRegistMobileNO(phone)){
                    Toast.makeText(this, "手机号格式错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String,String> map=new HashMap<>();
                map.put("token",Constant.TOKEN);
                map.put("mobile",phone);
                map.put("code",verifications);
                pressenter.sendMessage(PhoneActivity.this, Constant.ChangePhone,map, Bean.class);
                break;
                default:
        }
    }

    @Override
    public void requesta(Object data) {

        if (data instanceof Bean){
            Bean bean= (Bean) data;
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            if (bean.getMessage().equals("短信发送成功,请注意查收")){
                djs=60;
                handler.sendEmptyMessageDelayed(0,1000);
            }else if (bean.getMessage().equals("修改成功")){
                finish();
            }
        }

    }

    @Override
    public void fail(String error) {

    }
}
