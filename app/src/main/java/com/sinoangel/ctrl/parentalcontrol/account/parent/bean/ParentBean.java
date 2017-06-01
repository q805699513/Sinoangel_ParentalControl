package com.sinoangel.ctrl.parentalcontrol.account.parent.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Z on 2017/5/26.
 */

public class ParentBean {
    /**
     * flag : 1
     * error : []
     * data : {"user":{"id":"293","realm":"lenovo_kids","username":"小王子","email":"q@qq.com","emailVerified":"1","owner_id":"293","country_id":"51","first_lang":"2","sex":"1","pic_id":"6","usericon":"http://api2.sinoangel.cn/icons/usericon/1492573431smaillHead.png","allow_child_buy":"1","payment_cycle_day":"0","payment_sinocoin_limit":"0","create_time":"2016-09-13 08:38:59","break_time":"210","day_total_useage_time":"30","each_usage_time":"0","country_name":"阿尔及利亚"}}
     */

    private int flag;
    private DataBean data;
    private List<?> error;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public List<?> getError() {
        return error;
    }

    public void setError(List<?> error) {
        this.error = error;
    }

    public static class DataBean {
        /**
         * user : {"id":"293","realm":"lenovo_kids","username":"小王子","email":"q@qq.com","emailVerified":"1","owner_id":"293","country_id":"51","first_lang":"2","sex":"1","pic_id":"6","usericon":"http://api2.sinoangel.cn/icons/usericon/1492573431smaillHead.png","allow_child_buy":"1","payment_cycle_day":"0","payment_sinocoin_limit":"0","create_time":"2016-09-13 08:38:59","break_time":"210","day_total_useage_time":"30","each_usage_time":"0","country_name":"阿尔及利亚"}
         */

        private UserBean user;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean implements Serializable {
            /**
             * id : 293
             * realm : lenovo_kids
             * username : 小王子
             * email : q@qq.com
             * emailVerified : 1
             * owner_id : 293
             * country_id : 51
             * first_lang : 2
             * sex : 1
             * pic_id : 6
             * usericon : http://api2.sinoangel.cn/icons/usericon/1492573431smaillHead.png
             * allow_child_buy : 1
             * payment_cycle_day : 0
             * payment_sinocoin_limit : 0
             * create_time : 2016-09-13 08:38:59
             * break_time : 210
             * day_total_useage_time : 30
             * each_usage_time : 0
             * country_name : 阿尔及利亚
             */

            private String id;
//            private String realm;
            private String realname;
            private String email;
            private String emailVerified;
            private String owner_id;
            private String country_id;
            private String first_lang;
            private String sex;
            private String pic_id;
            private String usericon;
//            private String allow_child_buy;
//            private String payment_cycle_day;
//            private String payment_sinocoin_limit;
            private String create_time;
            private String break_time;
            private String day_total_useage_time;
            private String each_usage_time;
            private String country_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

//            public String getRealm() {
//                return realm;
//            }
//
//            public void setRealm(String realm) {
//                this.realm = realm;
//            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getEmailVerified() {
                return emailVerified;
            }

            public void setEmailVerified(String emailVerified) {
                this.emailVerified = emailVerified;
            }

            public String getOwner_id() {
                return owner_id;
            }

            public void setOwner_id(String owner_id) {
                this.owner_id = owner_id;
            }

            public String getCountry_id() {
                return country_id;
            }

            public void setCountry_id(String country_id) {
                this.country_id = country_id;
            }

            public String getFirst_lang() {
                return first_lang;
            }

            public void setFirst_lang(String first_lang) {
                this.first_lang = first_lang;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getPic_id() {
                return pic_id;
            }

            public void setPic_id(String pic_id) {
                this.pic_id = pic_id;
            }

            public String getUsericon() {
                return usericon;
            }

            public void setUsericon(String usericon) {
                this.usericon = usericon;
            }

//            public String getAllow_child_buy() {
//                return allow_child_buy;
//            }
//
//            public void setAllow_child_buy(String allow_child_buy) {
//                this.allow_child_buy = allow_child_buy;
//            }

//            public String getPayment_cycle_day() {
//                return payment_cycle_day;
//            }
//
//            public void setPayment_cycle_day(String payment_cycle_day) {
//                this.payment_cycle_day = payment_cycle_day;
//            }

//            public String getPayment_sinocoin_limit() {
//                return payment_sinocoin_limit;
//            }
//
//            public void setPayment_sinocoin_limit(String payment_sinocoin_limit) {
//                this.payment_sinocoin_limit = payment_sinocoin_limit;
//            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getBreak_time() {
                return break_time;
            }

            public void setBreak_time(String break_time) {
                this.break_time = break_time;
            }

            public String getDay_total_useage_time() {
                return day_total_useage_time;
            }

            public void setDay_total_useage_time(String day_total_useage_time) {
                this.day_total_useage_time = day_total_useage_time;
            }

            public String getEach_usage_time() {
                return each_usage_time;
            }

            public void setEach_usage_time(String each_usage_time) {
                this.each_usage_time = each_usage_time;
            }

            public String getCountry_name() {
                return country_name;
            }

            public void setCountry_name(String country_name) {
                this.country_name = country_name;
            }
        }
    }
}
