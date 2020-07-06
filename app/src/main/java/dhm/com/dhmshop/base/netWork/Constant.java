package dhm.com.dhmshop.base.netWork;

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




}