package com.sinoangel.ctrl.parentalcontrol.index;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.index.adapter.KidAccountAdapter;
import com.sinoangel.ctrl.parentalcontrol.account.kids.bean.KidBean;
import com.sinoangel.ctrl.parentalcontrol.utils.API;
import com.sinoangel.ctrl.parentalcontrol.utils.AppUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.HttpUtil;

import java.util.List;

public class KidAccountActivity extends BaseActivity implements View.OnClickListener {

    private View iv_back;
    private RecyclerView rv_list;
    private KidAccountAdapter kidAccountAdapter;


    private List<KidBean.DataBean> dataBeanList;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RESULT_CANCELED:
                    AppUtils.showToast(getString(R.string.tag_net_request_error));
                    break;
                case RESULT_OK:
                    kidAccountAdapter.setData(dataBeanList);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_account);

        rv_list = (RecyclerView) findViewById(R.id.rv_list);
        iv_back = findViewById(R.id.iv_back);

        RecyclerView.LayoutManager rvlm = new LinearLayoutManager(KidAccountActivity.this, LinearLayoutManager.VERTICAL, false);
        rv_list.setLayoutManager(rvlm);

        kidAccountAdapter = new KidAccountAdapter(this);
        rv_list.setAdapter(kidAccountAdapter);

        iv_back.setOnClickListener(this);

        BtnAnmiUtils btnAnmiUtils = new BtnAnmiUtils();
        btnAnmiUtils.setBtnAnmi(iv_back);

        getNetData();

    }

    private void getNetData() {
        HttpUtil.getUtils().getJsonString(API.getKidsList(), new HttpUtil.OnNetResponseListener() {
            @Override
            public void onNetFail() {
                handler.sendEmptyMessage(RESULT_CANCELED);
            }

            @Override
            public void onNetSucceed(String json) {
                try {
                    KidBean kidBean = JSON.parseObject(json, KidBean.class);
                    if (kidBean.getFlag() == 1) {

                        dataBeanList = kidBean.getData();
                        dataBeanList.add(new KidBean.DataBean());

                        handler.sendEmptyMessage(RESULT_OK);
                    } else
                        handler.sendEmptyMessage(RESULT_CANCELED);
                } catch (Exception e) {
                    handler.sendEmptyMessage(RESULT_CANCELED);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
