package dhm.com.dhmshop.view.mine;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.BaseActiity;
import dhm.com.dhmshop.utils.SpUtils;
import dhm.com.dhmshop.view.main.MainActivity;

public class SettingActivity extends BaseActiity {

    private PopupWindow popWindow;


    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @SuppressLint("NewApi")
    @Override
    protected void initView() {
        ButterKnife.bind(this);
        getWindow().setStatusBarColor(Color.LTGRAY);

    }

    @Override
    protected void initData() {
        showPopwindow();
    }

    @OnClick({R.id.back, R.id.address, R.id.safe, R.id.about, R.id.edit_login})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.address:
                intent=new Intent(SettingActivity.this,AddressActivity.class);
                startActivity(intent);
                break;
            case R.id.safe:
                intent=new Intent(SettingActivity.this,SafeActivity.class);
                startActivity(intent);
                break;
            case R.id.about:
                intent=new Intent(SettingActivity.this,AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.edit_login:
                setBackgroundAlpha(0.5f);
                popWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                break;
            default:
        }
    }



    private void showPopwindow() {
        View popView = View.inflate(this, R.layout.dialog_exit, null);
        TextView cancle = popView.findViewById(R.id.cancle);
        TextView sure = popView.findViewById(R.id.sure);

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWindow.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SpUtils.clear(SettingActivity.this);
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                popWindow.dismiss();
                finish();
            }
        });

        popWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popWindow.setFocusable(true);
        popWindow.setTouchable(true);
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        popWindow.setOutsideTouchable(true);// 设置允许在外点击消失



        popWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                // popupWindow隐藏时恢复屏幕正常透明度
                setBackgroundAlpha(1.0f);
            }
        });

    }


    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     *            屏幕透明度0.0-1.0 1表示完全不透明
     */
    public void setBackgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
    }

}
