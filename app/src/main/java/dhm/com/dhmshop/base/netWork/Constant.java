package dhm.com.dhmshop.base.netWork;

import android.os.Environment;

import dhm.com.dhmshop.utils.EncryptUtil;

public interface Constant {
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "/^[1][3,4,5,6,7,8,9][0-9]{9}$/";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 至少包含数字跟字母，可以有字符
     */
//    String reg = "/^\\w{6,16}$/";
    String reg = "(?=.*([a-zA-Z].*))(?=.*[0-9].*)[a-zA-Z0-9-*/+.~!@#$%^&*()]{6,20}$";

    /**
     * 正则表达式：只能是中文
     */
    public static final String CHINESE = "[\\u4e00-\\u9fa5]+";

    /**
     * 正则表达式：名字
     */
    String name = "^[\\u4e00-\\u9fa5][\\u4e00-\\u9fa5]+([•·]?+[\\u4e00-\\u9fa5]+)*$";


    /**
     * 正则表达式：身份证号码
     */
    String num = "/(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)/";


    /**
     * 正则表达式：统一社会信用代码
     */
    String company = "[1-9A-GY]{1}[1239]{1}[1-5]{1}[0-9]{5}[0-9A-Z]{10}";


    /**
     * 正则表达式：公司名称
     */
    String companyname = "^[\\u4e00-\\u9fa5][\\u4e00-\\u9fa5]+";


    String TOKEN = EncryptUtil.MD5("junzhicheng");


    String PATH = "http://app.jzcto.com";

    //登录
    String LOGIN = "/appapi/user/login";

    //    用户注册
    String UserRegist = "/appapi/user/register";

    //    商户注册
    String ShopRegist = "/appapi/user/shop_register";


    //修改头像
    String ChangeHeadImage = "/appapi/user/modify_headsmall";

    //修改用户信息
    String ChangeUserInfo = "/appapi/user/update_info";

    //发送验证码
    String SendMessage = "/appapi/user/send_message";

    //修改用户密码
    String SetNewPass = "/appapi/user/set_new_pass";

    //修改用户信息
    String CheckUser = "/appapi/user/check_user";

    //获取用户信息
    String GetUserInfo = "/appapi/user/get_userinfo";

    //获取用户收货地址列表
    String GetUserAddr = "/appapi/user/get_address";

    //新增收货地址
    String AddAddress = "/appapi/user/add_address";

    //修改手机号
    String ChangePhone = "/appapi/user/modify_mobile";

    //获取某个收货地址
    String GetAddress = "/appapi/user/get_address_info";

    //修改收货地址
    String EditAddress = "/appapi/user/edit_address";


    //获取一级分类
    String GetoneCategory = "/appapi/goods/get_one_category";

    //首页广告大图
    String GetAds = "/appapi/ads/get_ads";

    //首页头条
    String Topnews = "/appapi/information/top_news";

    //首页热门的商品(单个商品)
    String IsHotGoods = "/appapi/goods/is_hot_goods";

    //首页有好货的商品（获取单个商品）
    String IsEliteGoods = "/appapi/goods/is_elite_goods";

    //首页每日好店
    String HomeRecommendShop = "/appapi/shop/home_recommend_shop";


    //首页销量排前的商品
    String HotGoodsList = "/appapi/goods/hot_goods_list";

    //获取父类下面的所有子类和销量前六的产品 首页女装
    String GetChildCategoryGoods = "/appapi/goods/get_child_category_goods";

    //    拍照
    public static final int IMAGE_REQUEST_CODE = 12;
    public static final int REQUESTCODE = 200;
    public static final int REQUESTCODE1 = 201;
    public static final int RESULTCODE = 300;

    //拍照用到
    public static final String Tag = "dhm.com.dhmshop.fileProvider";

    //头像地址
    public static final String HeadPath = Environment.getExternalStorageDirectory().getAbsolutePath() +
            "/Shop/Image/HeadPath/";
    //头像地址
    public static final String ImagePath = Environment.getExternalStorageDirectory().getAbsolutePath() +
            "/Shop/Image/ImagePath/";

    //    店铺地址
    static String STOREIMGPATH = Environment.getExternalStorageDirectory().getAbsolutePath() +
            "/shop/Image/StroeImgPath/";


    //获取店铺收益总额
    String ShopProfit= "/appapi/shop/shop_profit";

    //删除某个地址
    String DelAddress= "/appapi/user/del_address";

    //获取我的店铺信息
    String GetMyShopinfo= "/appapi/shop/get_my_shopinfo";

    //修改我的店铺信息
    String FixShopinfo= "/appapi/shop/modify_shop_info";

    //小店上货
    String AddGoods= "/appapi/goods/add_goods";

    //获取一级分类
    String GetOneCategory= "/appapi/goods/get_one_category";

    //取父类下面的所有子类
    String GetChildCategory= "/appapi/goods/get_child_category";

    //关于
    String GetAboutCon= "/appapi/user/get_about_con";

    //获取我发布的商品列表
    String GetMyGoods= "/appapi/goods/get_my_goods";

    //获取购物车列表
    String ShoppingList= "/appapi/goods/shopping_list";

    //收藏店铺
    String AddShopCollect= "/appapi/shop/add_shop_collect";

    //取消收藏店铺
    String DelShopCollect= "/appapi/shop/del_shop_collect";

    //查看我收藏的店铺
    String MyShopCollects= "/appapi/shop/my_shop_collects";

    //获取单个商品信息
    String OneGoods= "/appapi/goods/one_goods";

    //获取全部订单
    String Allorders= "/appapi/order/all_orders";

    //获取广告图片列表
    String ShopAds= "/appapi/shop/shop_ads";

    //上传店铺广告图
    String AddShopAds= "/appapi/shop/add_shop_ads";

    //删除广告图片
    String DelShopAds= "/appapi/shop/del_shop_ads";

    //获取店铺订单总数
    String ShopOrderCount= "/appapi/shop/shop_order_count";

    //向商家催发货
    String AddSendOrder= "/appapi/order/add_send_order";

    //确认收货
    String OrderOver= "/appapi/order/order_over";

    //修改订单收货地址
    String OrderAddressModify= "/appapi/order/order_address_modify";

    //发布评价
    String AddGoodsComments= "/appapi/order/add_goods_comments";

    //发布评价
    String LogisticsInfo= "/appapi/order/logistics_info";

    //发布评价
    String CashOutShop= "/appapi/pay/cash_out_shop";

}