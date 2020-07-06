package dhm.com.dhmshop.entity;

import java.util.List;

public class HotGoodsList {


    /**
     * code : 1
     * message : 获取成功
     * data : [{"goods_id":1,"goods_name":"牛仔半身裙","price":"49.80","express_price":"46.80","sale_num":0,"goods_images":"/upload/goods/4/20200604/1907057c6ebd581f214430948c03bcda.jpg"},{"goods_id":2,"goods_name":"牛仔半身裙","price":"49.80","express_price":"46.80","sale_num":0,"goods_images":"/upload/goods/4/20200604/04607a32fb51a4fab409f0cd58d5b48f.jpg"},{"goods_id":3,"goods_name":"牛仔半身裙","price":"49.80","express_price":"46.80","sale_num":0,"goods_images":"/upload/goods/4/20200604/55a521b67c0b7116ece6f096a6d606b6.jpg"},{"goods_id":4,"goods_name":"牛仔半身裙","price":"49.80","express_price":"46.80","sale_num":0,"goods_images":"/upload/goods/4/20200604/e16bbfdec72bb01e387e59242ee6002f.jpg"},{"goods_id":5,"goods_name":"雪纺半身裙","price":"49.80","express_price":"46.80","sale_num":0,"goods_images":"/upload/goods/4/20200616/14c684d4e0e05a64c9ce0cd087296198.jpg"},{"goods_id":6,"goods_name":"商品1","price":"6.00","express_price":"2.00","sale_num":0,"goods_images":"/upload/goods/9/20200616/6584a65be01d24e2bef66b1950edc801.jpg"}]
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
         * goods_name : 牛仔半身裙
         * price : 49.80
         * express_price : 46.80
         * sale_num : 0
         * goods_images : /upload/goods/4/20200604/1907057c6ebd581f214430948c03bcda.jpg
         */

        private int goods_id;
        private String goods_name;
        private String price;
        private String express_price;
        private int sale_num;
        private String goods_images;

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

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getExpress_price() {
            return express_price;
        }

        public void setExpress_price(String express_price) {
            this.express_price = express_price;
        }

        public int getSale_num() {
            return sale_num;
        }

        public void setSale_num(int sale_num) {
            this.sale_num = sale_num;
        }

        public String getGoods_images() {
            return goods_images;
        }

        public void setGoods_images(String goods_images) {
            this.goods_images = goods_images;
        }
    }
}
