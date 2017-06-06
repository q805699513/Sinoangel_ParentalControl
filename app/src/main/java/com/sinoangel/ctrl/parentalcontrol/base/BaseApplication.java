package com.sinoangel.ctrl.parentalcontrol.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.sinoangel.ctrl.parentalcontrol.BuildConfig;
import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.utils.AppUtils;
import com.umeng.analytics.MobclickAgent;
import com.youku.cloud.player.YoukuPlayerConfig;

import java.util.List;


/**
 * 类说明：全局的appliction
 */
public class BaseApplication extends Application {

    private static final String CLIENT_ID_WITH_AD = "08833bce826c6f8a";
    private static final String CLIENT_SECRET_WITH_AD = "729ce34694c94c57c01bab4d0fa41c7a";

    private static BaseApplication instance;

    //谷歌统计
    private Tracker mTracker;

    public void onCreate() {
        super.onCreate();
        instance = this;

        String processName = getProcessName(this,
                android.os.Process.myPid());
        if (TextUtils.equals(BuildConfig.APPLICATION_ID, processName)) {
            //主进程
            AppUtils.initWH(this);
        }

        MobclickAgent.setScenarioType(this, MobclickAgent.EScenarioType.E_UM_NORMAL);
        getDefaultTracker();

        YoukuPlayerConfig.setClientIdAndSecret(CLIENT_ID_WITH_AD, CLIENT_SECRET_WITH_AD);
        YoukuPlayerConfig.onInitial(this);
        YoukuPlayerConfig.setLog(true);
    }

    /**
     * 获取唯一的MarsApplication实例
     **/
    public static BaseApplication getInstance() {
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

    public static String getProcessName(Context cxt, int pid) {
        ActivityManager am = (ActivityManager) cxt.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
            if (procInfo.pid == pid) {
                return procInfo.processName;
            }
        }
        return null;
    }
}
