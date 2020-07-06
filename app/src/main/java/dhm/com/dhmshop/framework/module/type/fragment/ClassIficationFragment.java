package dhm.com.dhmshop.framework.module.type.fragment;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.framework.base.BaseFragment;
import dhm.com.dhmshop.framework.module.home.entity.TabCategoryEntity;
import dhm.com.dhmshop.framework.module.home.model.HomeModel;
import dhm.com.dhmshop.framework.module.home.view.HomeView;
import dhm.com.dhmshop.framework.utils.StringUtil;
import io.reactivex.annotations.Nullable;
import q.rorbin.verticaltablayout.VerticalTabLayout;

public class ClassIficationFragment extends BaseFragment<HomeModel> implements HomeView {
    private View view;

    private List<TabCategoryEntity> typeEntities;
    private List<Fragment> fragments;
    private ClassIficationVpFgAdapter classificationVpFgAdapter;

    @Override
    protected void initView(View inflate) {

        LinearLayout typeMainSearch = findViewById(R.id.mainsearch_type);
        VerticalTabLayout typeTab = findViewById(R.id.tab_type);
        ViewPager typeVp = findViewById(R.id.vp_type);
        typeEntities = new ArrayList<>();
        fragments = new ArrayList<>();

        classificationVpFgAdapter = new ClassIficationVpFgAdapter(getChildFragmentManager());
        typeVp.setAdapter(classificationVpFgAdapter);
        typeTab.setupWithViewPager(typeVp);
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
        return R.layout.fragment_classification;
    }


    @Override
    public void getCategorySuccess(List<TabCategoryEntity> result) {
        if (result == null) {
            return;
        }
        typeEntities.addAll(result);
        for (TabCategoryEntity categoryEntity : typeEntities){
                fragments.add(new TypeCategoryChildFragment(categoryEntity.getId()));

        }
        classificationVpFgAdapter.notifyDataSetChanged();
    }


    class ClassIficationVpFgAdapter extends FragmentPagerAdapter {

        public ClassIficationVpFgAdapter(FragmentManager fm) {
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
            return StringUtil.preventNull(typeEntities.get(position).getName());
        }
    }
}
