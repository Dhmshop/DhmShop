package dhm.com.dhmshop.module.home.entity;

public class HomeHotGoodsList {
    /**
     * goods_id : 1
     * goods_name : 休闲T恤
     * price : 39.80
     * express_price : 36.80
     * sale_num : 0
     * goods_images : /upload/goods/4/20200603/dcde1308ba4431ec2d12aebdc1e4147c.jpg
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
