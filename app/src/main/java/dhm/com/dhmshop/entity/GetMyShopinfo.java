package dhm.com.dhmshop.entity;

import java.util.List;

public class GetMyShopinfo {

    /**
     * code : 1
     * message : 成功
     * data : [{"goods_id":1,"goods_name":"休闲T恤","express_price":"36.80","store_count":1000,"price":"39.80","goods_remark":"休闲T恤","goods_content":"休闲T恤","goods_images":"/upload/goods/4/20200603/dcde1308ba4431ec2d12aebdc1e4147c.jpg,/upload/goods/4/20200603/0a91f0b2a519944021880be31734cc4d.jpg,/upload/goods/4/20200603/7c5fd0af7d26e053fc7a67c99cecffc5.jpg","goods_images_thumb":"/upload/goods/4/20200603/dcde1308ba4431ec2d12aebdc1e4147c.jpg","on_time":"2020-06-03 11:36","sale_num":0,"click_count":1,"collection_count":0,"shop_id":3,"uid":4,"shop_type":3,"is_on_sale":1,"sale_status":"在售"}]
     */

    private int code;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * goods_id : 1
         * goods_name : 休闲T恤
         * express_price : 36.80
         * store_count : 1000
         * price : 39.80
         * goods_remark : 休闲T恤
         * goods_content : 休闲T恤
         * goods_images : /upload/goods/4/20200603/dcde1308ba4431ec2d12aebdc1e4147c.jpg,/upload/goods/4/20200603/0a91f0b2a519944021880be31734cc4d.jpg,/upload/goods/4/20200603/7c5fd0af7d26e053fc7a67c99cecffc5.jpg
         * goods_images_thumb : /upload/goods/4/20200603/dcde1308ba4431ec2d12aebdc1e4147c.jpg
         * on_time : 2020-06-03 11:36
         * sale_num : 0
         * click_count : 1
         * collection_count : 0
         * shop_id : 3
         * uid : 4
         * shop_type : 3
         * is_on_sale : 1
         * sale_status : 在售
         */

        private int goods_id;
        private String goods_name;
        private String express_price;
        private int store_count;
        private String price;
        private String goods_remark;
        private String goods_content;
        private String goods_images;
        private String goods_images_thumb;
        private String on_time;
        private int sale_num;
        private int click_count;
        private int collection_count;
        private int shop_id;
        private int uid;
        private int shop_type;
        private int is_on_sale;
        private String sale_status;

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getExpress_price() {
            return express_price;
        }

        public void setExpress_price(String express_price) {
            this.express_price = express_price;
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

        public String getGoods_images() {
            return goods_images;
        }

        public void setGoods_images(String goods_images) {
            this.goods_images = goods_images;
        }

        public String getGoods_images_thumb() {
            return goods_images_thumb;
        }

        public void setGoods_images_thumb(String goods_images_thumb) {
            this.goods_images_thumb = goods_images_thumb;
        }

        public String getOn_time() {
            return on_time;
        }

        public void setOn_time(String on_time) {
            this.on_time = on_time;
        }

        public int getSale_num() {
            return sale_num;
        }

        public void setSale_num(int sale_num) {
            this.sale_num = sale_num;
        }

        public int getClick_count() {
            return click_count;
        }

        public void setClick_count(int click_count) {
            this.click_count = click_count;
        }

        public int getCollection_count() {
            return collection_count;
        }

        public void setCollection_count(int collection_count) {
            this.collection_count = collection_count;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getShop_type() {
            return shop_type;
        }

        public void setShop_type(int shop_type) {
            this.shop_type = shop_type;
        }

        public int getIs_on_sale() {
            return is_on_sale;
        }

        public void setIs_on_sale(int is_on_sale) {
            this.is_on_sale = is_on_sale;
        }

        public String getSale_status() {
            return sale_status;
        }

        public void setSale_status(String sale_status) {
            this.sale_status = sale_status;
        }
    }
}
