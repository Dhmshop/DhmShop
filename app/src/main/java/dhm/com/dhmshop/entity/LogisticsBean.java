package dhm.com.dhmshop.entity;

import java.util.List;

public class LogisticsBean {

    /**
     * code : 1
     * message : 成功
     * data : {"nu":"9862325358361","com":"youzhengguonei","state":"0","data":[{"time":"2020-06-09 16:05:14","ftime":"2020-06-09 16:05:14","context":"[北京市]离开【北京邮件处理中心】,下一站【北京重件进口作业区】","location":"北京市"},{"time":"2020-06-09 13:11:48","ftime":"2020-06-09 13:11:48","context":"[北京市]到达【北京邮件处理中心】","location":"北京市"},{"time":"2020-06-08 23:06:28","ftime":"2020-06-08 23:06:28","context":"[郑州市]离开【河南省邮件处理中心】,下一站【北京邮件处理中心】（经转）","location":"郑州市"},{"time":"2020-06-08 17:25:40","ftime":"2020-06-08 17:25:40","context":"[郑州市]到达【河南省邮件处理中心】（经转）","location":"郑州市"},{"time":"2020-06-08 16:06:57","ftime":"2020-06-08 16:06:57","context":"[4107]离开【新乡包件处理班】,下一站【河南省邮件处理中心】","location":"4107"},{"time":"2020-06-08 12:45:03","ftime":"2020-06-08 12:45:03","context":"---到达【新乡包件处理班】","location":""},{"time":"2020-06-08 12:22:56","ftime":"2020-06-08 12:22:56","context":"[新乡市]离开【河南省新乡县大宗揽收营业部】,下一站【新乡包件处理班】","location":"新乡市"},{"time":"2020-06-08 12:20:20","ftime":"2020-06-08 12:20:20","context":"[新乡市]【河南省新乡县大宗揽收营业部】已收件,揽投员:张文明,电话:13598693168","location":"新乡市"}]}
     */

    private int code;
    private String message;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * nu : 9862325358361
         * com : youzhengguonei
         * state : 0
         * data : [{"time":"2020-06-09 16:05:14","ftime":"2020-06-09 16:05:14","context":"[北京市]离开【北京邮件处理中心】,下一站【北京重件进口作业区】","location":"北京市"},{"time":"2020-06-09 13:11:48","ftime":"2020-06-09 13:11:48","context":"[北京市]到达【北京邮件处理中心】","location":"北京市"},{"time":"2020-06-08 23:06:28","ftime":"2020-06-08 23:06:28","context":"[郑州市]离开【河南省邮件处理中心】,下一站【北京邮件处理中心】（经转）","location":"郑州市"},{"time":"2020-06-08 17:25:40","ftime":"2020-06-08 17:25:40","context":"[郑州市]到达【河南省邮件处理中心】（经转）","location":"郑州市"},{"time":"2020-06-08 16:06:57","ftime":"2020-06-08 16:06:57","context":"[4107]离开【新乡包件处理班】,下一站【河南省邮件处理中心】","location":"4107"},{"time":"2020-06-08 12:45:03","ftime":"2020-06-08 12:45:03","context":"---到达【新乡包件处理班】","location":""},{"time":"2020-06-08 12:22:56","ftime":"2020-06-08 12:22:56","context":"[新乡市]离开【河南省新乡县大宗揽收营业部】,下一站【新乡包件处理班】","location":"新乡市"},{"time":"2020-06-08 12:20:20","ftime":"2020-06-08 12:20:20","context":"[新乡市]【河南省新乡县大宗揽收营业部】已收件,揽投员:张文明,电话:13598693168","location":"新乡市"}]
         */

        private String nu;
        private String com;
        private String state;
        private List<DataBean> data;

        public String getNu() {
            return nu;
        }

        public void setNu(String nu) {
            this.nu = nu;
        }

        public String getCom() {
            return com;
        }

        public void setCom(String com) {
            this.com = com;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * time : 2020-06-09 16:05:14
             * ftime : 2020-06-09 16:05:14
             * context : [北京市]离开【北京邮件处理中心】,下一站【北京重件进口作业区】
             * location : 北京市
             */

            private String time;
            private String ftime;
            private String context;
            private String location;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getFtime() {
                return ftime;
            }

            public void setFtime(String ftime) {
                this.ftime = ftime;
            }

            public String getContext() {
                return context;
            }

            public void setContext(String context) {
                this.context = context;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }
        }
    }
}
