package dhm.com.dhmshop.view.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.http.okhttp.OkHttpUtils;

import dhm.com.dhmshop.entity.Result;
import dhm.com.dhmshop.entity.ResultDetailCallback;
import dhm.com.dhmshop.utils.BitmapUtil;
import okhttp3.Call;

import java.io.File;
import java.io.FileNotFoundException;
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
import dhm.com.dhmshop.utils.StringUtils;

public class RegistActivity extends BaseActiity implements LoginContract.IView {

    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.line_cus)
    LinearLayout lineCus;
    @BindView(R.id.userPwd)
    EditText userPwd;
    @BindView(R.id.rePwd)
    EditText rePwd;
    @BindView(R.id.userPhone)
    EditText userPhone;
    @BindView(R.id.verification)
    EditText verification;
    @BindView(R.id.get_verification)
    TextView getVerification;
    @BindView(R.id.agree)
    CheckBox agree;
    @BindView(R.id.regist)
    Button regist;
    @BindView(R.id.idCart_one)
    SimpleDraweeView idCartOne;
    @BindView(R.id.idCart_two)
    SimpleDraweeView idCartTwo;
    @BindView(R.id.idCart_three)
    SimpleDraweeView idCartThree;
    public static final  int SELECT_PICTURE = 1;
    public static final  int SELECT_PICTUREo = 2;
    public static final  int SELECT_PICTUREt = 3;
    private Uri urione;
    private Uri uritwo;
    private Uri urithree;

    private Drawable drawable;
    private Drawable drawables;
    private boolean isname=false;
    private boolean ispwd=false;
    private boolean isrepwd=false;
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
    private String type;
    private int beans=0;


    @Override
    protected int getLayout() {
        return R.layout.activity_regist;
    }

    @SuppressLint("NewApi")
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        getWindow().setStatusBarColor(Color.LTGRAY);
        pressenter=new PressenterImpl();
        pressenter.attachView(this);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initData() {
        type = getIntent().getStringExtra("type");
        if (type.equals("3")){
            lineCus.setVisibility(View.GONE);
        }else if (type.equals("2")){
            lineCus.setVisibility(View.VISIBLE);
        }else {
            type="1";
            lineCus.setVisibility(View.GONE);
        }

        userPwd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = userPwd.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null){
                    return false;}
                //如果不是按下事件，不再处理
                if (motionEvent.getAction() != MotionEvent.ACTION_UP){
                    return false;}
                if (motionEvent.getX() > userPwd.getWidth() - userPwd.getPaddingRight()- drawable.getIntrinsicWidth()){
                    showOrHide(userPwd);
                }
                return false;
            }
        });

        rePwd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左右上下四张图片
                Drawable drawable = rePwd.getCompoundDrawables()[2];
                //如果右边没有图片，不再处理
                if (drawable == null){
                    return false;}
                //如果不是按下事件，不再处理
                if (motionEvent.getAction() != MotionEvent.ACTION_UP){
                    return false;}
                if (motionEvent.getX() > rePwd.getWidth() - rePwd.getPaddingRight()- drawable.getIntrinsicWidth()){
                    showOrHide(rePwd);
                }
                return false;
            }
        });

        userName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = userName.getText().toString();
                if (ispwd&&isphone&&isver&&isrepwd){
                    if (s==null||s.equals("")){
                        regist.setClickable(false);
                        Drawable drawable = getResources().getDrawable(R.mipmap.usern);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        userName.setCompoundDrawables(drawable,null, null, null);//画在左边
                        regist.setBackgroundResource(R.drawable.back_registn);
                        isname=false;
                    }else {
                        regist.setClickable(true);
                        Drawable drawable = getResources().getDrawable(R.mipmap.users);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        userName.setCompoundDrawables(drawable,null, null, null);//画在左边
                        regist.setBackgroundResource(R.drawable.back_main);
                        isname=true;
                    }
                }else {
                    regist.setClickable(false);
                    if (s==null||s.equals("")){
                        Drawable drawable = getResources().getDrawable(R.mipmap.usern);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        userName.setCompoundDrawables(drawable,null, null, null);//画在左边
                        isname=false;
                    }else {
                        Drawable drawable = getResources().getDrawable(R.mipmap.users);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        userName.setCompoundDrawables(drawable,null, null, null);//画在左边
                        isname=true;
                    }
                    regist.setBackgroundResource(R.drawable.back_registn);
                }

            }
        });
        userPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = userPwd.getText().toString();

                if (drawables==null){
                    drawables = getResources().getDrawable(R.mipmap.eyesn);
                    drawables.setBounds(0, 0, drawables.getMinimumWidth(), drawables.getMinimumHeight());
                }

                if (isname&&isphone&&isver&&isrepwd){
                    if (s==null||s.equals("")){
                        regist.setClickable(false);
                        drawable = getResources().getDrawable(R.mipmap.passn);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        userPwd.setCompoundDrawables(drawable,null, drawables, null);//画在左边
                        regist.setBackgroundResource(R.drawable.back_registn);
                        ispwd=false;
                    }else {
                        regist.setClickable(true);
                        drawable = getResources().getDrawable(R.mipmap.passs);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        userPwd.setCompoundDrawables(drawable,null, drawables, null);//画在左边
                        regist.setBackgroundResource(R.drawable.back_main);
                        ispwd=true;
                    }
                }else {
                    regist.setClickable(false);
                    if (s==null||s.equals("")){
                        drawable = getResources().getDrawable(R.mipmap.passn);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        userPwd.setCompoundDrawables(drawable,null, drawables, null);//画在左边
                        ispwd=false;
                    }else {
                        drawable = getResources().getDrawable(R.mipmap.passs);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        userPwd.setCompoundDrawables(drawable,null, drawables, null);//画在左边
                        ispwd=true;
                    }
                    regist.setBackgroundResource(R.drawable.back_registn);
                }

            }
        });

        rePwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = rePwd.getText().toString();
                if (drawables==null){
                    drawables = getResources().getDrawable(R.mipmap.eyesn);
                    drawables.setBounds(0, 0, drawables.getMinimumWidth(), drawables.getMinimumHeight());
                }
                if (ispwd&&isname&&isver&&isphone){

                    if (s==null||s.equals("")){
                        regist.setClickable(false);
                        drawable = getResources().getDrawable(R.mipmap.passn);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        rePwd.setCompoundDrawables(drawable,null, drawables, null);//画在左边
                        regist.setBackgroundResource(R.drawable.back_registn);
                        isrepwd=false;
                    }else {
                        regist.setClickable(true);
                        drawable = getResources().getDrawable(R.mipmap.passs);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        rePwd.setCompoundDrawables(drawable,null, drawables, null);//画在左边
                        regist.setBackgroundResource(R.drawable.back_main);
                        isrepwd=true;
                    }
                }else {
                    regist.setClickable(false);
                    if (s==null||s.equals("")){
                        drawable = getResources().getDrawable(R.mipmap.passn);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        rePwd.setCompoundDrawables(drawable,null, drawables, null);//画在左边
                        isrepwd=false;
                    }else {
                        drawable = getResources().getDrawable(R.mipmap.passs);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        rePwd.setCompoundDrawables(drawable,null, drawables, null);//画在左边
                        isrepwd=true;
                    }
                    regist.setBackgroundResource(R.drawable.back_registn);
                }
            }
        });

        userPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = userPhone.getText().toString();
                if (ispwd&&isname&&isver&&isrepwd){
                    if (s==null||s.equals("")){
                        regist.setClickable(false);
                        Drawable drawable = getResources().getDrawable(R.mipmap.phonen);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        userPhone.setCompoundDrawables(drawable,null, null, null);//画在左边
                        regist.setBackgroundResource(R.drawable.back_registn);
                        isphone=false;
                    }else {
                        Drawable drawable = getResources().getDrawable(R.mipmap.phones);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        userPhone.setCompoundDrawables(drawable,null, null, null);//画在左边
                        regist.setClickable(true);
                        regist.setBackgroundResource(R.drawable.back_main);
                        isphone=true;
                    }
                }else {
                    regist.setClickable(false);
                    if (s==null||s.equals("")){
                        Drawable drawable = getResources().getDrawable(R.mipmap.phonen);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        userPhone.setCompoundDrawables(drawable,null, null, null);//画在左边
                        isphone=false;
                    }else {
                        Drawable drawable = getResources().getDrawable(R.mipmap.phones);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        userPhone.setCompoundDrawables(drawable,null, null, null);//画在左边
                        isphone=true;
                    }
                    regist.setBackgroundResource(R.drawable.back_registn);
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
                if (ispwd&&isname&&isphone&&isrepwd){
                    if (s==null||s.equals("")){
                        regist.setClickable(false);
                        Drawable drawable = getResources().getDrawable(R.mipmap.vern);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        verification.setCompoundDrawables(drawable,null, null, null);//画在左边
                        regist.setBackgroundResource(R.drawable.back_registn);
                        isver=false;
                    }else {
                        regist.setClickable(true);
                        Drawable drawable = getResources().getDrawable(R.mipmap.vers);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        verification.setCompoundDrawables(drawable,null, null, null);//画在左边
                        regist.setBackgroundResource(R.drawable.back_main);
                        isver=true;
                    }
                }else {
                    regist.setClickable(false);
                    if (s==null||s.equals("")){
                        Drawable drawable = getResources().getDrawable(R.mipmap.vern);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        verification.setCompoundDrawables(drawable,null, null, null);//画在左边
                        isver=false;
                    }else {
                        Drawable drawable = getResources().getDrawable(R.mipmap.vers);
                        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight()); //设置边界
                        verification.setCompoundDrawables(drawable,null, null, null);//画在左边
                        isver=true;
                    }
                    regist.setBackgroundResource(R.drawable.back_registn);
                }

            }
        });



    }

    private void showOrHide(EditText etPassword){
        //记住光标开始的位置
        int pos = etPassword.getSelectionStart();
        if(etPassword.getInputType()!= (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)){//隐藏密码
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
        etPassword.setCompoundDrawables(drawable,null, drawables, null);//画在左边
        etPassword.setSelection(pos);

    }



    @OnClick({R.id.login, R.id.idCart_one, R.id.idCart_two, R.id.idCart_three, R.id.get_verification, R.id.regist})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.login:
                finish();
                break;
            case R.id.get_verification:
                String phones = userPhone.getText().toString();
                if (phones==null||phones.equals("")){
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
                beans = 0;
                pressenter.sendMessage(RegistActivity.this,Constant.SendMessage,maps, Bean.class);
                break;
            case R.id.regist:

                if (type.equals("2")){
                    if (urione==null||urione.equals("")||uritwo==null||uritwo.equals("")||urithree==null||urithree.equals("")){
                        Toast.makeText(this, "请选择图片", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                String name = userName.getText().toString();
                String pwd = userPwd.getText().toString();
                String respwd = rePwd.getText().toString();
                String phone = userPhone.getText().toString();
                String verifications = verification.getText().toString();

                if (name==null||name.equals("")||pwd==null||pwd.equals("")||respwd==null||respwd.equals("")||phone==null||phone.equals("")||verifications==null||verifications.equals("")){
                    Toast.makeText(this, "请检查数据", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (StringUtils.isRegistMobileNO1(name)){
                    Toast.makeText(this, "用户名格式错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtils.isChinaPhoneLegal(phone)){
                    Toast.makeText(this, "手机号格式错误", Toast.LENGTH_SHORT).show();
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

                if (!agree.isChecked()){
                    Toast.makeText(this, "请阅读并同意用户协议", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (type.equals("3")){
                    Map<String,String> map=new HashMap<>();
                    map.put("token",Constant.TOKEN);
                    map.put("user_login",name);
                    map.put("user_pass",pwd);
                    map.put("re_pass",respwd);
                    map.put("mobile",phone);
                    map.put("code",verifications);
                    map.put("type",type);
                    beans = 1;
                    pressenter.sendMessage(RegistActivity.this, Constant.UserRegist,map, Bean.class);
                }else if (type.equals("2")){
                    Map<String, File> files = new HashMap<>();
                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(urione));
                        files.put(bitmapToString(bitmap), new File(bitmapToString(bitmap)));
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uritwo));
                        files.put(bitmapToString(bitmap), new File(bitmapToString(bitmap)));
                        bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(urithree));
                        files.put(bitmapToString(bitmap), new File(bitmapToString(bitmap)));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    OkHttpUtils.post().url(Constant.PATH + Constant.ShopRegist)
                            .addParams("token", Constant.TOKEN)
                            .addParams("user_login", name)
                            .addParams("user_pass", pwd)
                            .addParams("re_pass", respwd)
                            .addParams("mobile", phone)
                            .addParams("code", verifications)
                            .addParams("type", type + "")
                            .files("image[]", files)
                            .build().execute(new ResultDetailCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            Toast.makeText(RegistActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(Result response, int id) {
                            if (response != null && response.getCode()==1) {
                                Toast.makeText(RegistActivity.this, response.getMessage(), Toast.LENGTH_LONG).show();
                                finish();
                            } else if (response.getCode()==-2) {
                                Toast.makeText(RegistActivity.this, "验证码不正确", Toast.LENGTH_SHORT).show();
                            } else if (response.getCode()==-3) {
                                Toast.makeText(RegistActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


                break;
            case R.id.idCart_one:
                if (Build.VERSION.SDK_INT>=23)       {
                    int request= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
                    //缺少权限，进行权限申请
                    if (request!= PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},123);
                        return;
                    }
                    else
                    {
                        //权限同意，不需要处理,去掉用拍照的方法
                        intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent,"选择图片"), SELECT_PICTURE);
                    }
                }
                else{
                    //低于23 不需要特殊处理，去掉用拍照的方法
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent,"选择图片"), SELECT_PICTURE);
                }

                break;
            case R.id.idCart_two:
                if (Build.VERSION.SDK_INT>=23)       {
                    int request= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
                    //缺少权限，进行权限申请
                    if (request!= PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},123);
                        return;
                    }
                    else
                    {
                        //权限同意，不需要处理,去掉用拍照的方法
                        intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent,"选择图片"), SELECT_PICTUREo);
                    }
                }
                else{
                    //低于23 不需要特殊处理，去掉用拍照的方法
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent,"选择图片"), SELECT_PICTUREo);
                }
                break;
            case R.id.idCart_three:
                if (Build.VERSION.SDK_INT>=23)       {
                    int request= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
                    //缺少权限，进行权限申请
                    if (request!= PackageManager.PERMISSION_GRANTED)
                    {
                        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},123);
                        return;
                    }
                    else
                    {
                        //权限同意，不需要处理,去掉用拍照的方法
                        intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent,"选择图片"), SELECT_PICTUREt);
                    }
                }
                else{
                    //低于23 不需要特殊处理，去掉用拍照的方法
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent,"选择图片"), SELECT_PICTUREt);
                }
                break;
            default:
        }
    }

    public String bitmapToString(Bitmap bitmap) {
        //将bitmap转换为uri
        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, null, null));
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor actualimagecursor = managedQuery(uri, proj, null, null, null);
        int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        actualimagecursor.moveToFirst();
        String img_path = actualimagecursor.getString(actual_image_column_index);
        return img_path;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri uri;
        switch (requestCode) {
            case SELECT_PICTURE:
                urione = data.getData();
                idCartOne.setImageURI(urione);
                break;
            case SELECT_PICTUREo:
                uritwo = data.getData();
                idCartTwo.setImageURI(uritwo);
                break;
            case SELECT_PICTUREt:
                urithree = data.getData();
                idCartThree.setImageURI(urithree);
                break;
            default:
        }
    }
    //参数 requestCode是我们在申请权限的时候使用的唯一的申请码
    //String[] permission则是权限列表，一般用不到
    //int[] grantResults 是用户的操作响应，包含这权限是够请求成功
    //由于在权限申请的时候，我们就申请了一个权限，所以此处的数组的长度都是1
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode==123) {
            //当然权限多了，建议使用Switch，不必纠结于此
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                //权限同意，不需要处理,去掉用拍照的方法
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent,"选择图片"), SELECT_PICTURE);
            }else if (grantResults[0]== PackageManager.PERMISSION_DENIED)            {
                Toast.makeText(this, "您拒绝了权限的开启无法选择图片", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public void requesta(Object data) {
        if (data instanceof Bean){
            Bean bean= (Bean) data;
            if (beans == 1){
                if (bean.getCode()==1){
                    Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                } else if (bean.getCode()==-3) {
                    Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                } else if (bean.getCode()==-2) {
                    Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }else {
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
        
    }

    @Override
    public void fail(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }
}
