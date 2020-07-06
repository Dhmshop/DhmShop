package dhm.com.dhmshop.entity;

import java.util.List;

public class GetGoodsDetail {

    /**
     * code : 1
     * message : 成功
     * data : {"uid":4,"goods_name":"牛仔半身裙","goods_id":1,"shop_id":3,"goods_remark":"牛仔半身裙","goods_content":"牛仔半身裙","goods_images":["/upload/goods/4/20200604/1907057c6ebd581f214430948c03bcda.jpg","/upload/goods/4/20200604/07f46549a97f1fabd3d3184508c71849.jpg","/upload/goods/4/20200604/b4dda3b66f8c5d4477d85c1c1370ee51.jpg"],"goods_images_thumb":"/upload/goods/4/20200604/1907057c6ebd581f214430948c03bcda.jpg","sale_num":0,"store_count":1000,"price":"49.80","click_count":1,"comment_count":0,"express_price":"46.80","is_elite":0,"is_hot":0,"is_free":0,"is_discount":0,"is_new":0,"is_on_sale":1,"term_id":3,"piece":0,"money":"0.00","param":[{"param_name":"颜色","param_value":["白色","蓝色","黄色","粉色"]},{"param_name":"尺码","param_value":["S","M","L","XL","XXL","XXXL"]}],"on_time":"2020-06-04 15:30","shop_kefu":null,"is_collect":0,"is_support":0,"comment":[]}
     */

    private int code;
    private String message;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * uid : 4
         * goods_name : 牛仔半身裙
         * goods_id : 1
         * shop_id : 3
         * goods_remark : 牛仔半身裙
         * goods_content : 牛仔半身裙
         * goods_images : ["/upload/goods/4/20200604/1907057c6ebd581f214430948c03bcda.jpg","/upload/goods/4/20200604/07f46549a97f1fabd3d3184508c71849.jpg","/upload/goods/4/20200604/b4dda3b66f8c5d4477d85c1c1370ee51.jpg"]
         * goods_images_thumb : /upload/goods/4/20200604/1907057c6ebd581f214430948c03bcda.jpg
         * sale_num : 0
         * store_count : 1000
         * price : 49.80
         * click_count : 1
         * comment_count : 0
         * express_price : 46.80
         * is_elite : 0
         * is_hot : 0
         * is_free : 0
         * is_discount : 0
         * is_new : 0
         * is_on_sale : 1
         * term_id : 3
         * piece : 0
         * money : 0.00
         * param : [{"param_name":"颜色","param_value":["白色","蓝色","黄色","粉色"]},{"param_name":"尺码","param_value":["S","M","L","XL","XXL","XXXL"]}]
         * on_time : 2020-06-04 15:30
         * shop_kefu : null
         * is_collect : 0
         * is_support : 0
         * comment : []
         */

        private int uid;
        private String goods_name;
        private int goods_id;
        private int shop_id;
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
        private int is_free;
        private int is_discount;
        private int is_new;
        private int is_on_sale;
        private int term_id;
        private int piece;
        private String money;
        private String on_time;
        private Object shop_kefu;
        private int is_collect;
        private int is_support;
        private List<String> goods_images;
        private List<ParamBean> param;
        private List<?> comment;

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

        public int getIs_free() {
            return is_free;
        }

        public void setIs_free(int is_free) {
            this.is_free = is_free;
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

        public String getOn_time() {
            return on_time;
        }

        public void setOn_time(String on_time) {
            this.on_time = on_time;
        }

        public Object getShop_kefu() {
            return shop_kefu;
        }

        public void setShop_kefu(Object shop_kefu) {
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

        public List<ParamBean> getParam() {
            return param;
        }

        public void setParam(List<ParamBean> param) {
            this.param = param;
        }

        public List<?> getComment() {
            return comment;
        }

        public void setComment(List<?> comment) {
            this.comment = comment;
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
    }
}
