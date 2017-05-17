package com.sinoangel.ctrl.parentalcontrol.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.sinoangel.ctrl.parentalcontrol.BuildConfig;
import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.base.BaseApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/8/24 0024.
 */
public class AppUtils {

    public static void showToast(String word) {
        Toast toast = Toast.makeText(BaseApplication.getInstance(), word, Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * 验证邮箱地址
     *
     * @param email 邮箱地址
     */
    public static boolean isFormatEmail(String email) {

        if (hasEmoji(email))
            return false;

        final String pattern1 = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
        //      /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(email);
        return mat.find();
    }
//


    /**
     * 验证邮箱地址
     *
     * @param name 用户名
     */
    public static boolean isFormatUser(String name) {
        if (hasEmoji(name))
            return false;
        final String pattern1 = "^{1,15}$";
        //
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(name);
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


    private static View.OnTouchListener vot = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            //设置Animation
            Animation animDwon = AnimationUtils.loadAnimation(BaseApplication.getInstance(), R.anim.btn_down);
            Animation animUp = AnimationUtils.loadAnimation(BaseApplication.getInstance(), R.anim.btn_up);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.startAnimation(animDwon);
                    animDwon.setFillAfter(true);
                    break;

                case MotionEvent.ACTION_UP:
                    v.startAnimation(animUp);
                    animUp.setFillAfter(true);
                    break;
            }
            return false;    //这时必须返回true，不然 MotionEvent.ACTION_UP 没效果
        }
    };


    public static void setBtnAnmi(View v) {
        v.setOnTouchListener(vot);
    }

    public static boolean hasEmoji(String content) {

        Pattern pattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            return true;
        }
        return false;
    }
}
