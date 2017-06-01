package com.sinoangel.ctrl.parentalcontrol.index.bean;

import com.sinoangel.ctrl.parentalcontrol.base.NetBean;

/**
 * Created by Z on 2017/5/31.
 */

public class LimitBean extends NetBean {

    /**
     * error : []
     * data : {"user_balance":"4767","allow_child_buy":"1","payment_cycle_day":"0","payment_sinocoin_limit":"150"}
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
         * user_balance : 4767
         * allow_child_buy : 1
         * payment_cycle_day : 0
         * payment_sinocoin_limit : 150
         */

        private String user_balance;
        private String allow_child_buy;
        private String payment_cycle_day;
        private String payment_sinocoin_limit;

        public String getUser_balance() {
            return user_balance;
        }

        public void setUser_balance(String user_balance) {
            this.user_balance = user_balance;
        }

        public String getAllow_child_buy() {
            return allow_child_buy;
        }

        public void setAllow_child_buy(String allow_child_buy) {
            this.allow_child_buy = allow_child_buy;
        }

        public String getPayment_cycle_day() {
            return payment_cycle_day;
        }

        public void setPayment_cycle_day(String payment_cycle_day) {
            this.payment_cycle_day = payment_cycle_day;
        }

        public String getPayment_sinocoin_limit() {
            return payment_sinocoin_limit;
        }

        public void setPayment_sinocoin_limit(String payment_sinocoin_limit) {
            this.payment_sinocoin_limit = payment_sinocoin_limit;
        }
    }
}
