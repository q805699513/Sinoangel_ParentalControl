package com.sinoangel.ctrl.parentalcontrol.account.bean;

/**
 * Created by Administrator on 2016/7/27 0027.
 */
public class UserInfo {

    /**
     * flag : 1
     * error : []
     * data : {"id":"CtFnGtBaDyBZTa40S8T6Ej5XUB1uDvjcKs4mEtLr1TXkuvxm1Ryfjq7LSCeQVLOC","ttl":31556926,"created":"2016-07-27T11:00:10.195Z","userId":47,"platFrom":"self","userName":"wtest"}
     */

    private int flag;
    /**
     * id : CtFnGtBaDyBZTa40S8T6Ej5XUB1uDvjcKs4mEtLr1TXkuvxm1Ryfjq7LSCeQVLOC
     * ttl : 31556926
     * created : 2016-07-27T11:00:10.195Z
     * userId : 47
     * platFrom : self
     * userName : wtest
     */

    private DataBean data;
    private String error;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static class DataBean {
        private String id;
        private int userId;
        private String userName;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
