package dhm.com.dhmshop.entity;

import java.util.List;

public class GetCollectionShop {

    /**
     * code : 1
     * message : 获取成功
     * data : [{"id":2,"uid":5,"shop_id":3,"add_time":1591754641,"store_name":"新蕾女装","img":"/upload/shop_entry/4/20200604/55faac58697b1e0c7e12d2254e0866bc.jpg","suid":4}]
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
         * id : 2
         * uid : 5
         * shop_id : 3
         * add_time : 1591754641
         * store_name : 新蕾女装
         * img : /upload/shop_entry/4/20200604/55faac58697b1e0c7e12d2254e0866bc.jpg
         * suid : 4
         */

        private int id;
        private int uid;
        private int shop_id;
        private int add_time;
        private String store_name;
        private String img;
        private int suid;

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

        public int getAdd_time() {
            return add_time;
        }

        public void setAdd_time(int add_time) {
            this.add_time = add_time;
        }

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

        public int getSuid() {
            return suid;
        }

        public void setSuid(int suid) {
            this.suid = suid;
        }
    }
}
