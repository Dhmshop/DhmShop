package dhm.com.dhmshop.view.mine;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhm.com.dhmshop.R;
import dhm.com.dhmshop.adapter.MainVpFgAdapter;
import dhm.com.dhmshop.base.BaseActiity;
import dhm.com.dhmshop.fragment.wode.CollectionFragment;
import dhm.com.dhmshop.fragment.wode.shop.ShopFregment;

public class CollectionActivity extends BaseActiity {

    @BindView(R.id.edit)
    CheckBox edit;
    @BindView(R.id.del)
    CheckBox del;
    @BindView(R.id.main_tablayout)
    TabLayout mainTablayout;
    @BindView(R.id.main_vp)
    ViewPager mainVp;
    private ArrayList<String> titles;
    private ArrayList<Fragment> fragments;
    private MainVpFgAdapter mainVpFgAdapter;
    private CollectionFragment collectionFragment;
    private CollectionFragment collectionFragment1;


    @Override
    protected int getLayout() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        mainTablayout.setSelectedTabIndicatorHeight(0);
        //创建tab
        titles = new ArrayList<>();
        titles.add("宝贝");
        titles.add("店铺");
        //四个fragments
        fragments = new ArrayList<>();
        collectionFragment = new CollectionFragment("1");
        collectionFragment1 = new CollectionFragment("2");
        fragments.add(collectionFragment);
        fragments.add(collectionFragment1);
        //设置适配器
        mainVpFgAdapter = new MainVpFgAdapter(getSupportFragmentManager(), fragments, titles);
        changeTextColor(mainTablayout);
        //绑定适配器
        mainVp.setAdapter(mainVpFgAdapter);
        mainVp.setOffscreenPageLimit(0);
        //设置联动
        mainTablayout.setupWithViewPager(mainVp);

        setupTabIcons();//设置底部TabLayout的item
    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.shoper, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
                default:
        }
    }


    //有几个底部的item就写几个
    private void setupTabIcons() {
        //tablayout图文效果
        for (int i = 0; i < fragments.size(); i++) {
            mainTablayout.getTabAt(i).setCustomView(getView(i));
        }
    }

    @SuppressLint("NewApi")
    private View getView(int position) {
        View tabitem = LayoutInflater.from(this).inflate(R.layout.tabshop, null);
        TextView b =  tabitem.findViewById(R.id.b);
        TextView tv =  tabitem.findViewById(R.id.tabtv);
        tv.setText(titles.get(position));
        b.setBackgroundResource(R.drawable.selector_shop);
        return tabitem;


    }

    private void changeTextColor(TabLayout tabLayout){
        try {
            //拿到tabLayout的mTabStrip属性
            Field field = TabLayout.class.getDeclaredField("mTabStrip");
            field.setAccessible(true);
            //拿mTabStrip属性里面的值
            Object mTabStrip = field.get(tabLayout);
            //通过mTabStrip对象来获取class类，不能用field来获取class类，参数不能写Integer.class
            Method method = mTabStrip.getClass().getDeclaredMethod("setSelectedIndicatorColor", int.class);
            method.setAccessible(true);
            method.invoke(mTabStrip, Color.parseColor("#ffffff"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
