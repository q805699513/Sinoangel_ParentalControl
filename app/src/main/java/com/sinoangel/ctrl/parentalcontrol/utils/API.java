package com.sinoangel.ctrl.parentalcontrol.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Z on 2017/5/16.
 */

public class API {
    public static String API;
    public static String API2;

    static {
        switch (AppUtils.systemLanguageType()) {
            case 1:
                API = "http://cn.api.sinoangel.cn/";
                API2 = "http://api2.sinoangel.cn/";//商店
                break;
            default:
                API = "http://global.api.sinoangel.cn/";
                API2 = "http://global.api2.sinoangel.cn/";//商店
                break;
        }
    }
    public static final String NET_USER_LOGIN = API + "StoreUsers/login";//登录
    public static final String NET_USER_REGISTER = API + "StoreUsers/register";//注册
    public static final String NET_FINDPWDBYEMAIL = API2 + "api/StoreUsers/reset/";//密码找回
    public static final String NET_USER_STORE = API2 + "store";//商店

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

//    /**
//     * 用户登录
//     */
//    public static String userLogin(String api, Map<String, String> mss) {
//        return getURL(api, mss);
//    }

//    /**
//     * 用户注册
//     */
//    public static String userRegister(Map<String, String> mss) {
//        return getURL(NET_USER_REGISTER, mss);
//    }

    /**
     * 登录商店
     */
    public static String comeStore(String token) {
        Map<String, String> mss = new HashMap<>();
        mss.put("access_token", token);
        mss.put("channel_id", "");
        mss.put("lang", AppUtils.systemLanguageType() + "");
        mss.put("platfrom", "huawei");
        mss.put("location", "");
        return getURL(NET_USER_STORE, mss);
    }


}
