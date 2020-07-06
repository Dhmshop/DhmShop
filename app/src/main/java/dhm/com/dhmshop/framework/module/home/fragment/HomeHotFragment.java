package dhm.com.dhmshop.framework.module.home.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;


import com.chad.library.adapter.base.BaseQuickAdapter;
import com.youth.banner.Banner;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import dhm.com.dhmshop.R;
import dhm.com.dhmshop.framework.base.BaseFragment;
import dhm.com.dhmshop.framework.base.MyBaseViewHolder;
import dhm.com.dhmshop.framework.utils.GlideUtil;
import dhm.com.dhmshop.framework.utils.StringUtil;
import dhm.com.dhmshop.framework.module.home.entity.EliteGoodsEntity;
import dhm.com.dhmshop.framework.module.home.entity.HomeHotGoodsList;
import dhm.com.dhmshop.framework.module.home.entity.HotBannerEntity;
import dhm.com.dhmshop.framework.module.home.entity.HotGoodsEntity;
import dhm.com.dhmshop.framework.module.home.entity.RecommendShopEntity;
import dhm.com.dhmshop.framework.module.home.entity.TopNewsEntity;
import dhm.com.dhmshop.framework.module.home.model.HomeHotModel;
import dhm.com.dhmshop.framework.module.home.view.HomeHotView;

public class HomeHotFragment extends BaseFragment<HomeHotModel> implements HomeHotView {

    private Banner home_banner;
    private ImageView hotGoodsImage;
    private ImageView dailyShopImage;
    private ImageView hasGoodImage;
    private ViewFlipper viewFlipper;
    private HotGoodsListAdapter goodsListAdapter;

    public HomeHotFragment() {
    }

    @Override
    protected void initView(View inflate) {
        home_banner = findViewById(R.id.home_banner);
        hotGoodsImage = findViewById(R.id.hotGoodsImage);
        hasGoodImage = findViewById(R.id.hasGoodImage);
        dailyShopImage = findViewById(R.id.dailyShopImage);
        viewFlipper = findViewById(R.id.home_topnews);
        RecyclerView hotGoodsList = findViewById(R.id.hot_goods_list);
        goodsListAdapter = new HotGoodsListAdapter();
        hotGoodsList.setAdapter(goodsListAdapter);
    }

    @Override
    protected void initData() {
        model.getHotBanner();//调Banner接口
        model.getHotGoods();//首页热门的商品(单个商品)
        model.getEliteGoods();//首页有好货的商品
        model.getRecommendShop();//首页每日好店
        model.getTopNews();//首页头条
        model.getHotGoodsList();//获取首页列表商品
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected HomeHotModel initModel() {
        return new HomeHotModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_child_remen;
    }

    /**
     * @param bannerEntity banner数据成功回调
     */
    @Override
    public void getBannerSuccess(HotBannerEntity bannerEntity) {
        home_banner.setImages(bannerEntity.getInfo())
                .setDelayTime(3000)
                .isAutoPlay(true)
                .setBannerAnimation(Transformer.DepthPage)
                .setOnBannerListener((position -> {//banner点击监听

                }))
                .setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        HotBannerEntity.InfoBean images = (HotBannerEntity.InfoBean) path;
                        GlideUtil.loadImage(context,images.getContent(),imageView);
                    }
                }).start();
    }

    /**
     *
     * @param hotGoodsEntities 首页热门的商品(单个商品)成功回调
     */
    @Override
    public void onSuccessHotGoods(List<HotGoodsEntity> hotGoodsEntities) {
        GlideUtil.loadImage(context,StringUtil.preventNull(hotGoodsEntities.get(0).getGoods_images()),hotGoodsImage);
    }

    /**
     *
     * @param eliteGoodsEntities 首页有好货的商品成功回调
     */
    @Override
    public void onSuccessEliteGoods(List<EliteGoodsEntity> eliteGoodsEntities) {
        GlideUtil.loadImage(context, StringUtil.preventNull(eliteGoodsEntities.get(0).getGoods_images()),hasGoodImage);
    }

    /**
     *
     * @param recommendShopEntities 首页每日好店成功回调
     */
    @Override
    public void onSuccessRecommendShop(List<RecommendShopEntity> recommendShopEntities) {
        GlideUtil.loadImage(context,StringUtil.preventNull(recommendShopEntities.get(0).getImg()),dailyShopImage);
    }

    /**
     *
     * @param topNewsEntities 首页头条成功回调
     */
    @Override
    public void onSuccessTopNews(List<TopNewsEntity> topNewsEntities) {
        for (TopNewsEntity topNews : topNewsEntities){
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.hometopnewsitem, viewFlipper,false);
            TextView title = view.findViewById(R.id.zixuntitle);
            title.setText(topNews.getPost_title());
            viewFlipper.addView(view);
            viewFlipper.startFlipping();
        }
    }

    /**
     * @param homeHotGoodsLists 最新商品列表成功回调
     */
    @Override
    public void onSuccessHotGoodsList(List<HomeHotGoodsList> homeHotGoodsLists) {
        if (homeHotGoodsLists == null || homeHotGoodsLists.size() == 0){
            return;
        }
        goodsListAdapter.addData(homeHotGoodsLists);
    }

    /**
     * fragment销毁时 关闭跑马灯
     */
    @Override
    public void onStop() {
        super.onStop();
        viewFlipper.stopFlipping();
    }

    /**
     * 热门商品内部适配器
     */
    class HotGoodsListAdapter extends BaseQuickAdapter<HomeHotGoodsList, MyBaseViewHolder>{

        public HotGoodsListAdapter() {
            super(R.layout.hotgoodslistitem);
        }
        @Override
        protected void convert(MyBaseViewHolder helper, HomeHotGoodsList item) {
            helper.setText(R.id.goods_name,StringUtil.preventNull(item.getGoods_name()))
                    .setText(R.id.price,StringUtil.preventNull(item.getPrice()))
                    .setText(R.id.sale_num,StringUtil.buildString(item.getSale_num(),"人付款"));
            GlideUtil.loadImage(context,item.getGoods_images(),helper.getView(R.id.goods_images));
        }
    }
}
