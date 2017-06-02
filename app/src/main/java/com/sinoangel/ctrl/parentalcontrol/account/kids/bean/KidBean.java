package com.sinoangel.ctrl.parentalcontrol.account.kids.bean;

import com.sinoangel.ctrl.parentalcontrol.base.NetBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Z on 2017/5/27.
 */

public class KidBean extends NetBean {

    /**
     * flag : 1
     * error : []
     * data : [{"id":"279","owner_id":"293","nickname":"baby3","first_lang":"0","sex":"1","pic_id":"0","usericon":"0","birthday":"2017-01-21"},{"id":"298","owner_id":"293","nickname":"dax6","first_lang":"0","sex":"0","pic_id":"6","usericon":"http://api2.sinoangel.cn/icons/usericon/1486462479smaillHead.png","birthday":"2013-02-05"},{"id":"424","owner_id":"293","nickname":"big12","first_lang":"0","sex":"0","pic_id":"0","usericon":"0","birthday":"1997-02-28"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 279
         * owner_id : 293
         * nickname : baby3
         * first_lang : 0
         * sex : 1
         * pic_id : 0
         * usericon : 0
         * birthday : 2017-01-21
         */

        private String id;
//        private String owner_id;//家长id
        private String nickname;
        private String first_lang;
        private String sex;
        private String pic_id;
        private String usericon;
        private String birthday;
        private String theme_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

//        public String getOwner_id() {
//            return owner_id;
//        }
//
//        public void setOwner_id(String owner_id) {
//            this.owner_id = owner_id;
//        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
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

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getTheme_id() {
            if (theme_id == null)
                return "";
            return theme_id;
        }

        public void setTheme_id(String theme_id) {
            this.theme_id = theme_id;
        }
    }
}
