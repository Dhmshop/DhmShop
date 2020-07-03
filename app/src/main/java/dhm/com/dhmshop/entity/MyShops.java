package dhm.com.dhmshop.entity;

import java.util.List;

public class MyShops {

    /**
     * code : 1
     * message : 获取成功
     * data : [{"id":1,"goods_name":"商品1","goods_id":6,"number":2,"price":"2.00","shop_id":5,"shop_name":"圆圆的","goods_formats":"蓝色，L","picture":"/upload/goods/9/20200616/6584a65be01d24e2bef66b1950edc801.jpg"},{"id":2,"goods_name":"上衣","goods_id":7,"number":2,"price":"2.00","shop_id":5,"shop_name":"圆圆的","goods_formats":"蓝色，L","picture":"/upload/goods/9/20200618/31699876155c53cef3726540fb0ea4ce.jpg"},{"id":3,"goods_name":"商品4","goods_id":10,"number":2,"price":"2.00","shop_id":5,"shop_name":"圆圆的","goods_formats":"蓝色，L","picture":"/upload/goods/9/20200618/69d348cdb67e3fd82b01ac53b0305819.jpg"}]
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
         * goods_name : 商品1
         * goods_id : 6
         * number : 2
         * price : 2.00
         * shop_id : 5
         * shop_name : 圆圆的
         * goods_formats : 蓝色，L
         * picture : /upload/goods/9/20200616/6584a65be01d24e2bef66b1950edc801.jpg
         */

        private int id;
        private String goods_name;
        private int goods_id;
        private int number;
        private float price;
        private int shop_id;
        private String shop_name;
        private String goods_formats;
        private String picture;
        private boolean checked=false;

        public boolean isChecked() {
            return checked;
        }

        public void setChecked(boolean checked) {
            this.checked = checked;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
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

        public String getGoods_formats() {
            return goods_formats;
        }

        public void setGoods_formats(String goods_formats) {
            this.goods_formats = goods_formats;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
