package com.sinoangel.ctrl.parentalcontrol.utils;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.sinoangel.ctrl.parentalcontrol.account.parent.bean.TokenBean;

/**
 * Created by Z on 2017/5/17.
 */

public class StaticObjects {
    private static TokenBean.DataBean uidb;

    public static TokenBean.DataBean getUidb() {
        if (uidb == null) {
            String json = SPUtils.getStringVar(Constant.TOKEN);
            if (!TextUtils.isEmpty(json))
                uidb = JSON.parseObject(json, TokenBean.DataBean.class);
        }
        return uidb;
    }

    public static void setUidb(TokenBean.DataBean uidb) {

        StaticObjects.uidb = uidb;

        if (uidb == null) {
            SPUtils.putStringVar(Constant.TOKEN, null);
        } else {
            String json = JSON.toJSONString(uidb);
            SPUtils.putStringVar(Constant.TOKEN, json);
        }

    }
}
