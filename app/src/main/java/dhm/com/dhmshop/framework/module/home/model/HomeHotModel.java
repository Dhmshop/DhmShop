package dhm.com.dhmshop.module.home.model;

import java.util.List;

import dhm.com.dhmshop.framework.base.BaseModel;
import dhm.com.dhmshop.framework.network.OnSuccessAndFaultListener;
import dhm.com.dhmshop.framework.network.OnSuccessAndFaultSub;
import dhm.com.dhmshop.framework.utils.ToastUtil;
import dhm.com.dhmshop.module.home.entity.EliteGoodsEntity;
import dhm.com.dhmshop.module.home.entity.HomeHotGoodsList;
import dhm.com.dhmshop.module.home.entity.HotBannerEntity;
import dhm.com.dhmshop.module.home.entity.HotGoodsEntity;
import dhm.com.dhmshop.module.home.entity.RecommendShopEntity;
import dhm.com.dhmshop.module.home.entity.TopNewsEntity;
import dhm.com.dhmshop.module.home.view.HomeHotView;

public class HomeHotModel extends BaseModel<HomeHotView> {
    /**
     * 首页 热门 Banner
     */
    public void getHotBanner() {
        requestData(observable().getHotBanner(), new OnSuccessAndFaultSub<>(new OnSuccessAndFaultListener<HotBannerEntity>() {
            @Override
            public void onSuccess(HotBannerEntity bannerEntity) {
                mView.getBannerSuccess(bannerEntity);
            }

            @Override
            public void onFault(String errorMsg) {
                ToastUtil.show(errorMsg);
            }
        }));
    }

    /**
     * 首页热门的商品(单个商品)
     */
    public void getHotGoods() {
        requestData(observable().getHotGoods(), new OnSuccessAndFaultSub<>(new OnSuccessAndFaultListener<List<HotGoodsEntity>>() {
            @Override
            public void onSuccess(List<HotGoodsEntity> hotGoodsEntities) {
                mView.onSuccessHotGoods(hotGoodsEntities);
            }

            @Override
            public void onFault(String errorMsg) {
                ToastUtil.show(errorMsg);
            }
        }));
    }

    /**
     * 首页有好货的商品(单个商品)
     */
    public void getEliteGoods() {
        requestData(observable().getEliteGoods(), new OnSuccessAndFaultSub<>(new OnSuccessAndFaultListener<List<EliteGoodsEntity>>() {
            @Override
            public void onSuccess(List<EliteGoodsEntity> eliteGoodsEntities) {
                mView.onSuccessEliteGoods(eliteGoodsEntities);
            }

            @Override
            public void onFault(String errorMsg) {
                ToastUtil.show(errorMsg);
            }
        }));
    }

    /**
     * 首页每日好店(单个商品)
     */
    public void getRecommendShop() {
        requestData(observable().getRecommendShop(), new OnSuccessAndFaultSub<>(new OnSuccessAndFaultListener<List<RecommendShopEntity>>() {
            @Override
            public void onSuccess(List<RecommendShopEntity> recommendShopEntities) {
                mView.onSuccessRecommendShop(recommendShopEntities);
            }

            @Override
            public void onFault(String errorMsg) {
                ToastUtil.show(errorMsg);
            }
        }));
    }

    /**
     * 首页头条
     */
    public void getTopNews() {
        requestData(observable().getTopNews(), new OnSuccessAndFaultSub<>(new OnSuccessAndFaultListener<List<TopNewsEntity>>() {
            @Override
            public void onSuccess(List<TopNewsEntity> topNewsEntities) {
                mView.onSuccessTopNews(topNewsEntities);
            }

            @Override
            public void onFault(String errorMsg) {
                ToastUtil.show(errorMsg);
            }
        }));
    }

    /**
     *首页销量排前的商品
     */
    public void getHotGoodsList(){
        requestData(observable().getHotGoodsList(),new OnSuccessAndFaultSub<>(new OnSuccessAndFaultListener<List<HomeHotGoodsList>>() {
            @Override
            public void onSuccess(List<HomeHotGoodsList> homeHotGoodsLists) {
                mView.onSuccessHotGoodsList(homeHotGoodsLists);
            }

            @Override
            public void onFault(String errorMsg) {
                ToastUtil.show(errorMsg);
            }
        }));
    }
}
