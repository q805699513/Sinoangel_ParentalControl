package com.sinoangel.ctrl.parentalcontrol.index;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.account.parent.bean.TokenBean;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.index.adapter.ActionStatisticsAdapter;
import com.sinoangel.ctrl.parentalcontrol.utils.API;
import com.sinoangel.ctrl.parentalcontrol.utils.HttpUtil;
import com.sinoangel.ctrl.parentalcontrol.utils.StaticObjects;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionStatisticsActivity extends BaseActivity {
    private ActionStatisticsAdapter actionStatisticsAdapter;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RESULT_CANCELED:
                    break;
                case RESULT_OK:
                    actionStatisticsAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_statistics);

        RecyclerView rv_list = (RecyclerView) findViewById(R.id.rv_list);
        actionStatisticsAdapter = new ActionStatisticsAdapter();
        rv_list.setAdapter(actionStatisticsAdapter);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = sdf.format(new Date());
        getData(time);
    }

    private void getData(String time) {

        TokenBean.DataBean uidb = StaticObjects.getUidb();
        if (uidb != null)
            HttpUtil.getUtils().getJsonString(API.getKidsActions(uidb.getUserId(), time), new HttpUtil.OnNetResponseListener() {
                @Override
                public void onNetFail() {
                    handler.sendEmptyMessage(RESULT_CANCELED);
                }

                @Override
                public void onNetSucceed(String json) {
                    handler.sendEmptyMessage(RESULT_OK);
                }
            });
    }
}
