package dhm.com.dhmshop.framework.module.commodity.entity;

import java.util.List;

public class OneGoodsEntity {


    /**
     * uid : 4
     * goods_name : 牛仔半身裙
     * goods_id : 1
     * shop_id : 3
     * shop_info : {"store_name":"新蕾女装","img":"/upload/shop_entry/4/20200626/b292b308077bc8458128cfc57254005f.jpg","mobile":"15830108496","is_free":1,"piece":0,"money":"0.00","postage":"0.00","province":"河北省","city":"邢台市","area":"隆尧县","address":"柴荣大街15号"}
     * goods_remark : 牛仔半身裙
     * goods_content : 牛仔半身裙
     * goods_images : ["/upload/goods/4/20200604/1907057c6ebd581f214430948c03bcda.jpg","/upload/goods/4/20200604/07f46549a97f1fabd3d3184508c71849.jpg","/upload/goods/4/20200604/b4dda3b66f8c5d4477d85c1c1370ee51.jpg"]
     * goods_content_images : [""]
     * goods_images_thumb : /upload/goods/4/20200604/1907057c6ebd581f214430948c03bcda.jpg
     * sale_num : 0
     * store_count : 1000
     * price : 49.80
     * click_count : 1
     * comment_count : 0
     * express_price : 46.80
     * is_elite : 0
     * is_hot : 0
     * is_discount : 0
     * is_new : 0
     * is_on_sale : 1
     * term_id : 3
     * param : [{"param_name":"颜色","param_value":["白色","蓝色","黄色","粉色"]},{"param_name":"尺码","param_value":["S","M","L","XL","XXL","XXXL"]}]
     * on_time : 2020-06-04 15:30
     * shop_kefu : 15830108496
     * is_collect : 1
     * is_support : 0
     * comment : [{"cuid":5,"content":"穿出来效果很好","star":5,"time":"2020-06-26 17:35:08","nickname":"15830108495","headsmall":"/upload/time.jpg"}]
     */

    private int uid;
    private String goods_name;
    private int goods_id;
    private int shop_id;
    private ShopInfoBean shop_info;
    private String goods_remark;
    private String goods_content;
    private String goods_images_thumb;
    private int sale_num;
    private int store_count;
    private String price;
    private int click_count;
    private int comment_count;
    private String express_price;
    private int is_elite;
    private int is_hot;
    private int is_discount;
    private int is_new;
    private int is_on_sale;
    private int term_id;
    private String on_time;
    private String shop_kefu;
    private int is_collect;
    private int is_support;
    private List<String> goods_images;
    private List<String> goods_content_images;
    private List<ParamBean> param;
    private List<CommentBean> comment;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public int getShop_id() {
        return shop_id;
    }

    public void setShop_id(int shop_id) {
        this.shop_id = shop_id;
    }

    public ShopInfoBean getShop_info() {
        return shop_info;
    }

    public void setShop_info(ShopInfoBean shop_info) {
        this.shop_info = shop_info;
    }

    public String getGoods_remark() {
        return goods_remark;
    }

    public void setGoods_remark(String goods_remark) {
        this.goods_remark = goods_remark;
    }

    public String getGoods_content() {
        return goods_content;
    }

    public void setGoods_content(String goods_content) {
        this.goods_content = goods_content;
    }

    public String getGoods_images_thumb() {
        return goods_images_thumb;
    }

    public void setGoods_images_thumb(String goods_images_thumb) {
        this.goods_images_thumb = goods_images_thumb;
    }

    public int getSale_num() {
        return sale_num;
    }

    public void setSale_num(int sale_num) {
        this.sale_num = sale_num;
    }

    public int getStore_count() {
        return store_count;
    }

