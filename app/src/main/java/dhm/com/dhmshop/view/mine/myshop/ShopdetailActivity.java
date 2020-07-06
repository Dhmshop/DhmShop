package dhm.com.dhmshop.view.mine.myshop;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dhm.com.dhmshop.R;
import dhm.com.dhmshop.adapter.MainVpFgAdapter;
import dhm.com.dhmshop.base.BaseActiity;
import dhm.com.dhmshop.base.Presenter.PressenterImpl;
import dhm.com.dhmshop.base.netWork.Constant;
import dhm.com.dhmshop.base.netWork.LoginContract;
import dhm.com.dhmshop.entity.Bean;
import dhm.com.dhmshop.entity.GetCollectionShop;
import dhm.com.dhmshop.fragment.wode.shop.ShopFregment;
import dhm.com.dhmshop.utils.SpUtils;

public class ShopdetailActivity extends BaseActiity implements LoginContract.IView {
    @BindView(R.id.image)
    SimpleDraweeView image;
    @BindView(R.id.main_tablayout)
    TabLayout mainTablayout;
    @BindView(R.id.main_vp)
    ViewPager mainVp;
    @BindView(R.id.edit)
    CheckBox edit;
    @BindView(R.id.del)
    CheckBox del;
    @BindView(R.id.collection)
    ImageView collection;
    @BindView(R.id.shoper)
    LinearLayout shoper;
    private ArrayList<String> titles;
    private ArrayList<Fragment> fragments;
    private MainVpFgAdapter mainVpFgAdapter;
    private ShopFregment shopFregment;
    private ShopFregment shopFregment1;
    private ShopFregment shopFregment2;

    private PressenterImpl pressenter;
    private String uid;
    private String pid;
    private boolean collection1=false;


    @Override
    protected int getLayout() {
        return R.layout.activity_shopdetail;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        pid = intent.getStringExtra("pid");
        String type = intent.getStringExtra("type");
        uid = SpUtils.getString(this, "uid");
        pressenter=new PressenterImpl();
        pressenter.attachView(this);
        if (type.equals("2")){
            shoper.setVisibility(View.VISIBLE);
            collection.setVisibility(View.GONE);
        }else {
            shoper.setVisibility(View.GONE);
            collection.setVisibility(View.VISIBLE);
        }

        mainTablayout.setSelectedTabIndicatorHeight(0);
        //创建tab
        titles = new ArrayList<>();
        titles.add("推荐");
        titles.add("全部宝贝");
        titles.add("新品");
        //四个fragments
        fragments = new ArrayList<>();
        shopFregment = new ShopFregment("1");
        shopFregment1 = new ShopFregment("2");
        shopFregment2 = new ShopFregment("3");
        fragments.add(shopFregment);
        fragments.add(shopFregment1);
        fragments.add(shopFregment2);
        //设置适配器
        mainVpFgAdapter = new MainVpFgAdapter(getSupportFragmentManager(), fragments, titles);
        changeTextColor(mainTablayout);
        //绑定适配器
        mainVp.setAdapter(mainVpFgAdapter);
        mainVp.setOffscreenPageLimit(0);
        //设置联动
        mainTablayout.setupWithViewPager(mainVp);

        setupTabIcons();//设置底部TabLayout的item

        Map<String,String> map=new HashMap<>();
        map.put("token",Constant.TOKEN);
        map.put("uid",uid);
        pressenter.sendMessage(this,Constant.MyShopCollects,map, GetCollectionShop.class);

    }

    private int position=0;

    @Override
    protected void initData() {
        mainTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                del.setChecked(false);
                del.setChecked(false);
                del.setChecked(((ShopFregment)fragments.get(position)).isIsdel());
                edit.setChecked(((ShopFregment)fragments.get(position)).isIsedit());
                ((ShopFregment)fragments.get(position)).setdelcheck(del.isChecked());
                ((ShopFregment)fragments.get(position)).seteditcheck(edit.isChecked());
            }




            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        edit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (del.isChecked()){
                    Toast.makeText(ShopdetailActivity.this, "请完成删除功能", Toast.LENGTH_SHORT).show();
                    edit.setChecked(false);
                    return;
                }
                if (edit.isChecked()){
                    edit.setText("完成");
                }else {
                    edit.setText("编辑");
                }
                ((ShopFregment)fragments.get(position)).seteditcheck(edit.isChecked());
            }
        });
        del.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (edit.isChecked()){
                    Toast.makeText(ShopdetailActivity.this, "请完成编辑功能", Toast.LENGTH_SHORT).show();
                    del.setChecked(false);
                    return;
                }
                if (del.isChecked()){
                    del.setText("完成");
                }else {
                    del.setText("删除");
                }
                ((ShopFregment)fragments.get(position)).setdelcheck(del.isChecked());
            }
        });

    }

    @OnClick({R.id.collection, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.collection:
                Toast.makeText(this, "点击收藏", Toast.LENGTH_SHORT).show();
                if (!isCollection){
                    Map<String,String> map=new HashMap<>();
                    map.put("token",Constant.TOKEN);
                    map.put("uid",uid);
                    map.put("shop_id",pid);
                    pressenter.sendMessage(ShopdetailActivity.this,Constant.AddShopCollect,map, Bean.class);
                }else {
                    collection1 = true;
                    Map<String,String> map=new HashMap<>();
                    map.put("token",Constant.TOKEN);
                    map.put("uid",uid);
                    pressenter.sendMessage(this,Constant.MyShopCollects,map, GetCollectionShop.class);
                }

                break;
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

    private boolean isCollection=false;

    @Override
    public void requesta(Object data) {
        if (data instanceof GetCollectionShop){
            GetCollectionShop getCollectionShop= (GetCollectionShop) data;
            if (getCollectionShop.getCode()==1){
                for (int i=0;i<getCollectionShop.getData().size();i++){
                    if (pid.equals(getCollectionShop.getData().get(i).getShop_id()+"")){
                        collection.setImageResource(R.mipmap.detailclick);
                        isCollection=true;
                        if (collection1){
                            Map<String,String> map=new HashMap<>();
                            map.put("token",Constant.TOKEN);
                            map.put("uid",uid);
                            map.put("shop_id",pid);
                            map.put("cid",getCollectionShop.getData().get(i).getId()+"");
                            pressenter.sendMessage(ShopdetailActivity.this,Constant.DelShopCollect,map, Bean.class);
                        }
                    }else {
                        isCollection=false;
                        collection.setImageResource(R.mipmap.detailclickn);
                    }
                }
            }


        }else if (data instanceof Bean){
            Bean bean= (Bean) data;
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            if (bean.getCode()==1){
                if (bean.getMessage().equals("收藏成功")){
                    collection.setImageResource(R.mipmap.detailclick);
                }else {
                    collection.setImageResource(R.mipmap.detailclickn);
                    collection1=false;
                }
            }

        }

    }

    @Override
    public void fail(String error) {

    }
}
