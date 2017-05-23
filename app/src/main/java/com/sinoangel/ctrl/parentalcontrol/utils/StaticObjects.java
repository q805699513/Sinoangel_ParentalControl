package com.sinoangel.ctrl.parentalcontrol.utils;

import com.alibaba.fastjson.JSON;
import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.account.bean.UserInfo;

/**
 * Created by Z on 2017/5/17.
 */

public class StaticObjects {
    private static UserInfo.DataBean uidb;

    public static UserInfo.DataBean getUidb() {
        if (uidb == null) {
            String json = SPUtils.getStringVar(Constant.USERBEAN);
            uidb = JSON.parseObject(json, UserInfo.DataBean.class);
        }
        return uidb;
    }

    public static void setUidb(UserInfo.DataBean uidb) {
        StaticObjects.uidb = uidb;
        String json = JSON.toJSONString(uidb);
        SPUtils.putStringVar(Constant.USERBEAN, json);
    }

    public static final int[] HeadIdList = {
            R.mipmap.parentheader1,
            R.mipmap.parentheader2,
            R.mipmap.parentheader3,
            R.mipmap.parentheader4,
            R.mipmap.parentheader5,
            R.mipmap.parentheader6};
}
