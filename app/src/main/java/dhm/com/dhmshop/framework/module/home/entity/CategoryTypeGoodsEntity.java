package dhm.com.dhmshop.framework.module.home.entity;

import java.util.List;

/**
 *获取父类下面的所有子类和销量前六的产品
 */
public class CategoryTypeGoodsEntity {
    private List<CatelistBean> catelist;
    private List<GoodsBean> goods;

    public List<CatelistBean> getCatelist() {
        return catelist;
    }

    public void setCatelist(List<CatelistBean> catelist) {
        this.catelist = catelist;
    }

    public List<GoodsBean> getGoods() {
        return goods;
    }

    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    public static class CatelistBean {
        /**
         * id : 3
         * name : 套装
         * thumb :
         */

        private int id;
        private String name;
        private String thumb;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }
    }

    public static class GoodsBean {
        /**
         * goods_name : 牛仔半身裙
         * price : 49.80
         * sale_num : 0
         * goods_images : /upload/goods/4/20200604/1907057c6ebd581f214430948c03bcda.jpg
         */

        private String goods_name;
        private String price;
        private int sale_num;
        private String goods_images;

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
