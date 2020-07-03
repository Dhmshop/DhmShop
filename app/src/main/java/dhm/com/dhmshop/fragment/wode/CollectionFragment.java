package dhm.com.dhmshop.fragment.wode;

import android.annotation.SuppressLint;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.BaseFragment;

@SuppressLint("ValidFragment")
public class CollectionFragment extends BaseFragment {

    private String type;

    public CollectionFragment(String type) {
        this.type = type;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_collecton;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}
