package dhm.com.dhmshop.entity;

import java.util.List;

public class UserAddr {
    /**
     * code : 1
     * message : 成功
     * data : [{"id":7,"province":"北京市","city":"北京市","area":"丰台区","address":"沙子口路宣祥家园17号楼1005","tag":" ","add_time":"2020-06-08 14:53","uid":7,"user_name":"胡圆圆","mobile":"18931077675","is_default":0},{"id":8,"province":"北京市","city":"北京市","area":"丰台区","address":"沙子口路宣祥家园17号楼1005","tag":" ","add_time":"2020-06-08 14:53","uid":7,"user_name":"胡圆圆","mobile":"18931077675","is_default":0},{"id":9,"province":"北京市","city":"北京市","area":"丰台区","address":"沙子口路宣祥家园17号楼1005","tag":" ","add_time":"2020-06-08 14:53","uid":7,"user_name":"胡圆圆","mobile":"18931077675","is_default":0}]
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
         * id : 7
         * province : 北京市
         * city : 北京市
         * area : 丰台区
         * address : 沙子口路宣祥家园17号楼1005
         * tag :
         * add_time : 2020-06-08 14:53
         * uid : 7
         * user_name : 胡圆圆
         * mobile : 18931077675
         * is_default : 0
         */

        private int id;
        private String province;
        private String city;
        private String area;
        private String address;
        private String tag;
        private String add_time;
        private int uid;
        private String user_name;
        private String mobile;
        private int is_default;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getIs_default() {
            return is_default;
        }

        public void setIs_default(int is_default) {
            this.is_default = is_default;
        }
    }
}
