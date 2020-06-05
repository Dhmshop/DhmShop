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
import dhm.com.dhmshop.entity.UserLogin;
import dhm.com.dhmshop.utils.SpUtils;
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

    private PressenterImpl pressenter;


    /**
     * 用户注册类型：3->客户    2->商家
     */
    private String type = "3";
    private Drawable drawable;
    private Drawable drawables;

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        pressenter=new PressenterImpl();
        pressenter.attachView(this);

    }

    private boolean isuser=false;
    private boolean ispwd=false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initData() {
        login.setClickable(false);
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

                if (!ispwd){
                    if (s==null||s.equals("")){
                        login.setBackgroundResource(R.drawable.back_button);
                        Drawable drawable = getResources().getDrawable(R.mipmap.usern);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        username.setCompoundDrawables(drawable,null, null, null);//画在左边
                        login.setBackgroundResource(R.drawable.back_button);
                        isuser=false;
                    }else {
                        login.setBackgroundResource(R.drawable.back_registn);
                        Drawable drawable = getResources().getDrawable(R.mipmap.users);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        username.setCompoundDrawables(drawable,null, null, null);//画在左边
                        login.setBackgroundResource(R.drawable.back_button);
                        isuser=true;
                    }
                }else {
                    if (s==null||s.equals("")){
                        login.setBackgroundResource(R.drawable.back_registn);
                        Drawable drawable = getResources().getDrawable(R.mipmap.usern);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        username.setCompoundDrawables(drawable,null, null, null);//画在左边
                        login.setBackgroundResource(R.drawable.back_button);
                        isuser=false;
                    }else {
                        login.setBackgroundResource(R.drawable.back_btn);
                        Drawable drawable = getResources().getDrawable(R.mipmap.users);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        username.setCompoundDrawables(drawable,null, null, null);//画在左边
                        login.setBackgroundResource(R.drawable.back_button);
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


                if (drawables==null){
                    drawables = getResources().getDrawable(R.mipmap.eyesn);
                    drawables.setBounds(0, 0, drawables.getMinimumWidth(), drawables.getMinimumHeight());
                }
                if (!isuser){
                    login.setClickable(false);
                    if (s==null||s.equals("")){
                        drawable = getResources().getDrawable(R.mipmap.passn);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        userpwd.setCompoundDrawablesWithIntrinsicBounds(drawable,null, drawables, null);//画在左边
                        login.setBackgroundResource(R.drawable.back_button);
                        ispwd=false;
                    }else {
                        drawable = getResources().getDrawable(R.mipmap.passs);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        userpwd.setCompoundDrawablesWithIntrinsicBounds(drawable,null, drawables, null);//画在左边
                        login.setBackgroundResource(R.drawable.back_registn);
                        ispwd=true;
                    }
                }else {
                    if (s==null||s.equals("")){
                        login.setClickable(false);
                        drawable = getResources().getDrawable(R.mipmap.passn);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        userpwd.setCompoundDrawablesWithIntrinsicBounds(drawable,null, drawables, null);//画在左边
                        login.setBackgroundResource(R.drawable.back_registn);
                        ispwd=false;
                    }else {
                        login.setClickable(true);
                        drawable = getResources().getDrawable(R.mipmap.passs);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        userpwd.setCompoundDrawablesWithIntrinsicBounds(drawable,null, drawables, null);//画在左边
                        login.setBackgroundResource(R.drawable.back_main);
                        ispwd=true;
                    }
                }

            }
        });

    }

    private void showOrHide(EditText etPassword){
        //记住光标开始的位置
        int pos = etPassword.getSelectionStart();
        //隐藏密码
        if(etPassword.getInputType()!= (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)){
            drawables = getResources().getDrawable(R.mipmap.eyesn);
            drawables.setBounds(0, 0, drawables.getMinimumWidth(), drawables.getMinimumHeight());
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }else{//显示密码
            drawables = getResources().getDrawable(R.mipmap.eyes);
            drawables.setBounds(0, 0, drawables.getMinimumWidth(), drawables.getMinimumHeight());
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }

        if (drawable==null){
            drawable = getResources().getDrawable(R.mipmap.passn);
        }
        //画在左边
        etPassword.setCompoundDrawablesWithIntrinsicBounds(drawable,null, drawables, null);
        etPassword.setSelection(pos);

    }

    @SuppressLint("ResourceAsColor")
    @OnClick({R.id.user, R.id.customer, R.id.login, R.id.regist, R.id.forget})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.user:
                type="3";
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

                String s = Constant.TOKEN.toString();
                Map<String,String> map=new HashMap<>();
                map.put("token",Constant.TOKEN);
                map.put("user_login",name);
                map.put("user_pass",pwd);
                map.put("user_type",type);
                pressenter.sendMessage(LoginActivity.this,Constant.LOGIN,map, UserLogin.class);
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
        if (data instanceof UserLogin){
            UserLogin userLogin= (UserLogin) data;
            if (userLogin.getCode()==1){
                int uid = userLogin.getData().get(0).getUid();
                SpUtils.saveString(LoginActivity.this,"uid",uid+"");
                if (type.equals("2")){
                    SpUtils.saveString(LoginActivity.this,"shop_id",userLogin.getData().get(0).getShop_id()+"");
                }
                Intent intent=new Intent(LoginActivity.this,MainActivity.class);
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
