package com.sinoangel.ctrl.parentalcontrol.index.bean;

import java.util.List;

/**
 * Created by Z on 2017/2/10.
 */

public class HelpBean {

    /**
     * flag : 1
     * error : []
     * data : ["http://api2.sinoangel.cn/icons/help/1/hh1.png","http://api2.sinoangel.cn/icons/help/1/hh2.png","http://api2.sinoangel.cn/icons/help/1/hh3.png","http://api2.sinoangel.cn/icons/help/1/hh4.png","http://api2.sinoangel.cn/icons/help/1/hh5.png","http://api2.sinoangel.cn/icons/help/1/hh6.png","http://api2.sinoangel.cn/icons/help/1/hh7.png"]
     */

    private int flag;
    private List<?> error;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
