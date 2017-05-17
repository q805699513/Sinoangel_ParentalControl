package com.sinoangel.ctrl.parentalcontrol.base;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.sinoangel.ctrl.parentalcontrol.R;


/**
 * 类说明：全局的appliction
 */
public class BaseApplication extends Application {

    public static BaseApplication instance;

    //谷歌统计
    private Tracker mTracker;

    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /**
     * 获取唯一的MarsApplication实例
     **/
    public static BaseApplication getInstance() {
        if (instance == null) {
            instance = new BaseApplication();
        }
        return instance;
    }

    synchronized public Tracker getDefaultTracker() {
        if (mTracker == null) {
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
            mTracker = analytics.newTracker(R.xml.global_tracker);
        }
        return mTracker;
    }

    public void sendAnalyticsActivity(String name) {
        getDefaultTracker().setScreenName(name);
        getDefaultTracker().send(new HitBuilders.ScreenViewBuilder().build());
    }

    public void sendAnalyticsEvent(String type, String name) {
        getDefaultTracker().send(new HitBuilders.EventBuilder()
                .setCategory(type)
                .setAction(name)
                .build());
    }
}