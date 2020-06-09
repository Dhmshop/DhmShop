package dhm.com.dhmshop.view.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.BaseActiity;
import dhm.com.dhmshop.base.netWork.LoginContract;
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

    private boolean isname;
    private boolean ispwd;
    private boolean isphone;
    private boolean isver;

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



    @Override
    protected int getLayout() {
        return R.layout.activity_regist;
    }

    @SuppressLint("NewApi")
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        getWindow().setStatusBarColor(Color.LTGRAY);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initData() {
        type = getIntent().getStringExtra("type");
        if (type.equals("1")){
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
                if (ispwd||isphone||isver){
                    if (s==null||s.equals("")){
                        regist.setBackgroundResource(R.drawable.back_registn);
                        isname=false;
                    }else {
                        regist.setBackgroundResource(R.drawable.back_btn);
                        isname=true;
                    }
                }else {
                    if (s==null||s.equals("")){
                        isname=false;
                    }else {
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
                if (isname||isphone||isver){
                    if (s==null||s.equals("")){
                        regist.setBackgroundResource(R.drawable.back_registn);
                        ispwd=false;
                    }else {
                        regist.setBackgroundResource(R.drawable.back_btn);
                        ispwd=true;
                    }
                }else {
                    if (s==null||s.equals("")){
                        ispwd=false;
                    }else {
                        ispwd=true;
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
                if (ispwd||isname||isver){
                    if (s==null||s.equals("")){
                        regist.setBackgroundResource(R.drawable.back_registn);
                        isphone=false;
                    }else {
                        regist.setBackgroundResource(R.drawable.back_btn);
                        isphone=true;
                    }
                }else {
                    if (s==null||s.equals("")){
                        isphone=false;
                    }else {
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
                if (ispwd||isname||isphone){
                    if (s==null||s.equals("")){
                        regist.setBackgroundResource(R.drawable.back_registn);
                        isver=false;
                    }else {
                        regist.setBackgroundResource(R.drawable.back_btn);
                        isver=true;
                    }
                }else {
                    if (s==null||s.equals("")){
                        isver=false;
                    }else {
                        isver=true;
                    }
                    regist.setBackgroundResource(R.drawable.back_registn);
                }

            }
        });



    }

    @OnClick({R.id.login, R.id.idCart_one, R.id.idCart_two, R.id.idCart_three, R.id.get_verification, R.id.regist})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.login:
                finish();
                break;
            case R.id.get_verification:
                handler.sendEmptyMessageDelayed(0,1000);
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
                if (StringUtils.isRegistMobileNO(phone)){
                    Toast.makeText(this, "手机号格式错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (StringUtils.isRegistMobileNO1(pwd)){
                    Toast.makeText(this, "密码格式错误", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pwd.equals(respwd)){
                    Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!agree.isChecked()){
                    Toast.makeText(this, "请阅读并同意用户协议", Toast.LENGTH_SHORT).show();
                    return;
                }

                intent = new Intent(RegistActivity.this, MainActivity.class);
                startActivity(intent);

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


    @Override
    public void requesta(Object data) {

    }

    @Override
    public void fail(String error) {

    }
}
