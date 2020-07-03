package dhm.com.dhmshop.entity;

/**
 * Created by admin
 * 2019/4/24
 */
public class HeaderIMage {


    /**
     * code : 1
     * message : 更新成功
     * data : {"headsmall":"/upload/headsmall/1/20190331/7ceaed2b7aceba182422602f3c8f8d29.jpg"}
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
         * headsmall : /upload/headsmall/1/20190331/7ceaed2b7aceba182422602f3c8f8d29.jpg
         */

        private String headsmall;

        public String getHeadsmall() {
            return headsmall;
        }

        public void setHeadsmall(String headsmall) {
            this.headsmall = headsmall;
        }
    }
}