    public void setStore_count(int store_count) {
        this.store_count = store_count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getClick_count() {
        return click_count;
    }

    public void setClick_count(int click_count) {
        this.click_count = click_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public String getExpress_price() {
        return express_price;
    }

    public void setExpress_price(String express_price) {
        this.express_price = express_price;
    }

    public int getIs_elite() {
        return is_elite;
    }

    public void setIs_elite(int is_elite) {
        this.is_elite = is_elite;
    }

    public int getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(int is_hot) {
        this.is_hot = is_hot;
    }

    public int getIs_discount() {
        return is_discount;
    }

    public void setIs_discount(int is_discount) {
        this.is_discount = is_discount;
    }

    public int getIs_new() {
        return is_new;
    }

    public void setIs_new(int is_new) {
        this.is_new = is_new;
    }

    public int getIs_on_sale() {
        return is_on_sale;
    }

    public void setIs_on_sale(int is_on_sale) {
        this.is_on_sale = is_on_sale;
    }

    public int getTerm_id() {
        return term_id;
    }

    public void setTerm_id(int term_id) {
        this.term_id = term_id;
    }

    public String getOn_time() {
        return on_time;
    }

    public void setOn_time(String on_time) {
        this.on_time = on_time;
    }

    public String getShop_kefu() {
        return shop_kefu;
    }

    public void setShop_kefu(String shop_kefu) {
        this.shop_kefu = shop_kefu;
    }

    public int getIs_collect() {
        return is_collect;
    }

    public void setIs_collect(int is_collect) {
        this.is_collect = is_collect;
    }

    public int getIs_support() {
        return is_support;
    }

    public void setIs_support(int is_support) {
        this.is_support = is_support;
    }

    public List<String> getGoods_images() {
        return goods_images;
    }

    public void setGoods_images(List<String> goods_images) {
        this.goods_images = goods_images;
    }

    public List<String> getGoods_content_images() {
        return goods_content_images;
    }

    public void setGoods_content_images(List<String> goods_content_images) {
        this.goods_content_images = goods_content_images;
    }

    public List<ParamBean> getParam() {
        return param;
    }

    public void setParam(List<ParamBean> param) {
        this.param = param;
    }

    public List<CommentBean> getComment() {
        return comment;
    }

    public void setComment(List<CommentBean> comment) {
        this.comment = comment;
    }

    public static class ShopInfoBean {
        /**
         * store_name : 新蕾女装
         * img : /upload/shop_entry/4/20200626/b292b308077bc8458128cfc57254005f.jpg
         * mobile : 15830108496
         * is_free : 1
         * piece : 0
         * money : 0.00
         * postage : 0.00
         * province : 河北省
         * city : 邢台市
         * area : 隆尧县
         * address : 柴荣大街15号
         */

        private String store_name;
        private String img;
        private String mobile;
        private int is_free;
        private int piece;
        private String money;
        private String postage;
        private String province;
        private String city;
        private String area;
        private String address;

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getIs_free() {
            return is_free;
        }

        public void setIs_free(int is_free) {
            this.is_free = is_free;
        }

        public int getPiece() {
            return piece;
        }

        public void setPiece(int piece) {
            this.piece = piece;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getPostage() {
            return postage;
        }

        public void setPostage(String postage) {
            this.postage = postage;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public static class ParamBean {
        /**
         * param_name : 颜色
         * param_value : ["白色","蓝色","黄色","粉色"]
         */

        private String param_name;
        private List<String> param_value;

        public String getParam_name() {
            return param_name;
        }

        public void setParam_name(String param_name) {
            this.param_name = param_name;
        }

        public List<String> getParam_value() {
            return param_value;
        }

        public void setParam_value(List<String> param_value) {
            this.param_value = param_value;
        }
    }

    public static class CommentBean {
        /**
         * cuid : 5
         * content : 穿出来效果很好
         * star : 5
         * time : 2020-06-26 17:35:08
         * nickname : 15830108495
         * headsmall : /upload/time.jpg
         */

        private int cuid;
        private String content;
        private int star;
        private String time;
        private String nickname;
        private String headsmall;

        public int getCuid() {
            return cuid;
        }

        public void setCuid(int cuid) {
            this.cuid = cuid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getStar() {
            return star;
        }

        public void setStar(int star) {
            this.star = star;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadsmall() {
            return headsmall;
        }

        public void setHeadsmall(String headsmall) {
            this.headsmall = headsmall;
        }
    }
}
