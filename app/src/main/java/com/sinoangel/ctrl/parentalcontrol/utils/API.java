package com.sinoangel.ctrl.parentalcontrol.utils;

import com.sinoangel.ctrl.parentalcontrol.account.parent.bean.TokenBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Z on 2017/5/16.
 */

public class API {
//    public static final String API = "http://global.api.sinoangel.cn/";
    public static final String API = "http://cn.api.sinoangel.cn/";
//    public static final String API2 = "http://shop.sinoangel.cn/";//商店

    public static final String NET_USER_STORE = "http://shop.sinoangel.cn/home";//商店

//    public static final String NET_USER_STORE = "http://api2.sinoangel.cn/store";//商店 store
//    public static final String NET_USER_STORE = "http://global.api2.sinoangel.cn/store";//商店 store

    public static final String NET_USER_LOGIN = API + "StoreUsers/login";//登录
    public static final String NET_USER_REGISTER = API + "StoreUsers/register";//注册
    public static final String NET_FINDPWDBYEMAIL = API + "api/StoreUsers/reset/";//密码找回
    public static final String NET_USER_INFO = API + "common/getUser";//查询用户资料
    public static final String NET_USER_BALANCE = API + "common/GetUserAccount";//查询用户余额
    public static final String NET_USER_INFO_UPDATE = API + "common/SetParentInfo";//更新用户资料
    public static final String NET_APP_COUNTRY = API + "storecountry/list";//国家列表接口lang=6

    public static final String NET_FINDKIDACTIONS = API + "parents/quire";//儿童行为列表
    public static final String NET_FINDKIDKIDS = API + "common/getUserChildInfo";//儿童列表
    public static final String NET_DELETEKIDS = API + "common/delUserChildInfo";//删除儿童
    public static final String NET_UPDATEKIDS = API + "common/SetUserChildInfo";//修改儿童
    public static final String NET_ADDKIDS = API + "common/SetUserChildInfo";//添加儿童

    public static final String NET_WEB_LIST = API + "common/GetGreennetInfo";//获取网址列表
    public static final String NET_WEB_UPDATE = API + "common/SaveGreennetInfo";//修改网址
    public static final String NET_WEB_ADD = API + "common/addGreennetInfo";//添加网址
    public static final String NET_WEB_DELETE = API + "common/DelGreennetInfo";//删除网址

    public static final String NET_LIMIT = API + "common/SetUserChildBuyLimit";//更新消费保护

    public static final String NET_EYESHIELD = API + "common/setProtectEyes";//更新护眼设置

    public static final String NET_FEEDBACK = API + "common/setfeedack";//提交反馈

    public static final String NET_HELP = API + "Help";//帮助图片


    public static String getURL(String api, Map<String, String> hmss) {
        Set<String> ss = hmss.keySet();
        StringBuilder sb = new StringBuilder("?");
        for (String str : ss) {
            sb.append(str);
            sb.append("=");
            sb.append(hmss.get(str));
            sb.append("&");
        }
        sb.deleteCharAt(sb.length() - 1);
        String url = api + sb.toString();
        AppUtils.outputLog(url);
        return url;
    }

    /**
     * 用户登录
     */
    public static String userLogin(Map<String, String> mss) {
        return getURL(NET_USER_LOGIN, mss);
    }

    /**
     * 用户注册
     */
    public static String userRegister(Map<String, String> mss) {
        return getURL(NET_USER_REGISTER, mss);
    }

    /**
     * 获取用户资料
     */
    public static String getUserInfo() {
        Map<String, String> mss = new HashMap<>();
        TokenBean.DataBean uidb = StaticObjects.getUidb();
        if (uidb != null)
            mss.put("userId", uidb.getUserId());
        mss.put("langId", AppUtils.systemLanguageType() + "");
        return getURL(NET_USER_INFO, mss);
    }

    /**
     * 更新用户资料
     */
    public static String upDateUserInfo(Map<String, String> mss) {
        return getURL(NET_USER_INFO_UPDATE, mss);
    }

    /**
     * 登录商店
     */
    public static String comeStore(String token) {
        Map<String, String> mss = new HashMap<>();
        mss.put("access_token", token);
        mss.put("channel_id", Constant.CHANNEL);
        mss.put("lang", AppUtils.systemLanguageType() + "");
//        mss.put("platfrom", "huawei");
        mss.put("location", "2");//2代表国内
        mss.put("parent", "1");
        return getURL(NET_USER_STORE, mss);
    }

    /**
     * 获取儿童列表
     */
    public static String getKidsList() {
        Map<String, String> mss = new HashMap<>();
        TokenBean.DataBean uidb = StaticObjects.getUidb();
        if (uidb != null)
            mss.put("userId", uidb.getUserId());
        return getURL(NET_FINDKIDKIDS, mss);
    }

