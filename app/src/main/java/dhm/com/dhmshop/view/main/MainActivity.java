package dhm.com.dhmshop.view.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


import dhm.com.dhmshop.R;
import dhm.com.dhmshop.adapter.MainVpFgAdapter;
import dhm.com.dhmshop.base.BaseActiity;
import dhm.com.dhmshop.fragment.classification.ClassificationFragment;
import dhm.com.dhmshop.fragment.home.HomeFragment;
import dhm.com.dhmshop.fragment.shoppingcart.ShoppingcartFragment;
import dhm.com.dhmshop.fragment.wode.WodeFragment;

public class MainActivity extends BaseActiity implements View.OnClickListener {

    private LinearLayout mMainSearch;
    private ViewPager mMainVp;
    private TabLayout mMainTablayout;
    private LinearLayout mMainContainer;
    private ArrayList<String> titles;
    private ArrayList<Fragment> fragments;
    private HomeFragment homeFragment;
    private ClassificationFragment classificationFragment;
    private ShoppingcartFragment shoppingcartFragment;
    private WodeFragment wodeFragment;
    private MainVpFgAdapter mainVpFgAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mMainSearch = (LinearLayout) findViewById(R.id.main_search);
        mMainVp = (ViewPager) findViewById(R.id.main_vp);
        mMainTablayout = (TabLayout) findViewById(R.id.main_tablayout);
        mMainContainer = (LinearLayout) findViewById(R.id.main_container);
        mMainSearch.setOnClickListener(this);
        mMainVp.setOnClickListener(this);
        mMainTablayout.setOnClickListener(this);

        mMainTablayout.setSelectedTabIndicatorHeight(0);
        //创建tab
        titles = new ArrayList<>();
        titles.add("首页");
        titles.add("分类");
        titles.add("购物车");
        titles.add("我的");

        //四个fragments
        fragments = new ArrayList<>();
        homeFragment = new HomeFragment();
        classificationFragment = new ClassificationFragment();
        shoppingcartFragment = new ShoppingcartFragment();
        wodeFragment = new WodeFragment();
        fragments.add(homeFragment);
        fragments.add(classificationFragment);
        fragments.add(shoppingcartFragment);
        fragments.add(wodeFragment);

        //设置适配器
        mainVpFgAdapter = new MainVpFgAdapter(getSupportFragmentManager(), fragments, titles);

        //绑定适配器
        mMainVp.setAdapter(mainVpFgAdapter);
        //设置联动
        mMainTablayout.setupWithViewPager(mMainVp);

        setupTabIcons();//设置底部TabLayout的item





    }

    @Override
    protected void initData() {

    }

    //有几个底部的item就写几个
     private void setupTabIcons() {
         //tablayout图文效果
         for (int i = 0; i < fragments.size(); i++) {
             mMainTablayout.getTabAt(i).setCustomView(getView(i));
         }
    }

    private View getView(int position) {
        View tabitem = LayoutInflater.from(this).inflate(R.layout.tabitem, null);
        ImageView iv = (ImageView) tabitem.findViewById(R.id.tabiv);
        TextView tv = (TextView) tabitem.findViewById(R.id.tabtv);
        tv.setText(titles.get(position));

       /* if (position == 0) {
            nav_title.setTextColor(getResources().getColor(R.color.colorPrimary));
            nav_icon.setTextColor(getResources().getColor(R.color.colorPrimary));
        } else {
            nav_title.setTextColor(getResources().getColor(R.color.gray_navigation_bar));
            nav_icon.setTextColor(getResources().getColor(R.color.gray_navigation_bar));
        }*/


        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.selector_show);
        images.add(R.drawable.selector_classification);
        images.add(R.drawable.selector_shoppingcart);
        images.add(R.drawable.selector_wode);
        iv.setImageResource(images.get(position));
        return tabitem;


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.main_search:
                break;
            case R.id.main_vp:
                break;
            case R.id.main_tablayout:
                break;
        }
    }
}
