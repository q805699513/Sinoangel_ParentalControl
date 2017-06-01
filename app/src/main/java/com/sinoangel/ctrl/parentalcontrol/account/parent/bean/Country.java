package com.sinoangel.ctrl.parentalcontrol.account.parent.bean;

import java.util.List;

/**
 * Created by pc on 2016/5/20.
 */
public class Country {
    /**
     * flag : 1
     * error : []
     * data : [{"id":"2","country":"Algeria"},{"id":"17","country":"Benin"},{"id":"26","country":"Burkina Faso"},{"id":"31","country":"Chad"},{"id":"33","country":"China"},{"id":"34","country":"Colombia"},{"id":"49","country":"Gambia"},{"id":"51","country":"Ghana"},{"id":"55","country":"Guinea"},{"id":"61","country":"India"},{"id":"70","country":"Kenya"},{"id":"76","country":"Liberia"},{"id":"82","country":"Malawi"},{"id":"84","country":"Mali"},{"id":"86","country":"Mauritania"},{"id":"87","country":"Mauritius"},{"id":"93","country":"Mozambique"},{"id":"99","country":"Niger"},{"id":"100","country":"Nigeria"},{"id":"115","country":"Saudi Arabia"},{"id":"116","country":"Senegal"},{"id":"118","country":"Sierra Leone"},{"id":"136","country":"Tanzania"},{"id":"139","country":"Tunis"},{"id":"143","country":"Uganda"},{"id":"145","country":"United Arab Emirates"},{"id":"153","country":"Zimbabwe"},{"id":"154","country":"Burundi"},{"id":"155","country":"Egypt"},{"id":"156","country":"Ethiopia"},{"id":"157","country":"Sudan"},{"id":"158","country":"Togo"},{"id":"159","country":"Republic of Congo"},{"id":"160","country":"Congo-Kinshasa"},{"id":"161","country":"Gabon"},{"id":"162","country":"Cameroon"},{"id":"163","country":"Cote d'Ivoire"},{"id":"164","country":"Kigali"},{"id":"165","country":"Somalia"},{"id":"166","country":"South Sudan"},{"id":"167","country":"Zambia"}]
     */

    private int flag;
    private List<?> error;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 2
         * country : Algeria
         */

        private String id;
        private String country;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }
}
