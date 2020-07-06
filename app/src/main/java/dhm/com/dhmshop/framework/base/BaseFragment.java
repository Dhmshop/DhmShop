package dhm.com.dhmshop.framework.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;


import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @data 2019/11/28
 * 说明:BaseFragment封装
 */
public abstract class BaseFragment<M extends BaseModel> extends Fragment implements BaseView{
    //设置上下文对象
    public Activity context ;
    //获取P层对象
    public M model;
    /***封装toast对象**/
    private static Toast toast;
    /***获取TAG的activity名称**/
    protected final String TAG = this.getClass().getSimpleName();

    private Unbinder unbinder;
    public View inflate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        model = initModel();
        context = getActivity();
        if (model != null) model.onAttachedView(this,context);
        inflate = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, inflate);

//        if (!EventBus.getDefault().isRegistered(this))
//            EventBus.getDefault().register(this);
        initView(inflate);
        initData();
        initListener();
        return inflate;
    }

    protected abstract void initView(View inflate);

    protected abstract void initData();

    protected abstract void initListener();

    protected abstract M initModel();

    protected abstract int getLayoutId();

    /**
     * 显示长toast
     *
     * @param msg
     */
    public void toastLong(String msg) {
        if (null == toast) {
            toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setText(msg);
            toast.show();
        } else {
            toast.setText(msg);
            toast.show();
        }
    }
    protected <T extends View> T findViewById(@IdRes int id) {
        return inflate.findViewById(id);
    }

    /**
     * 显示短toast
     *
     * @param msg
     */
    public void toastShort(String msg) {
        if (null == toast) {
            toast = new Toast(context);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setText(msg);
            toast.show();
        } else {
            toast.setText(msg);
            toast.show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
        if (model != null){
            model.onDetached();
            model.onUnSubscribe();
        }
//        EventBus.getDefault().unregister(this);

    }

    /**
     * 设置背景透明度
     *
     * @param alpha
     */

    public void setAlpha(float alpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = alpha;
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        getActivity().getWindow().setAttributes(lp);
    }
    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        int height = resources.getDimensionPixelSize(resourceId);
        return height;
    }

}
