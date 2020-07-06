package dhm.com.dhmshop.entity;

public class GetUserInfo {

    /**
     * code : 1
     * message : 成功
     * data : {"uid":7,"user_nickname":"13691195484","user_login":"hyy2162427251","user_email":null,"sex":0,"birthday":"2019-01-01","signature":null,"create_time":"2020-06-04 11:43","last_login_time":"2020-06-11 14:51","last_login_ip":"111.198.195.98","score":0,"user_type":2,"mobile":"13691195484","headsmall":"/upload/headsmall/7/20200611/5842d785bb5fa541376105a8dfe47d35.jpg","balance":"0.00","lat":null,"lng":null}
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
         * uid : 7
         * user_nickname : 13691195484
         * user_login : hyy2162427251
         * user_email : null
         * sex : 0
         * birthday : 2019-01-01
         * signature : null
         * create_time : 2020-06-04 11:43
         * last_login_time : 2020-06-11 14:51
         * last_login_ip : 111.198.195.98
         * score : 0
         * user_type : 2
         * mobile : 13691195484
         * headsmall : /upload/headsmall/7/20200611/5842d785bb5fa541376105a8dfe47d35.jpg
         * balance : 0.00
         * lat : null
         * lng : null
         */

        private int uid;
        private String user_nickname;
        private String user_login;
        private Object user_email;
        private int sex;
        private String birthday;
        private String address;
        private Object signature;
        private String create_time;
        private String last_login_time;
        private String last_login_ip;
        private int score;
        private int user_type;
        private String mobile;
        private String headsmall;
        private String balance;
        private Object lat;
        private Object lng;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getUser_nickname() {
            return user_nickname;
        }

        public void setUser_nickname(String user_nickname) {
            this.user_nickname = user_nickname;
        }

        public String getUser_login() {
            return user_login;
        }

        public void setUser_login(String user_login) {
            this.user_login = user_login;
        }

        public Object getUser_email() {
            return user_email;
        }

        public void setUser_email(Object user_email) {
            this.user_email = user_email;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public Object getSignature() {
            return signature;
        }

        public void setSignature(Object signature) {
            this.signature = signature;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getLast_login_time() {
            return last_login_time;
        }

        public void setLast_login_time(String last_login_time) {
            this.last_login_time = last_login_time;
        }

        public String getLast_login_ip() {
            return last_login_ip;
        }

        public void setLast_login_ip(String last_login_ip) {
            this.last_login_ip = last_login_ip;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getUser_type() {
            return user_type;
        }

        public void setUser_type(int user_type) {
            this.user_type = user_type;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getHeadsmall() {
            return headsmall;
        }

        public void setHeadsmall(String headsmall) {
            this.headsmall = headsmall;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public Object getLat() {
            return lat;
        }

        public void setLat(Object lat) {
            this.lat = lat;
        }

        public Object getLng() {
            return lng;
        }

        public void setLng(Object lng) {
            this.lng = lng;
        }
    }
}
