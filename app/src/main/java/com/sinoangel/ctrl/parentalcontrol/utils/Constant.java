package com.sinoangel.ctrl.parentalcontrol.utils;


import com.sinoangel.ctrl.parentalcontrol.R;

/**
 * 类使用使用：所有常量都储存在此类中
 */
public class Constant {

    /**
     * =====语言======
     */
    public static final int LANGUAGE_ZH = 1;// 中简体
    public static final int LANGUAGE_EN = 2;//英
    public static final int LANGUAGE_FR = 3;
    public static final int LANGUAGE_DE = 4;
    public static final int LANGUAGE_ES = 5;
    public static final int LANGUAGE_AR = 6;
    public static final int LANGUAGE_IT = 11;
    public static final int LANGUAGE_JA = 15;
    public static final int LANGUAGE_KO = 16;

    public static final int[] ParentHeadIdList = {
            R.mipmap.icon_parent_1,
            R.mipmap.icon_parent_2,
            R.mipmap.icon_parent_3,
            R.mipmap.icon_parent_4,
            R.mipmap.icon_parent_5,
            R.mipmap.icon_parent_6};

    public static final int[] KidHeadIdList = {
            R.mipmap.icon_kids_1,
            R.mipmap.icon_kids_2,
            R.mipmap.icon_kids_3,
            R.mipmap.icon_kids_4,
            R.mipmap.icon_kids_5,
            R.mipmap.icon_kids_6};

    public static final String ACTION_HEAD_UPDATE = "action_head_update";//刷新头像广播

    public static final int RESULT_SUCCESS_UPDATE = 100;//成功 刷新请求

    public static final String TOKEN = "token_id";//用户token
    public static final String USERBEAN = "user_info";//实体用户引用名
    public static final String HEADID = "user_head_id";//头像id
    public static final String WEBBEAN = "webbean";//网址类实体
    public static final String HEAD_FALGE = "head_flage";//头像标记

    public static final String GIRL = "0";//女
    public static final String BOY = "1";//男

    public static final String THEME1 = "0";//主题1
    public static final String THEME2 = "1";//主题2
    public static final String THEME3 = "2";//主题3


    public static final int HEAD_KIDS = 10;//儿童头像标记
    public static final int HEAD_PARENT = 20;//家长头像标记

    public static final String CHANNEL = "591c328c8630f5520e000dc8";//渠道号 注意xml里面也有一个
}
