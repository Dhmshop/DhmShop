package dhm.com.dhmshop.framework.module.home.entity;


/**
 * 首页每日好店
 */
public class RecommendShopEntity {
    /**
     * store_name : 新蕾女装
     * img : null
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
