package dhm.com.dhmshop.framework.module.home.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.framework.base.BaseFragment;
import dhm.com.dhmshop.framework.module.home.activity.SearchActivity;
import dhm.com.dhmshop.framework.module.home.entity.TabCategoryEntity;
import dhm.com.dhmshop.framework.module.home.model.HomeModel;
import dhm.com.dhmshop.framework.module.home.view.HomeView;
import dhm.com.dhmshop.framework.utils.StringUtil;


public class HomeFragment extends BaseFragment<HomeModel> implements HomeView, View.OnClickListener {

    private List<Fragment> fragments;
    private List<TabCategoryEntity> tabEntities;
    private CategoryPageAdapter pageAdapter;
    private View view;
    private LinearLayout mMainsearchHomefg;

    @Override
    protected void initView(View inflate) {
        TabLayout homeTab = findViewById(R.id.tab_homefg);
        ViewPager homeVp = findViewById(R.id.vp_homefg);
        mMainsearchHomefg = findViewById(R.id.mainsearch_homefg);
        mMainsearchHomefg.setOnClickListener(this);

        fragments = new ArrayList<>();
        tabEntities = new ArrayList<>();
        pageAdapter = new CategoryPageAdapter(getChildFragmentManager());
        homeVp.setAdapter(pageAdapter);
        homeTab.setupWithViewPager(homeVp);
    }

    @Override
    protected void initData() {
        //调接口发网络请求
        model.getTabCategory();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected HomeModel initModel() {
        return new HomeModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void getCategorySuccess(List<TabCategoryEntity> result) {
        if (result == null) {
            return;
        }
        tabEntities.addAll(result);
        for (TabCategoryEntity categoryEntity : tabEntities) {
            if (categoryEntity.getName().equals("热门")) {
                fragments.add(new HomeHotFragment());
            } else {
                fragments.add(new HomeTabTypeFragment(categoryEntity.getId()));
            }
        }
        pageAdapter.notifyDataSetChanged();
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

    //内部类写viewPage适配器
    class CategoryPageAdapter extends FragmentPagerAdapter {

        public CategoryPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return fragments.get(i);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return StringUtil.preventNull(tabEntities.get(position).getName());
        }
    }
}
