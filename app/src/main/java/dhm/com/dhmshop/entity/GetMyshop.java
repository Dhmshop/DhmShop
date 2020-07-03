package dhm.com.dhmshop.entity;

public class GetMyshop {

    /**
     * code : 1
     * message : 获取成功
     * data : {"shop_id":3,"store_name":"新蕾女装","mobile":"15830108496","province":"河北省","city":"邢台市","area":"隆尧县","address":"柴荣大街15号","excerpt":"夏季精品女装","is_free":0,"money":"0.00","piece":0,"postage":"0.00"}
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
         * shop_id : 3
         * store_name : 新蕾女装
         * mobile : 15830108496
         * province : 河北省
         * city : 邢台市
         * area : 隆尧县
         * address : 柴荣大街15号
         * excerpt : 夏季精品女装
         * is_free : 0
         * money : 0.00
         * piece : 0
         * postage : 0.00
         */

        private int shop_id;
        private String store_name;
        private String mobile;
        private String province;
        private String city;
        private String area;
        private String address;
        private String excerpt;
        private String shop_type;
        private int is_free;
        private String money;
        private int piece;
        private String postage;

        public String getShop_type() {
            return shop_type;
        }

        public void setShop_type(String shop_type) {
            this.shop_type = shop_type;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
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

        public String getExcerpt() {
            return excerpt;
        }

        public void setExcerpt(String excerpt) {
            this.excerpt = excerpt;
        }

        public int getIs_free() {
            return is_free;
        }

        public void setIs_free(int is_free) {
            this.is_free = is_free;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public int getPiece() {
            return piece;
        }

        public void setPiece(int piece) {
            this.piece = piece;
        }

        public String getPostage() {
            return postage;
        }

        public void setPostage(String postage) {
            this.postage = postage;
        }
    }
}
