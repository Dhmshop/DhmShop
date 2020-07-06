package dhm.com.dhmshop.framework.base;

import java.util.List;

import dhm.com.dhmshop.framework.module.commodity.entity.OneGoodsEntity;
import dhm.com.dhmshop.framework.module.home.entity.CategoryTypeGoodsEntity;
import dhm.com.dhmshop.framework.module.home.entity.EliteGoodsEntity;
import dhm.com.dhmshop.framework.module.home.entity.HomeHotGoodsList;
import dhm.com.dhmshop.framework.module.home.entity.HotBannerEntity;
import dhm.com.dhmshop.framework.module.home.entity.HotGoodsEntity;
import dhm.com.dhmshop.framework.module.home.entity.ListEliteGoodsEntity;
import dhm.com.dhmshop.framework.module.home.entity.ListHotGoodsEntity;
import dhm.com.dhmshop.framework.module.home.entity.RecommendShopEntity;
import dhm.com.dhmshop.framework.module.home.entity.RecommendShopListEntity;
import dhm.com.dhmshop.framework.module.home.entity.TabCategoryEntity;
import dhm.com.dhmshop.framework.module.home.entity.TopNewsEntity;
import dhm.com.dhmshop.framework.module.type.entity.ClassIficationEntity;
import dhm.com.dhmshop.framework.module.type.entity.TypeCategoryChildEntity;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @describe 接口地址
 */
public interface HttpApi {

    /**
     * 首页 Tab栏接口
     */
    @GET("/appapi/goods/get_one_category")
    Observable<BaseInfo<List<TabCategoryEntity>>> getTabCategory();


    /**
     * 首页 热门 Banner
     */
    @GET("/appapi/ads/get_ads")
    Observable<BaseInfo<HotBannerEntity>> getHotBanner();

    /**
     * 首页热门的商品(单个商品)
     */
    @GET("/appapi/goods/is_hot_goods")
    Observable<BaseInfo<List<HotGoodsEntity>>> getHotGoods();

    /**
     * 有好货商品列表
     */
    @FormUrlEncoded
    @POST("/appapi/goods/list_elite_goods")
    Observable<BaseInfo<List<ListEliteGoodsEntity>>> getListEliteGoods(@Field("page") int page);

    /**
     * 首页有好货的商品（获取单个商品）
     */
    @GET("/appapi/goods/is_elite_goods")
    Observable<BaseInfo<List<EliteGoodsEntity>>> getEliteGoods();

    /**
     * 有好货商品列表
     */
    @FormUrlEncoded
    @POST("/appapi/goods/list_hot_goods")
    Observable<BaseInfo<List<ListHotGoodsEntity>>> getListHotGoods(@Field("page") int page);

    /**
     * 首页每日好店
     */
    @GET("/appapi/shop/home_recommend_shop")
    Observable<BaseInfo<List<RecommendShopEntity>>> getRecommendShop();

    /**
     * 获取好店列表
     */
    @GET("/appapi/shop/recommend_shop_list")
    Observable<BaseInfo<List<RecommendShopListEntity>>> getRecommendShopList();

    /**
     * 首页头条
     */
    @GET("/appapi/information/top_news")
    Observable<BaseInfo<List<TopNewsEntity>>> getTopNews();

    /**
     * 首页销量排前的商品
     */
    @GET("/appapi/goods/hot_goods_list")
    Observable<BaseInfo<List<HomeHotGoodsList>>> getHotGoodsList();


    /**
     * 获取父类下面的所有子类和销量前六的产品
     */
    @FormUrlEncoded
    @POST("/appapi/goods/get_child_category_goods")
    Observable<BaseInfo<CategoryTypeGoodsEntity>> getCategoryTypeGoods(@Field("pid") int pid);


    /**
     * 取父类下面的所有子类
     */
    @FormUrlEncoded
    @POST("/appapi/goods/get_child_category")
    Observable<BaseInfo<List<TypeCategoryChildEntity>>> getTypeCategoryChild(@Field("pid") int pid);


    /**
     * 获取单个商品信息
     */
    @FormUrlEncoded
    @POST("/appapi/goods/one_goods")
    Observable<BaseInfo<OneGoodsEntity>> getOneGoods(@Field("gid") int gid, @Field("uid")int uid);

}

