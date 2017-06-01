package com.sinoangel.ctrl.parentalcontrol.index;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.index.adapter.InternetManagerAdapter;
import com.sinoangel.ctrl.parentalcontrol.index.bean.WebBean;
import com.sinoangel.ctrl.parentalcontrol.utils.API;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.Constant;
import com.sinoangel.ctrl.parentalcontrol.utils.DialogUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.HttpUtil;

import java.util.List;

public class InternetManagerActivity extends BaseActivity implements View.OnClickListener {
    private View iv_back, rl_box;
    private RecyclerView rv_list;
    private InternetManagerAdapter internetManagerAdapter;

    private List<WebBean.DataBean> dataBeanList;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RESULT_CANCELED:
                    break;
                case RESULT_OK:
                    internetManagerAdapter.setData(dataBeanList);
                    break;
            }
            DialogUtils.dismissProgressDialog();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_manager);

        iv_back = findViewById(R.id.iv_back);
        rl_box = findViewById(R.id.rl_box);
        rv_list = (RecyclerView) findViewById(R.id.rv_list);

        iv_back.setOnClickListener(this);
        rl_box.setOnClickListener(this);

        BtnAnmiUtils btnAnmiUtils = new BtnAnmiUtils();
        btnAnmiUtils.setBtnAnmi(iv_back);
        btnAnmiUtils.setBtnAnmi(rl_box);

        RecyclerView.LayoutManager rvlm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_list.setLayoutManager(rvlm);

        internetManagerAdapter = new InternetManagerAdapter(this);
        rv_list.setAdapter(internetManagerAdapter);

        getNetData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_box:
                startActivityForResult(new Intent(InternetManagerActivity.this, AddURLActivity.class), 0);
                break;
        }
    }

    public void getNetData() {
        DialogUtils.showProgressDialog(this, true);
        HttpUtil.getUtils().getJsonString(API.getWebList(), new HttpUtil.OnNetResponseListener() {
            @Override
            public void onNetFail() {
                handler.sendEmptyMessage(RESULT_CANCELED);
            }

            @Override
            public void onNetSucceed(String json) {
                try {
                    WebBean webBean = JSON.parseObject(json, WebBean.class);
                    if (webBean.getFlag() == 1) {
                        dataBeanList =webBean.getData();
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
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Constant.RESULT_SUCCESS_UPDATE)
            getNetData();
    }
}
