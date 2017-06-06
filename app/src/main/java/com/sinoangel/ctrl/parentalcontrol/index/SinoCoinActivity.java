package com.sinoangel.ctrl.parentalcontrol.index;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.index.bean.LimitBean;
import com.sinoangel.ctrl.parentalcontrol.utils.API;
import com.sinoangel.ctrl.parentalcontrol.utils.AppUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.DialogUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.HttpUtil;

public class SinoCoinActivity extends BaseActivity implements View.OnClickListener {

    private View iv_back, btn_coin1, btn_coin2, btn_coin3, btn_coin4;
    private TextView tv_yue;
    private LimitBean.DataBean limitBean;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            DialogUtils.dismissProgressDialog();
            switch (msg.what) {
                case RESULT_CANCELED:
                    AppUtils.showToast(getString(R.string.tag_net_request_error));
                    break;
                case RESULT_OK:
                    tv_yue.setText(limitBean.getUser_balance());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinocoin);

        iv_back = findViewById(R.id.iv_back);
        btn_coin1 = findViewById(R.id.btn_coin1);
        btn_coin2 = findViewById(R.id.btn_coin2);
        btn_coin3 = findViewById(R.id.btn_coin3);
        btn_coin4 = findViewById(R.id.btn_coin4);
        tv_yue = (TextView) findViewById(R.id.tv_yue);


        iv_back.setOnClickListener(this);
        btn_coin1.setOnClickListener(this);
        btn_coin2.setOnClickListener(this);
        btn_coin3.setOnClickListener(this);
        btn_coin4.setOnClickListener(this);

        BtnAnmiUtils btnAnmiUtils = new BtnAnmiUtils();
        btnAnmiUtils.setBtnAnmi(iv_back);
        btnAnmiUtils.setBtnAnmi(btn_coin1);
        btnAnmiUtils.setBtnAnmi(btn_coin2);
        btnAnmiUtils.setBtnAnmi(btn_coin3);
        btnAnmiUtils.setBtnAnmi(btn_coin4);

        getData();

    }

    private void getData() {
        DialogUtils.showProgressDialog(SinoCoinActivity.this, true);

        HttpUtil.getUtils().getJsonString(API.getBalance(), new HttpUtil.OnNetResponseListener() {
            @Override
            public void onNetFail() {
                handler.sendEmptyMessage(RESULT_CANCELED);
            }

            @Override
            public void onNetSucceed(String json) {

                try {
                    LimitBean lb = JSON.parseObject(json, LimitBean.class);

                    if (lb.getFlag() == 1) {
                        limitBean = lb.getData();
                        if (limitBean != null) {
                            handler.sendEmptyMessage(RESULT_OK);
                        } else
                            handler.sendEmptyMessage(RESULT_CANCELED);
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
        Intent intent;
        switch (v.getId()) {

            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_coin1:
                intent = new Intent(SinoCoinActivity.this, PayActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_coin2:
                intent = new Intent(SinoCoinActivity.this, PayActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_coin3:
                intent = new Intent(SinoCoinActivity.this, PayActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_coin4:
                intent = new Intent(SinoCoinActivity.this, PayActivity.class);
                startActivity(intent);
                break;
        }
    }
}
