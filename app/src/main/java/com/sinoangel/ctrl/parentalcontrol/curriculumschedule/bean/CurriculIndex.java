package com.sinoangel.ctrl.parentalcontrol.curriculumschedule.bean;

import com.sinoangel.ctrl.parentalcontrol.base.NetBean;
import com.sinoangel.ctrl.parentalcontrol.customview.RecyclerBanner;

import java.util.List;

/**
 * Created by Z on 2017/6/9.
 */

public class CurriculIndex extends NetBean {

    /**
     * error : []
     * data : {"ban":[{"ban_id":"1","ban_name":"banner1","ban_img":"images/1.jpg"},{"ban_id":"2","ban_name":"banner2","ban_img":"images/2.jpg"},{"ban_id":"3","ban_name":"banner3","ban_img":" images/3.jpg"},{"ban_id":"4","ban_name":"banner4","ban_img":"images/4.jpg"}],"nav":[{"nav_id":"1","nav_name":"语言","nav_img":"images/1.jpg"},{"nav_id":"2","nav_name":"认知","nav_img":"images/2.jpg"},{"nav_id":"3","nav_name":"教学","nav_img":"images/3.jpg"},{"nav_id":"4","nav_name":"艺术","nav_img":"images/4.jpg"}],"cont":[{"cont_id":"1","cont_title":"内容名称1","cont_content":"内容1","nav_id":"1","cont_icon":"images/icon1.jpg","cont_img":"images/c1.jpg","cont_zan":"0"},{"cont_id":"2","cont_title":"内容名称2","cont_content":"内容2","nav_id":"2","cont_icon":"images/icon2.jpg","cont_img":"images/c2.jpg","cont_zan":"13"},{"cont_id":"3","cont_title":"内容名称3","cont_content":"内容3","nav_id":"3","cont_icon":"images/icon3.jpg","cont_img":"images/c3.jpg","cont_zan":"24"},{"cont_id":"4","cont_title":"内容名称4","cont_content":"内容4","nav_id":"1","cont_icon":"images/icon4.jpg","cont_img":"images/c4.jpg","cont_zan":"6"},{"cont_id":"5","cont_title":"内容名称5","cont_content":"内容5","nav_id":"1","cont_icon":"images/icon5.jpg","cont_img":"images/c5.jpg","cont_zan":"0"},{"cont_id":"6","cont_title":"内容名称6","cont_content":"内容6","nav_id":"2","cont_icon":"images/icon6.jpg","cont_img":"images/c6.jpg","cont_zan":"3"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<BanBean> ban;
        private List<NavBean> nav;
        private List<ContBean> cont;

        public List<BanBean> getBan() {
            return ban;
        }

        public void setBan(List<BanBean> ban) {
            this.ban = ban;
        }

        public List<NavBean> getNav() {
            return nav;
        }

        public void setNav(List<NavBean> nav) {
            this.nav = nav;
        }

        public List<ContBean> getCont() {
            return cont;
        }

        public void setCont(List<ContBean> cont) {
            this.cont = cont;
        }

        public static class BanBean implements RecyclerBanner.BannerEntity{
            /**
             * ban_id : 1
             * ban_name : banner1
             * ban_img : images/1.jpg
             */

            private String ban_id;
            private String ban_name;
            private String ban_img;

            public String getBan_id() {
                return ban_id;
            }

            public void setBan_id(String ban_id) {
                this.ban_id = ban_id;
            }

            public String getBan_name() {
                return ban_name;
            }

            public void setBan_name(String ban_name) {
                this.ban_name = ban_name;
            }

            public String getBan_img() {
                return ban_img;
            }

            public void setBan_img(String ban_img) {
                this.ban_img = ban_img;
            }

            @Override
            public String getUrl() {
                return ban_img;
            }
        }

        public static class NavBean {
            /**
             * nav_id : 1
             * nav_name : 语言
             * nav_img : images/1.jpg
             */

            private String nav_id;
            private String nav_name;
            private String nav_img;

            public String getNav_id() {
                return nav_id;
            }

            public void setNav_id(String nav_id) {
                this.nav_id = nav_id;
            }

            public String getNav_name() {
                return nav_name;
            }

            public void setNav_name(String nav_name) {
                this.nav_name = nav_name;
            }

            public String getNav_img() {
                return nav_img;
            }

            public void setNav_img(String nav_img) {
                this.nav_img = nav_img;
            }
        }

        public static class ContBean {
            /**
             * cont_id : 1
             * cont_title : 内容名称1
             * cont_content : 内容1
             * nav_id : 1
             * cont_icon : images/icon1.jpg
             * cont_img : images/c1.jpg
             * cont_zan : 0
             */

            private String cont_id;
            private String cont_title;
            private String cont_content;
            private String nav_id;
            private String cont_icon;
            private String cont_img;
            private String cont_zan;

            public String getCont_id() {
                return cont_id;
            }

            public void setCont_id(String cont_id) {
                this.cont_id = cont_id;
            }

            public String getCont_title() {
                return cont_title;
            }

            public void setCont_title(String cont_title) {
                this.cont_title = cont_title;
            }

            public String getCont_content() {
                return cont_content;
            }

            public void setCont_content(String cont_content) {
                this.cont_content = cont_content;
            }

            public String getNav_id() {
                return nav_id;
            }

            public void setNav_id(String nav_id) {
                this.nav_id = nav_id;
            }

            public String getCont_icon() {
                return cont_icon;
            }

            public void setCont_icon(String cont_icon) {
                this.cont_icon = cont_icon;
            }

            public String getCont_img() {
                return cont_img;
            }

            public void setCont_img(String cont_img) {
                this.cont_img = cont_img;
            }

            public String getCont_zan() {
                return cont_zan;
            }

            public void setCont_zan(String cont_zan) {
                this.cont_zan = cont_zan;
            }
        }
    }
}
