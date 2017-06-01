package com.sinoangel.ctrl.parentalcontrol.index.bean;

import com.sinoangel.ctrl.parentalcontrol.base.NetBean;

import java.io.Serializable;
import java.util.List;


/**
 * Created by Z on 2017/5/31.
 */

public class WebBean extends NetBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 7
         * user_id : 293
         * website_name : qwe
         * website_url : www.baidu.com
         */

        private String id;
        private String website_name;
        private String website_url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWebsite_name() {
            return website_name;
        }

        public void setWebsite_name(String website_name) {
            this.website_name = website_name;
        }

        public String getWebsite_url() {
            return website_url;
        }

        public void setWebsite_url(String website_url) {
            this.website_url = website_url;
        }
    }
}
