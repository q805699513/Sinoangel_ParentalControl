package com.sinoangel.ctrl.parentalcontrol.index.bean;

import com.sinoangel.ctrl.parentalcontrol.base.NetBean;

import java.util.List;

/**
 * Created by Z on 2017/6/1.
 */

public class StatisticsBean extends NetBean {


    /**
     * flag : 1
     * error : []
     * data : [{"id":"4","userid":"293","packName":"dax6","time":"2017-06-01 11:13","context":"Catbug: Catbug Says","img":"http://cn.img.store.sinoangel.cn/icons/2016-08-12/01/Catbug_Catbug-Says.png","useTime":"4490"},{"id":"5","userid":"293","packName":"dax6","time":"2017-06-01 11:13","context":"Catbug: Catbug Says","img":"http://cn.img.store.sinoangel.cn/icons/2016-08-12/01/Catbug_Catbug-Says.png","useTime":"4490"},{"id":"6","userid":"293","packName":"dax6","time":"2017-06-01 11:13","context":"Catbug: Catbug Says","img":"http://cn.img.store.sinoangel.cn/icons/2016-08-12/01/Catbug_Catbug-Says.png","useTime":"4490"},{"id":"7","userid":"293","packName":"dax6","time":"2017-06-01 11:13","context":"Catbug: Catbug Says","img":"http://cn.img.store.sinoangel.cn/icons/2016-08-12/01/Catbug_Catbug-Says.png","useTime":"4490"}]
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 4
         * userid : 293
         * packName : dax6
         * time : 2017-06-01 11:13
         * context : Catbug: Catbug Says
         * img : http://cn.img.store.sinoangel.cn/icons/2016-08-12/01/Catbug_Catbug-Says.png
         * useTime : 4490
         */

        private String id;
        private String userid;
        private String packName;//儿童名称
        private String time;
        private String context;
        private String img;
        private String useTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }

        public String getPackName() {
            return packName;
        }

        public void setPackName(String packName) {
            this.packName = packName;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUseTime() {
            return useTime;
        }

        public void setUseTime(String useTime) {
            this.useTime = useTime;
        }
    }
}
