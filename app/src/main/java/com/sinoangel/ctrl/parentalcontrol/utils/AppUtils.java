package com.sinoangel.ctrl.parentalcontrol.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.sinoangel.ctrl.parentalcontrol.BuildConfig;
import com.sinoangel.ctrl.parentalcontrol.base.BaseApplication;

import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/8/24 0024.
 */
public class AppUtils {

    private static int wei, hei;


    public static int getWei() {
        if (wei == 0)
            wei = SPUtils.getIntVar("wei");
        return wei;
    }

    public static int getHei() {
        if (hei == 0)
            hei = SPUtils.getIntVar("hei");
        return hei;
    }

    public static void initWH(Context context) {
        wei = context.getResources().getDisplayMetrics().widthPixels;
        hei = context.getResources().getDisplayMetrics().heightPixels;
        SPUtils.putIntVar("wei", wei);
        SPUtils.putIntVar("hei", hei);
    }

    public static void showToast(String word) {
        Toast toast = Toast.makeText(BaseApplication.getInstance(), word, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * @param email 邮箱地址
     */
    public static boolean isFormatEmail(String email) {

        if (hasEmoji(email))
            return false;

        final String pattern1 = "[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?";
        //      /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(email);
        return mat.find();
    }

    /**
     * @param name 用户名
     */
    public static boolean isFormatUser(String name) {
        if (hasEmoji(name))
            return false;
        final String pattern1 = "^.{1,18}$";
        //
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(name);
        return mat.find();
    }

    /**
     * @param url 网址
     */

    public static boolean isFormatUrl(String url) {
        final String pattern1 = "^((https|http|ftp|rtsp|mms)?:\\/\\/)[^\\s]+";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(url);
        return mat.find();
    }


    public static void outputLog(String str) {
        if (BuildConfig.DEBUG)
            if (str != null)
                Log.e("dd", str);
    }


    /**
     * 判断系统的语言
     */
    public static int systemLanguageType() {
        Locale l = Locale.getDefault();
        String language = l.getLanguage();
        if (TextUtils.isEmpty(language)) {
            return Constant.LANGUAGE_EN;
        } else if (language.trim().contains("en")) {//英文
            return Constant.LANGUAGE_EN;
        } else if (language.trim().contains("fr")) {
            return Constant.LANGUAGE_FR;
        } else if (language.trim().contains("zh")) {//中文简体、繁体
            return Constant.LANGUAGE_ZH;
        } else if (language.trim().contains("ar")) {
            return Constant.LANGUAGE_AR;
        } else if (language.trim().contains("de")) {
            return Constant.LANGUAGE_DE;
        } else if (language.trim().contains("es")) {
            return Constant.LANGUAGE_ES;
        } else if (language.trim().contains("it")) {
            return Constant.LANGUAGE_IT;
        } else if (language.trim().contains("ja")) {
            return Constant.LANGUAGE_JA;
        } else if (language.trim().contains("ko")) {
            return Constant.LANGUAGE_KO;
        }
        return Constant.LANGUAGE_EN;
    }

    public static boolean hasEmoji(String content) {

        Pattern pattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    private static long starTime;//开始时间

    public static void recordTime() {
        starTime = new Date().getTime();
    }

    public static void outTime(String name) {
        long val = new Date().getTime() - starTime;
        outputLog(name + "---" + val);
    }

}
