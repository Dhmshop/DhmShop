package dhm.com.dhmshop.entity;

import java.util.List;

public class GetAds {
    /**
     * code : 1
     * message : 成功
     * data : {"type":2,"type_name":"首页大图广告","info":[{"content":"/upload/default/advers/adver_small.png","shop_id":0}]}
     */

    private int code;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * type : 2
         * type_name : 首页大图广告
         * info : [{"content":"/upload/default/advers/adver_small.png","shop_id":0}]
         */

        private int type;
        private String type_name;
        private List<InfoBean> info;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public List<InfoBean> getInfo() {
            return info;
        }

        public void setInfo(List<InfoBean> info) {
            this.info = info;
        }

        public static class InfoBean {
            /**
             * content : /upload/default/advers/adver_small.png
             * shop_id : 0
             */

            private String content;
            private int shop_id;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getShop_id() {
                return shop_id;
            }

            public void setShop_id(int shop_id) {
                this.shop_id = shop_id;
            }
        }
    }
}
