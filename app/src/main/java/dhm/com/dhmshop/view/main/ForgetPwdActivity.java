package dhm.com.dhmshop.view.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
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
import dhm.com.dhmshop.entity.ReSetPwd;
import dhm.com.dhmshop.utils.SpUtils;
import dhm.com.dhmshop.utils.StringUtils;

public class ForgetPwdActivity extends BaseActiity implements LoginContract.IView {

    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.userphone)
    EditText userphone;
    @BindView(R.id.verification)
    EditText verification;
    @BindView(R.id.get_verification)
    TextView getVerification;
    @BindView(R.id.sure)
    Button sure;

    private boolean isname=false;
    private boolean isphone=false;
    private boolean isver=false;
    private PressenterImpl pressenter;
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
                if (isname&&isver){
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

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = username.getText().toString();
                if (isphone&&isver){
                    if (s.isEmpty()||s.equals("")){
                        sure.setClickable(false);
                        sure.setBackgroundResource(R.drawable.back_registn);
                        isname=false;
                    }else {
                        sure.setClickable(true);
                        sure.setBackgroundResource(R.drawable.back_main);
                        isname=true;
                    }
                }else {
                    sure.setClickable(false);
                    if (s.isEmpty()||s.equals("")){
                        isname=false;
                    }else {
                        isname=true;
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
                if (isphone&&isname){
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

    @OnClick({R.id.back, R.id.get_verification, R.id.sure})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
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
                maps.put("token",Constant.TOKEN);
                pressenter.sendMessage(ForgetPwdActivity.this,Constant.SendMessage,maps, Bean.class);
                break;
            case R.id.sure:
                String name = username.getText().toString();
                String phone = userphone.getText().toString();
                String verifications = verification.getText().toString();
                if (name.isEmpty()||name.equals("")||phone.isEmpty()||phone.equals("")||verifications.isEmpty()||verifications.equals("")){
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
                Map<String,String> map=new HashMap<>();
                map.put("token",Constant.TOKEN);
                map.put("user_login",name);
                map.put("mobile",phone);
                map.put("code",verifications);
                pressenter.sendMessage(ForgetPwdActivity.this, Constant.CheckUser,map, ReSetPwd.class);
                break;
            default:
        }
    }

    @Override
    public void requesta(Object data) {
        if (data instanceof ReSetPwd){
            ReSetPwd bean= (ReSetPwd) data;
            if (bean.getCode()==1){
                SpUtils.saveString(ForgetPwdActivity.this,"uid",bean.getData().get(0).getUid()+"");
                Intent intent = new Intent(ForgetPwdActivity.this, ReSetActivity.class);
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }else if (data instanceof Bean){
            Bean bean= (Bean) data;
            if (bean.getCode()==1){
                handler.sendEmptyMessageDelayed(0,1000);
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            } else if (bean.getCode()==-3) {
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            } else if (bean.getCode()==-2) {
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void fail(String error) {
//        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
