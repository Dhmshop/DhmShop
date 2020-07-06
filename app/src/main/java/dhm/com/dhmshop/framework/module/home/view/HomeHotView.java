package dhm.com.dhmshop.module.home.view;

import java.util.List;

import dhm.com.dhmshop.framework.base.BaseView;
import dhm.com.dhmshop.module.home.entity.EliteGoodsEntity;
import dhm.com.dhmshop.module.home.entity.HomeHotGoodsList;
import dhm.com.dhmshop.module.home.entity.HotBannerEntity;
import dhm.com.dhmshop.module.home.entity.HotGoodsEntity;
import dhm.com.dhmshop.module.home.entity.RecommendShopEntity;
import dhm.com.dhmshop.module.home.entity.TopNewsEntity;

public interface HomeHotView extends BaseView {
    void getBannerSuccess(HotBannerEntity bannerEntity);

    void onSuccessHotGoods(List<HotGoodsEntity> hotGoodsEntities);

    void onSuccessEliteGoods(List<EliteGoodsEntity> eliteGoodsEntities);

    void onSuccessRecommendShop(List<RecommendShopEntity> recommendShopEntities);

    void onSuccessTopNews(List<TopNewsEntity> topNewsEntities);

    void onSuccessHotGoodsList(List<HomeHotGoodsList> homeHotGoodsLists);
}
