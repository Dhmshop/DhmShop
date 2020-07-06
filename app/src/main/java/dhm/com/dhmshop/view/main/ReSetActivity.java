package dhm.com.dhmshop.view.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class ReSetActivity extends BaseActiity implements LoginContract.IView {

    @BindView(R.id.userpwd)
    EditText userpwd;
    @BindView(R.id.repwd)
    EditText repwd;
    @BindView(R.id.login)
    Button login;

    private PressenterImpl pressenter;
    private String uid;

    private boolean ispwd=false;
    private boolean isrepwd=false;
    @Override
    protected int getLayout() {
        return R.layout.activity_re_set;
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

        uid = SpUtils.getString(ReSetActivity.this, "uid");



        userpwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = userpwd.getText().toString();
                if (isrepwd){
                    if (s.isEmpty()||s.equals("")){
                        login.setClickable(false);
                        login.setBackgroundResource(R.drawable.back_registn);
                        ispwd=false;
                    }else {
                        login.setClickable(true);
                        login.setBackgroundResource(R.drawable.back_main);
                        ispwd=true;
                    }
                }else {
                    login.setClickable(false);
                    if (s.isEmpty()||s.equals("")){
                        ispwd=false;
                    }else {
                        ispwd=true;
                    }
                    login.setBackgroundResource(R.drawable.back_registn);
                }
            }
        });
        repwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = repwd.getText().toString();
                if (ispwd){
                    if (s.isEmpty()||s.equals("")){
                        login.setClickable(false);
                        login.setBackgroundResource(R.drawable.back_registn);
                        isrepwd=false;
                    }else {
                        login.setClickable(true);
                        login.setBackgroundResource(R.drawable.back_main);
                        isrepwd=true;
                    }
                }else {
                    login.setClickable(false);
                    if (s.isEmpty()||s.equals("")){
                        isrepwd=false;
                    }else {
                        isrepwd=true;
                    }
                    login.setBackgroundResource(R.drawable.back_registn);
                }
            }
        });



    }

    @OnClick({R.id.back, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.login:
                String pwd = userpwd.getText().toString();
                String respwd = repwd.getText().toString();
                if (pwd.isEmpty()||pwd.equals("")||respwd.isEmpty()||respwd.equals("")){
                    Toast.makeText(this, "请检查数据", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtils.isRegistMobileNO1(pwd)){
                    Toast.makeText(this, "密码格式错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!pwd.equals(respwd)){
                    Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtils.isRegistMobileNO1(pwd)){
                    Toast.makeText(this, "密码格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                Map<String,String> map=new HashMap<>();
                map.put("token", Constant.TOKEN);
                map.put("uid",uid);
                map.put("new_pass",pwd);
                map.put("re_new_pass",respwd);
                pressenter.sendMessage(ReSetActivity.this,Constant.SetNewPass,map, Bean.class);
                break;
            default:
        }
    }

    @Override
    public void requesta(Object data) {
        if (data instanceof Bean){
            Bean bean= (Bean) data;
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            if (bean.getCode()==1){
                Intent intent = new Intent(ReSetActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }

    @Override
    public void fail(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
