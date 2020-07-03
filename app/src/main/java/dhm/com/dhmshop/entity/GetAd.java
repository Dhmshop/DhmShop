package dhm.com.dhmshop.entity;

import java.util.List;

public class GetAd {

    /**
     * code : 1
     * message : 获取成功
     * data : [{"id":1,"uid":4,"shop_id":3,"ad_images":"/upload/shop_ad/4/20200604/e35fdb77afd4bee706fbbd9da860a106.jpg"},{"id":2,"uid":4,"shop_id":3,"ad_images":"/upload/shop_ad/4/20200604/3ef34d23a4cfd980f6982dbd10c5dccd.jpg"}]
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
         * id : 1
         * uid : 4
         * shop_id : 3
         * ad_images : /upload/shop_ad/4/20200604/e35fdb77afd4bee706fbbd9da860a106.jpg
         */

        private int id;
        private int uid;
        private int shop_id;
        private String ad_images;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
        }

        public String getAd_images() {
            return ad_images;
        }

        public void setAd_images(String ad_images) {
            this.ad_images = ad_images;
        }
    }
}
