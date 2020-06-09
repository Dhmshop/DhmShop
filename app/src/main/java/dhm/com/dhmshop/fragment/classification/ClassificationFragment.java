package dhm.com.dhmshop.fragment.classification;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.adapter.classification.ClassificationVpFgAdapter;
import dhm.com.dhmshop.base.BaseFragment;
import q.rorbin.verticaltablayout.VerticalTabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassificationFragment extends BaseFragment implements View.OnClickListener {


    private View view;
    private LinearLayout mMainsearchClassfg;
    private VerticalTabLayout mTabClassificationfg;
    private ViewPager mVpClassificationfg;
    private ArrayList<String> titles;
    private ArrayList<Fragment> fragments;
    private ClassificationChildFragment classificationChildFragment;
    private ClassificationVpFgAdapter classificationVpFgAdapter;

    public ClassificationFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayout() {
        return R.layout.fragment_classification;
    }

    @Override
    protected void initView(View view) {

        mMainsearchClassfg = (LinearLayout) view.findViewById(R.id.mainsearch_classififg);
        mMainsearchClassfg.setOnClickListener(this);
        mTabClassificationfg = (VerticalTabLayout) view.findViewById(R.id.tab_classificationfg);
        mVpClassificationfg = (ViewPager) view.findViewById(R.id.vp_classificationfg);


        titles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            titles.add("推荐");
            titles.add("女装");
            titles.add("男装");
            titles.add("鞋包");
        }
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            classificationChildFragment = new ClassificationChildFragment();
            fragments.add(classificationChildFragment);
        }

        classificationVpFgAdapter = new ClassificationVpFgAdapter(getChildFragmentManager(), titles, fragments);
        mVpClassificationfg.setAdapter(classificationVpFgAdapter);
        mTabClassificationfg.setupWithViewPager(mVpClassificationfg);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.mainsearch_classififg:
                break;
        }
    }


}
