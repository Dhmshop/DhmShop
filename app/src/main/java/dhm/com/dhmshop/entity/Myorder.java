package dhm.com.dhmshop.entity;

import java.util.List;

public class Myorder {

    /**
     * code : 1
     * message : 成功
     * data : [{"order_id":1,"order_sn":"201904021544010133567","goods_id":1,"goods_img":"/upload/goods/1/20190329/4391167bd506fdaf9a67d65916b6fecb.jpg","goods_name":"猕猴桃","desc":"非常非常好吃的猕猴桃","price":"11.55","count":2,"uid":2,"address":"北京市丰台区万丰商务写字楼6087","pay_type":1,"total":"23.10","create_time":"2019-04-02 15:45:07","express_price":"11.55","delivery_money":"15.50","start_delivery":"20.00","need_delivery":1,"shop_id":1,"shop_name":"好再来盖饭","shop_img":"/upload/shop_entry/1/20190410/e07926aac889aba3eb34475968938edb.png","shop_type":1,"pay_status":2,"pay_status_name":"支付成功"}]
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
         * order_id : 1
         * order_sn : 201904021544010133567
         * goods_id : 1
         * goods_img : /upload/goods/1/20190329/4391167bd506fdaf9a67d65916b6fecb.jpg
         * goods_name : 猕猴桃
         * desc : 非常非常好吃的猕猴桃
         * price : 11.55
         * count : 2
         * uid : 2
         * address : 北京市丰台区万丰商务写字楼6087
         * pay_type : 1
         * total : 23.10
         * create_time : 2019-04-02 15:45:07
         * express_price : 11.55
         * delivery_money : 15.50
         * start_delivery : 20.00
         * need_delivery : 1
         * shop_id : 1
         * shop_name : 好再来盖饭
         * shop_img : /upload/shop_entry/1/20190410/e07926aac889aba3eb34475968938edb.png
         * shop_type : 1
         * pay_status : 2
         * pay_status_name : 支付成功
         */

        private int order_id;
        private String order_sn;
        private int goods_id;
        private String goods_img;
        private String goods_name;
        private String desc;
        private String price;
        private int count;
        private int uid;
        private String address;
        private int pay_type;
        private String total;
        private String create_time;
        private String express_price;
        private String delivery_money;
        private String start_delivery;
        private int need_delivery;
        private int shop_id;
        private String shop_name;
        private String shop_img;
        private int shop_type;
        private int pay_status;
        private String pay_status_name;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public int getGoods_id() {
            return goods_id;
        }

        public void setGoods_id(int goods_id) {
            this.goods_id = goods_id;
        }

        public String getGoods_img() {
            return goods_img;
        }

        public void setGoods_img(String goods_img) {
            this.goods_img = goods_img;
        }

        public String getGoods_name() {
            return goods_name;
        }

        public void setGoods_name(String goods_name) {
            this.goods_name = goods_name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getExpress_price() {
            return express_price;
        }

        public void setExpress_price(String express_price) {
            this.express_price = express_price;
        }

        public String getDelivery_money() {
            return delivery_money;
        }

        public void setDelivery_money(String delivery_money) {
            this.delivery_money = delivery_money;
        }

        public String getStart_delivery() {
            return start_delivery;
        }

        public void setStart_delivery(String start_delivery) {
            this.start_delivery = start_delivery;
        }

        public int getNeed_delivery() {
            return need_delivery;
        }

        public void setNeed_delivery(int need_delivery) {
            this.need_delivery = need_delivery;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getShop_img() {
            return shop_img;
        }

        public void setShop_img(String shop_img) {
            this.shop_img = shop_img;
        }

        public int getShop_type() {
            return shop_type;
        }

        public void setShop_type(int shop_type) {
            this.shop_type = shop_type;
        }

        public int getPay_status() {
            return pay_status;
        }

        public void setPay_status(int pay_status) {
            this.pay_status = pay_status;
        }

        public String getPay_status_name() {
            return pay_status_name;
        }

        public void setPay_status_name(String pay_status_name) {
            this.pay_status_name = pay_status_name;
        }
    }
}
