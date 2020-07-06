package dhm.com.dhmshop.entity;

import java.util.List;

public class HomeRecommendShop {

    /**
     * code : 1
     * message : 获取成功
     * data : [{"store_name":"新蕾女装","img":"/upload/shop_entry/4/20200604/55faac58697b1e0c7e12d2254e0866bc.jpg","shop_id":3}]
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
         * store_name : 新蕾女装
         * img : /upload/shop_entry/4/20200604/55faac58697b1e0c7e12d2254e0866bc.jpg
         * shop_id : 3
         */

        private String store_name;
        private String img;
        private int shop_id;

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

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }
    }
}