    /**
     * 修改儿童
     */
    public static String updateKid(Map<String, String> mss) {
        return getURL(NET_UPDATEKIDS, mss);
    }

    /**
     * 添加儿童
     */
    public static String addKid(Map<String, String> mss) {
        return getURL(NET_ADDKIDS, mss);
    }

    /**
     * 删除儿童
     */
    public static String deleteKid(String kidId) {
        Map<String, String> mss = new HashMap<>();
        mss.put("childId", kidId);
        mss.put("child", "2");
        return getURL(NET_DELETEKIDS, mss);
    }

    /**
     * 获取儿童行为列表
     */
    public static String getKidsActions(String uid, String time) {
        Map<String, String> mss = new HashMap<>();
        mss.put("userid", uid);
        mss.put("time", time);
        return getURL(NET_FINDKIDACTIONS, mss);
    }

    /**
     * 关于图片
     */
    public static String getHelpImg() {
        Map<String, String> mss = new HashMap<>();
        mss.put("lang", AppUtils.systemLanguageType() + "");
        mss.put("channel", Constant.CHANNEL);
        return getURL(NET_HELP, mss);
    }

    /**
     * 获取国家列表
     */
    public static String getCountryList() {
        Map<String, String> mss = new HashMap<>();
        mss.put("lang_id", AppUtils.systemLanguageType() + "");
        mss.put("list_code", "1");
        return getURL(NET_APP_COUNTRY, mss);
    }

    /**
     * 获取网址列表
     */
    public static String getWebList() {
        Map<String, String> mss = new HashMap<>();
        TokenBean.DataBean uidb = StaticObjects.getUidb();
        if (uidb != null)
            mss.put("userId", uidb.getUserId());
        return getURL(NET_WEB_LIST, mss);
    }

    /**
     * 添加网址
     */
    public static String addWebPath(String name, String url) {
        Map<String, String> mss = new HashMap<>();
        TokenBean.DataBean uidb = StaticObjects.getUidb();
        if (uidb != null)
            mss.put("userId", uidb.getUserId());
        mss.put("website_name", name);
        mss.put("website_url", url);
        return getURL(NET_WEB_ADD, mss);
    }

    /**
     * 删除网址
     */
    public static String deleteWebPath(String id) {
        Map<String, String> mss = new HashMap<>();
        mss.put("greennetId", id);
        mss.put("child", "2");
        return getURL(NET_WEB_DELETE, mss);
    }

    /**
     * 修改网址
     */
    public static String updateWebPath(String id, String name, String url) {
        Map<String, String> mss = new HashMap<>();
        mss.put("greennetId", id);
        mss.put("website_name", name);
        mss.put("website_url", url);
        mss.put("child", "2");
        return getURL(NET_WEB_UPDATE, mss);
    }

    /**
     * 查询消费保护等
     */
    public static String getBalance() {
        Map<String, String> mss = new HashMap<>();
        TokenBean.DataBean uidb = StaticObjects.getUidb();
        if (uidb != null)
            mss.put("userId", uidb.getUserId());
        return getURL(NET_USER_BALANCE, mss);
    }

    /**
     * 更新消费保护
     */
    public static String updateLimit(String sinocoin, String day, String isPay) {
        Map<String, String> mss = new HashMap<>();
        TokenBean.DataBean uidb = StaticObjects.getUidb();
        if (uidb != null)
            mss.put("userId", uidb.getUserId());
        mss.put("payment_sinocoin_limit", sinocoin);
        mss.put("payment_cycle_day", day);
        mss.put("allow_child_buy", isPay);
        return getURL(NET_LIMIT, mss);
    }

    /**
     * 提交反馈
     */
    public static String commitFeedback(String feedback) {
        Map<String, String> mss = new HashMap<>();
        TokenBean.DataBean uidb = StaticObjects.getUidb();
        if (uidb != null)
            mss.put("userId", uidb.getUserId());
        mss.put("email", "a");
        mss.put("feedback", feedback);
        return getURL(NET_FEEDBACK, mss);
    }

    /**
     * 更护眼设置
     */
    public static String updateEyeshieldSetting(String count, String once, String wait) {
        Map<String, String> mss = new HashMap<>();
        TokenBean.DataBean uidb = StaticObjects.getUidb();
        if (uidb != null)
            mss.put("userId", uidb.getUserId());
        mss.put("day_total_useage_time", count);
        mss.put("each_usage_time", once);
        mss.put("break_time", wait);
        return getURL(NET_EYESHIELD, mss);
    }
}
