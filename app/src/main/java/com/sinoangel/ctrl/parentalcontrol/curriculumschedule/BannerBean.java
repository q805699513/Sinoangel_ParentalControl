package com.sinoangel.ctrl.parentalcontrol.curriculumschedule;

import com.sinoangel.ctrl.parentalcontrol.webview.RecyclerBanner;

/**
 * Created by Z on 2017/6/5.
 */

public class BannerBean implements RecyclerBanner.BannerEntity {

    private String url;


    public BannerBean(String str) {
        url = str;
    }

    @Override
    public String getUrl() {
        return url;
    }
}
