package dhm.com.dhmshop.framework.module.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.base.netWork.Constant;
import dhm.com.dhmshop.framework.base.BaseActivity;
import dhm.com.dhmshop.framework.base.MyBaseViewHolder;
import dhm.com.dhmshop.framework.module.home.entity.RecommendShopListEntity;
import dhm.com.dhmshop.framework.module.home.model.RecommendShopListModel;
import dhm.com.dhmshop.framework.module.home.view.RecommendShopListView;
import dhm.com.dhmshop.framework.utils.GlideUtil;
import dhm.com.dhmshop.framework.utils.StringUtil;

public class RecommendShopListActivity extends BaseActivity<RecommendShopListModel> implements RecommendShopListView, View.OnClickListener {


    private RecommendShopListAdapter recommendShopListAdapter;
    private ImageView recommendFan;
    private RecyclerView recommendShopListRecy;
    private ImageView mRecommendFan;

    @Override
    protected void initView() {
        recommendFan = (ImageView) findViewById(R.id.recommend_fan);
        recommendShopListRecy = (RecyclerView) findViewById(R.id.recommend_shop_list);
        recommendShopListAdapter = new RecommendShopListAdapter();
        recommendShopListRecy.setAdapter(recommendShopListAdapter);
        mRecommendFan = (ImageView) findViewById(R.id.recommend_fan);
        mRecommendFan.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        model.getRecommendShopList();
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected int intiLayoutId() {
        return R.layout.activity_recommend_shop_list;
    }

    @Override
    protected RecommendShopListModel initModel() {
        return new RecommendShopListModel();
    }


    @Override
    public void getReCommendShopList(List<RecommendShopListEntity> list) {
        if (list == null || list.size() == 0) {
            return;
        }

        recommendShopListAdapter.addData(list);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.recommend_fan:
                finish();
                break;
        }
    }


    class RecommendShopListAdapter extends BaseQuickAdapter<RecommendShopListEntity, MyBaseViewHolder> {


        public RecommendShopListAdapter() {
            super(R.layout.recommend_shop_list_item);
        }


        @Override
        protected void convert(MyBaseViewHolder helper, RecommendShopListEntity item) {

            GlideUtil.loadCircleImage(RecommendShopListActivity.this, Constant.PATH + item.getImg(), helper.getView(R.id.image_recomshoplist));
            GlideUtil.loadImage(RecommendShopListActivity.this, item.getImg(), helper.getView(R.id.recommendshoplist_ivone));
            GlideUtil.loadImage(RecommendShopListActivity.this, item.getImg(), helper.getView(R.id.recommendshoplist_ivthree));

            helper.setText(R.id.title_recomshoplist, StringUtil.preventNull(item.getStore_name()));
            helper.setText(R.id.desc_recomshoplist, StringUtil.preventNull(item.getStore_name()));
        }
    }
}
