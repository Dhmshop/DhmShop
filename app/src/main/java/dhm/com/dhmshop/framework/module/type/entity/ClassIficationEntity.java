package dhm.com.dhmshop.framework.module.type.entity;

import java.util.List;

public class ClassIficationEntity {


    /**
     * code : 1
     * message : 获取成功
     * data : [{"id":19,"name":"热门","thumb":""},{"id":1,"name":"女装","thumb":""},{"id":4,"name":"男装","thumb":""},{"id":5,"name":"饰品","thumb":""},{"id":6,"name":"母婴","thumb":""},{"id":7,"name":"百货","thumb":""},{"id":8,"name":"美妆","thumb":""}]
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
         * id : 19
         * name : 热门
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
}
