package com.sinoangel.ctrl.parentalcontrol.base;

import java.util.List;

/**
 * Created by Z on 2017/5/27.
 */

public class NetBean {

    /**
     * flag : 1
     * error : []
     * data : set userInfo is success.
     */

    private int flag;
    private List<?> error;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public List<?> getError() {
        return error;
    }

    public void setError(List<?> error) {
        this.error = error;
    }
}
