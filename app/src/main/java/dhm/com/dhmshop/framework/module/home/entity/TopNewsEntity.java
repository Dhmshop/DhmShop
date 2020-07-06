package dhm.com.dhmshop.module.home.entity;

public class TopNewsEntity {
    /**
     * id : 1
     * post_title : 商城正式上线了！！！！
     * post_keywords : 商城上线
     */

    private int id;
    private String post_title;
    private String post_keywords;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_keywords() {
        return post_keywords;
    }

    public void setPost_keywords(String post_keywords) {
        this.post_keywords = post_keywords;
    }
}
