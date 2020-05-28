package dhm.com.dhmshop.base.netWork;

public interface Constant {
    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^1[3-9]\\d{9}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 至少包含数字跟字母，可以有字符
     */
    String reg = "(?=.*([a-zA-Z].*))(?=.*[0-9].*)[a-zA-Z0-9-*/+.~!@#$%^&*()]{6,20}$";

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











    String PATH = "http://hl-api.51huole.cn/api/";
    //判断手机号是否存在
    String EXITS_PHONE = "exists_phone";

    //发送手机验证码
    String SEND_SMS= "send_sms";

    //登录
    String LOGIN= "login_p";

    //生成图形验证码
    String CATPCHA= "captcha";

    //判断图形验证码是否正确
    String VERIFY_GCODE= "verify_gcode";

    //判断手机验证码是否正确
    String VERIFY_PCODE= "verify_pcode";

    //判断手机验证码是否正确
    String MODIFY_PWD= "modify_pwd";


    //个人认证
    String PERSONAL_CERTIFICATE="personal_certificate";


    //经纪公司认证
    String JYP_CERTIFICATE="jyp_certificate";


    //企业认证
    String ENTERPRISE_CERTIFICATE="enterprise_certificate";

    //明星认证
    String STAR_CERTIFICATE="star_certificate";

    //经纪人认证
    String BROKER_CERTIFICATE="broker_certificate";

    //首页轮播文字
    String SEEK_CAROUSEL="seek_carousel";

    //首页轮播图片
    String SEEK_BANNER="seek_banner";

    //首页热门明星
    String SEEKHOTSTAR="seek_hot_star?offset=";

    //首页火演推荐
    String SEEKRECOMMENDSTAR="seek_recommend_star?offset=";

    //首页火演推荐
    String SEEKPRIJECTDOCK="seek_project_dock";

    //首页火演推荐
    String SEARCH_STAR="search_star?kw=";


    //首页火演推荐
    String NOSTAR="feedback_nostarfound?uid=";



    //首页明星详情页
    String GETSINGER="get_singer?uid=";




    //首页明星详情页
    String SIMGERLIST="singer_list?offset=";


    //首页明星详情页
    String SINGERALL="singer_all_price?uid=";


    //首页明星详情页
    String CITIES="cities";

}