package com.sinoangel.ctrl.parentalcontrol.index;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.base.NetBean;
import com.sinoangel.ctrl.parentalcontrol.utils.API;
import com.sinoangel.ctrl.parentalcontrol.utils.AppUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.HttpUtil;

public class FeedbackActivity extends BaseActivity implements View.OnClickListener {

    private View btn_send, iv_back;
    private EditText tv_context;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RESULT_CANCELED:
                    AppUtils.showToast(getString(R.string.tag_net_request_error));
                    break;
                case RESULT_OK:
                    AppUtils.showToast(getString(R.string.tag_net_request_success));
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        btn_send = findViewById(R.id.btn_send);
        iv_back = findViewById(R.id.iv_back);
        tv_context = (EditText) findViewById(R.id.tv_context);

        iv_back.setOnClickListener(this);
        btn_send.setOnClickListener(this);

        BtnAnmiUtils btnAnmiUtils = new BtnAnmiUtils();
        btnAnmiUtils.setBtnAnmi(iv_back);
        btnAnmiUtils.setBtnAnmi(btn_send);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_send:
                String context = tv_context.getText().toString().trim();
                if (TextUtils.isEmpty(context))
                    AppUtils.showToast(getString(R.string.feedback_context_null));
                else
                    commint(context);
                break;
        }
    }

    public void commint(String context) {


        HttpUtil.getUtils().getJsonString(API.commitFeedback(context), new HttpUtil.OnNetResponseListener() {
            @Override
            public void onNetFail() {
                handler.sendEmptyMessage(RESULT_CANCELED);
            }

            @Override
            public void onNetSucceed(String json) {
                try {
                    NetBean netBean = JSON.parseObject(json, NetBean.class);
                    if (netBean.getFlag() == 1)
                        handler.sendEmptyMessage(RESULT_OK);
                    else
                        handler.sendEmptyMessage(RESULT_CANCELED);
                } catch (Exception e) {
                    handler.sendEmptyMessage(RESULT_CANCELED);
                }
            }
        });
    }
}
