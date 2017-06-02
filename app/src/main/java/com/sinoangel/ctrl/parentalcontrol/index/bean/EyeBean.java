package com.sinoangel.ctrl.parentalcontrol.index.bean;

import com.sinoangel.ctrl.parentalcontrol.base.NetBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Z on 2017/5/26.
 */

public class EyeBean extends NetBean{
    /**
     * flag : 1
     * error : []
     * data : {"user":{"id":"293","realm":"lenovo_kids","username":"小王子","email":"q@qq.com","emailVerified":"1","owner_id":"293","country_id":"51","first_lang":"2","sex":"1","pic_id":"6","usericon":"http://api2.sinoangel.cn/icons/usericon/1492573431smaillHead.png","allow_child_buy":"1","payment_cycle_day":"0","payment_sinocoin_limit":"0","create_time":"2016-09-13 08:38:59","break_time":"210","day_total_useage_time":"30","each_usage_time":"0","country_name":"阿尔及利亚"}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
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
             * create_time : 2016-09-13 08:38:59
             * break_time : 210
             * day_total_useage_time : 30
             * each_usage_time : 0
             */

            private String break_time;
            private String day_total_useage_time;
            private String each_usage_time;

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
        }
    }
}
