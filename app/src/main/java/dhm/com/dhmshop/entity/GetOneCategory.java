package dhm.com.dhmshop.entity;

import java.util.List;

public class GetOneCategory {

    /**
     * code : 1
     * message : 获取成功
     * data : [{"id":1,"name":"女装","thumb":""},{"id":4,"name":"男装","thumb":"http://www.junzhichengshop.com/upload/admin/20200521/0521379d5ffef10c7545e4fac1846933.jpg"}]
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
         * name : 女装
         * thumb :
         */

        private int id;
        private String name;
        private String thumb;
        private String checked="0";


        public String getChecked() {
            return checked;
        }

        public void setChecked(String checked) {
            this.checked = checked;
        }

        private ChildCategory childCategory;


        public ChildCategory getChildCategory() {
            return childCategory;
        }

        public void setChildCategory(ChildCategory childCategory) {
            this.childCategory = childCategory;
        }
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
    public static class ChildCategory {


        /**
         * code : 1
         * message : 获取成功
         * data : [{"id":2,"name":"套装我","thumb":""},{"id":3,"name":"套装","thumb":""}]
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
             * name : 套装我
             * thumb :
             */

            private int id;
            private String name;
            private String thumb;

            private String checked="0";


            public String getChecked() {
                return checked;
            }

            public void setChecked(String checked) {
                this.checked = checked;
            }
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
}
