package dhm.com.dhmshop.fragment.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.adapter.home.HomeVpFgAdapter;
import dhm.com.dhmshop.base.BaseFragment;
import dhm.com.dhmshop.view.main.SearchActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {


    private View view;
    private LinearLayout mMainsearchHomefg;
    private TabLayout mTabHomefg;
    private ViewPager mVpHomefg;
    private ArrayList<String> titles;
    private HomeChildFragment homeChildFragment;
    private ArrayList<Fragment> fragments;
    private HomeVpFgAdapter homeVpFgAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        mMainsearchHomefg = (LinearLayout) view.findViewById(R.id.mainsearch_homefg);
        mMainsearchHomefg.setOnClickListener(this);
        mTabHomefg = (TabLayout) view.findViewById(R.id.tab_homefg);
        mVpHomefg = (ViewPager) view.findViewById(R.id.vp_homefg);
        mTabHomefg.setSelectedTabIndicatorHeight(0);

        titles = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            titles.add("热门");
            titles.add("女装");
            titles.add("美妆");
        }
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            homeChildFragment = new HomeChildFragment();
            fragments.add(homeChildFragment);
        }

        homeVpFgAdapter = new HomeVpFgAdapter(getChildFragmentManager(), titles, fragments);
        mVpHomefg.setAdapter(homeVpFgAdapter);
        mTabHomefg.setupWithViewPager(mVpHomefg);


    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.mainsearch_homefg:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
        }
    }


}
