package com.sinoangel.ctrl.parentalcontrol.bean;

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
        private int unq;
        private String id;
        private int ttl;
        private String created;
        private int userId;
        private String platFrom;
        private String userName;


        public int getUnq() {
            return unq;
        }

        public void setUnq(int unq) {
            this.unq = unq;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getTtl() {
            return ttl;
        }

        public void setTtl(int ttl) {
            this.ttl = ttl;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getPlatFrom() {
            return platFrom;
        }

        public void setPlatFrom(String platFrom) {
            this.platFrom = platFrom;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }
    }
}
