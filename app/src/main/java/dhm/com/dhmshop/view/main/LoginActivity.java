package dhm.com.dhmshop.view.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.BaseActiity;
import dhm.com.dhmshop.base.netWork.LoginContract;
import dhm.com.dhmshop.utils.StringUtils;

public class LoginActivity extends BaseActiity implements LoginContract.IView {

    @BindView(R.id.simple)
    SimpleDraweeView simple;
    @BindView(R.id.user)
    LinearLayout user;
    @BindView(R.id.customer)
    LinearLayout customer;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.userpwd)
    EditText userpwd;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.text_user)
    TextView textUser;
    @BindView(R.id.line_user)
    TextView lineUser;
    @BindView(R.id.text_cus)
    TextView textCus;
    @BindView(R.id.line_cus)
    TextView lineCus;


    /**
     * 用户注册类型：1->客户    2->商家
     */
    private String type = "1";

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);


    }

    private boolean isuser=false;
    private boolean ispwd=false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initData() {

        userpwd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = userpwd.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null){
                    return false;}
                //如果不是按下事件，不再处理
                if (motionEvent.getAction() != MotionEvent.ACTION_UP){
                    return false;}
                if (motionEvent.getX() > userpwd.getWidth() - userpwd.getPaddingRight()- drawable.getIntrinsicWidth()){
                    showOrHide(userpwd);
                }
                return false;
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

                if (ispwd){
                    if (s==null||s.equals("")){
                        login.setBackgroundResource(R.drawable.back_button);
                        isuser=false;
                    }else {
                        login.setBackgroundResource(R.drawable.back_registn);
                        isuser=true;
                    }
                }else {
                    if (s==null||s.equals("")){
                        login.setBackgroundResource(R.drawable.back_registn);
                        isuser=false;
                    }else {
                        login.setBackgroundResource(R.drawable.back_btn);
                        isuser=true;
                    }
                }


            }
        });

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

                if (!isuser){
                    if (s==null||s.equals("")){
                        login.setBackgroundResource(R.drawable.back_button);
                        ispwd=false;
                    }else {
                        login.setBackgroundResource(R.drawable.back_registn);
                        ispwd=true;
                    }
                }else {
                    if (s==null||s.equals("")){
                        login.setBackgroundResource(R.drawable.back_registn);
                        ispwd=false;
                    }else {
                        login.setBackgroundResource(R.drawable.back_btn);
                        ispwd=true;
                    }
                }

            }
        });

    }

    private void showOrHide(EditText etPassword){
        //记住光标开始的位置
        int pos = etPassword.getSelectionStart();
        if(etPassword.getInputType()!= (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)){//隐藏密码
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }else{//显示密码
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }
        etPassword.setSelection(pos);

    }

    @SuppressLint("ResourceAsColor")
    @OnClick({R.id.user, R.id.customer, R.id.login, R.id.regist, R.id.forget})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.user:
                type="1";
                userpwd.getText().clear();
                username.getText().clear();
                textCus.setTextColor(getResources().getColor(R.color.mainblack));
                textUser.setTextColor(getResources().getColor(R.color.main));
                lineCus.setBackgroundColor(getResources().getColor(R.color.white));
                lineUser.setBackgroundColor(getResources().getColor(R.color.main));
                break;
            case R.id.customer:
                type="2";
                userpwd.getText().clear();
                username.getText().clear();
                textCus.setTextColor(getResources().getColor(R.color.main));
                textUser.setTextColor(getResources().getColor(R.color.mainblack));
                lineCus.setBackgroundColor(getResources().getColor(R.color.main));
                lineUser.setBackgroundColor(getResources().getColor(R.color.white));
                break;
            case R.id.login:
                String name = username.getText().toString();
                String pwd = userpwd.getText().toString();
                if (name==null||name.equals("")||pwd==null||pwd.equals("")){
                    Toast.makeText(this, "请检查数据", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtils.isRegistMobileNO1(pwd)){
                    Toast.makeText(this, "密码格式不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.regist:
                intent = new Intent(LoginActivity.this, RegistActivity.class);
                intent.putExtra("type",type);
                startActivity(intent);
                break;
            case R.id.forget:
                intent = new Intent(LoginActivity.this, ForgetPwdActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }

    @Override
    public void requesta(Object data) {


    }

    @Override
    public void fail(String error) {

    }
}
