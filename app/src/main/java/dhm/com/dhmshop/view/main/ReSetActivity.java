package dhm.com.dhmshop.view.main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.BaseActiity;
import dhm.com.dhmshop.utils.StringUtils;

public class ReSetActivity extends BaseActiity {

    @BindView(R.id.userpwd)
    EditText userpwd;
    @BindView(R.id.repwd)
    EditText repwd;

    @Override
    protected int getLayout() {
        return R.layout.activity_re_set;
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

    @OnClick({R.id.back, R.id.login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.login:
                String pwd = userpwd.getText().toString();
                String respwd = repwd.getText().toString();
                if (pwd==null||pwd.equals("")||respwd==null||respwd.equals("")){
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
                Intent intent = new Intent(ReSetActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }
}
