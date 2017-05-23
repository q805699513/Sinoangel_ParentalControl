package com.sinoangel.ctrl.parentalcontrol.utils;

import android.content.SharedPreferences;

import com.sinoangel.ctrl.parentalcontrol.base.BaseApplication;

public class SPUtils {

    private final static String SP_SINOANGEL = "spsinoangel";
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;


    public static void putPhoneId(String name) {
        preferences = BaseApplication.getInstance().getSharedPreferences(SP_SINOANGEL, 0);
        editor = preferences.edit();
        editor.putString("PID", name);
        editor.apply();
    }

    public static String getPhoneId() {
        preferences = BaseApplication.getInstance().getSharedPreferences(SP_SINOANGEL, 0);
        return preferences.getString("PID", "860846030000447");
    }


    public static void putIsExists(String name) {
        preferences = BaseApplication.getInstance().getSharedPreferences(SP_SINOANGEL, 0);
        editor = preferences.edit();
        editor.putBoolean(name, false);
        editor.commit();
    }

    public static boolean getIsExists(String name) {
        preferences = BaseApplication.getInstance().getSharedPreferences(SP_SINOANGEL, 0);
        return preferences.getBoolean(name, true);
    }


    public static String getStringVar(String name) {
        preferences = BaseApplication.getInstance().getSharedPreferences(SP_SINOANGEL, 0);
        return preferences.getString(name, null);
    }

    public static void putStringVar(String name, String var) {
        preferences = BaseApplication.getInstance().getSharedPreferences(SP_SINOANGEL, 0);
        editor = preferences.edit();
        editor.putString(name, var);
        editor.commit();
    }

    public static int getIntVar(String name) {
        preferences = BaseApplication.getInstance().getSharedPreferences(SP_SINOANGEL, 0);
        return preferences.getInt(name, 0);
    }

    public static void putIntVar(String name, int var) {
        preferences = BaseApplication.getInstance().getSharedPreferences(SP_SINOANGEL, 0);
        editor = preferences.edit();
        editor.putInt(name, var);
        editor.commit();
    }
}
