package dhm.com.dhmshop.module.home.entity;

public class HotGoodsEntity {
    /**
     * goods_id : 5
     * goods_name : 雪纺半身裙
     * price : 49.80
     * express_price : 46.80
     * click_count : 0
     * goods_images : /upload/goods/4/20200616/14c684d4e0e05a64c9ce0cd087296198.jpg
     */

    private int goods_id;
    private String goods_name;
    private String price;
    private String express_price;
    private int click_count;
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

    public int getClick_count() {
        return click_count;
    }

    public void setClick_count(int click_count) {
        this.click_count = click_count;
    }

    public String getGoods_images() {
        return goods_images;
    }

    public void setGoods_images(String goods_images) {
        this.goods_images = goods_images;
    }
}
