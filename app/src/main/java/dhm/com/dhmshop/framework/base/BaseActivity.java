package dhm.com.dhmshop.framework.base;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;


import com.gyf.immersionbar.ImmersionBar;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dhm.com.dhmshop.R;
import dhm.com.dhmshop.framework.network.NetworkChange;
import dhm.com.dhmshop.framework.utils.NetUtil;
import dhm.com.dhmshop.framework.utils.ToastUtil;

/**
 * @date 2020/4/1
 * @param <M> 网络解析model层对象
 * @pattern mvc
 * @describe 简单的基类封装
 */
public abstract class BaseActivity<M extends BaseModel> extends AppCompatActivity implements BaseView{
    //model层对象
    public M model;
    private Unbinder unbinder;
    /***封装toast对象**/
    public static Toast toast;
    /***获取TAG的activity名称**/
    protected final String TAG = this.getClass().getSimpleName();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(intiLayoutId());
        unbinder = ButterKnife.bind(this);
        this.registerReceiver();
        model = initModel();
        if (model != null){
            model.onAttachedView(this,this);
        }
//        if (!EventBus.getDefault().isRegistered(this))
//            EventBus.getDefault().register(this);
        ImmersionBar.with(this)
                .navigationBarColor(R.color.white)
                .navigationBarAlpha(0.4f)//导航栏透明度，不写默认0.0F
                .keyboardEnable(true)////解决软键盘与底部输入框冲突问题
                .init();
        //初始化控件
        initView();
        if (NetUtil.getAPNType(this) ==  NetUtil.netType.noneNet){
            ToastUtil.show("当前没有网络，请检查网络状态");
        }else{
            //设置数据
            initData();
        }
        //设置监听
        initListener();
    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract int intiLayoutId();

    protected abstract M initModel();


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (view instanceof EditText) {
                Rect r = new Rect();
                view.getGlobalVisibleRect(r);
                int rawX = (int)ev.getRawX();
                int rawY = (int)ev.getRawY();
                if (!r.contains(rawX, rawY)) {
                    view.clearFocus();
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
        if (model != null) {
            model.onDetached();
            model.onUnSubscribe();
        }
//        EventBus.getDefault().unregister(this);
        this.unregisterReceiver();
    }
    private NetworkChange.OnNetWorkChange home_onChange = (wifi, mobile, none, oldStatus, newStatus) -> {
        if (newStatus == none){
            //没有网络
            ToastUtil.show("当前没有网络，请检查网络状态");
        }
        if (newStatus == mobile){
            //移动网络
        }
        if (newStatus == wifi){
            //wifi网络
            if (oldStatus == mobile) {
                //从移动网络切换到wifi网络
            }
        }
    };

    //注册监听网络变化
    private void registerReceiver() {
        NetworkChange.getInstance().setOnNetWorkChange(home_onChange);
    }

    //取消监听网络变化
    private void unregisterReceiver() {
        NetworkChange.getInstance().delOnNetWorkChange(home_onChange);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

   /* *//**
     * @param isDark //状态栏字体是颜色，true 为亮色
     *//*
    public void setStatusBarDarkFont(boolean isDark){
        ImmersionBar.with(this).statusBarDarkFont(isDark).init();
    }

    *//**
     * @param statusBarColor //修改状态栏颜色
     *//*
    public void setStatusBarColor(@ColorRes int statusBarColor){
        ImmersionBar.with(this).statusBarColor(statusBarColor).init();
    }
*/
    /**
     * 设置背景透明度
     * @param alpha
     */

    public void setAlpha(float alpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = alpha;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getWindow().setAttributes(lp);
    }

    /**
     * 显示键盘
     *
     * @param et 输入焦点
     */
    public void showInput(final EditText et) {
        et.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.showSoftInput(et, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 隐藏键盘
     */
    public void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            assert imm != null;
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }
}
