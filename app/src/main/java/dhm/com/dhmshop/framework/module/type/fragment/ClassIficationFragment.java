package dhm.com.dhmshop.framework.module.home.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.framework.base.BaseFragment;
import dhm.com.dhmshop.framework.module.home.model.ClassIficationModel;
import dhm.com.dhmshop.framework.module.home.view.ClassIficationView;
import q.rorbin.verticaltablayout.VerticalTabLayout;

public class ClassIficationFragment extends BaseFragment<ClassIficationModel> implements ClassIficationView {
    private View view;
    private LinearLayout mMainsearchClassififg;
    private VerticalTabLayout mTabClassificationfg;
    private ViewPager mVpClassificationfg;

    @Override
    protected void initView(View inflate) {

        mMainsearchClassififg = findViewById(R.id.mainsearch_classififg);
        mTabClassificationfg = findViewById(R.id.tab_classificationfg);
        mVpClassificationfg = findViewById(R.id.vp_classificationfg);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected ClassIficationModel initModel() {
        return new ClassIficationModel();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_classification;
    }


}
