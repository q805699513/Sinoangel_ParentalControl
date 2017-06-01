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
import com.sinoangel.ctrl.parentalcontrol.index.bean.WebBean;
import com.sinoangel.ctrl.parentalcontrol.utils.API;
import com.sinoangel.ctrl.parentalcontrol.utils.AppUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.Constant;
import com.sinoangel.ctrl.parentalcontrol.utils.DialogUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.HttpUtil;

public class AddURLActivity extends BaseActivity implements View.OnClickListener {

    private View btn_save, iv_back;
    private EditText et_url_name, et_url_path;

    private WebBean.DataBean dataBean;

    private static final String WEB_HTTP = "http://";
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RESULT_CANCELED:
                    AppUtils.showToast(getString(R.string.tag_net_request_error));
                    break;
                case RESULT_OK:
                    setResult(Constant.RESULT_SUCCESS_UPDATE);
                    finish();
                    break;
            }
            DialogUtils.dismissProgressDialog();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_url);

        iv_back = findViewById(R.id.iv_back);
        btn_save = findViewById(R.id.btn_save);
        et_url_name = (EditText) findViewById(R.id.et_url_name);
        et_url_path = (EditText) findViewById(R.id.et_url_path);

        iv_back.setOnClickListener(this);
        btn_save.setOnClickListener(this);

        BtnAnmiUtils btnAnmiUtils = new BtnAnmiUtils();
        btnAnmiUtils.setBtnAnmi(iv_back);
        btnAnmiUtils.setBtnAnmi(btn_save);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            dataBean = (WebBean.DataBean) bundle.get(Constant.WEBBEAN);
        if (dataBean != null) {
            et_url_name.setText(dataBean.getWebsite_name());
            et_url_path.setText(dataBean.getWebsite_url());
        } else
            et_url_path.setText(WEB_HTTP);
    }

    private void addWebPath(String name, String url) {

        HttpUtil.getUtils().getJsonString(API.addWebPath(name, url), new HttpUtil.OnNetResponseListener() {
            @Override
            public void onNetFail() {
                handler.sendEmptyMessage(RESULT_CANCELED);
            }

            @Override
            public void onNetSucceed(String json) {
                try {
                    NetBean netBean = JSON.parseObject(json, NetBean.class);
                    if (netBean.getFlag() == 1) {
                        handler.sendEmptyMessage(RESULT_OK);
                    } else handler.sendEmptyMessage(RESULT_CANCELED);
                } catch (Exception e) {
                    handler.sendEmptyMessage(RESULT_CANCELED);
                }
            }
        });
    }

    private void upDateWebPath(String id, String name, String url) {

        HttpUtil.getUtils().getJsonString(API.updateWebPath(id, name, url), new HttpUtil.OnNetResponseListener() {
            @Override
            public void onNetFail() {
                handler.sendEmptyMessage(RESULT_CANCELED);
            }

            @Override
            public void onNetSucceed(String json) {
                try {
                    NetBean netBean = JSON.parseObject(json, NetBean.class);
                    if (netBean.getFlag() == 1) {
                        handler.sendEmptyMessage(RESULT_OK);
                    } else handler.sendEmptyMessage(RESULT_CANCELED);
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
            case R.id.btn_save:
                String name = et_url_name.getText().toString().trim();
                String url = et_url_path.getText().toString().trim();

                if (TextUtils.isEmpty(name))
                    AppUtils.showToast(getString(R.string.net_url_name_null));
                else if (TextUtils.isEmpty(url)) {
                    AppUtils.showToast(getString(R.string.net_url_path_null));
                } else if (!AppUtils.isFormatUrl(url)) {
                    AppUtils.showToast(getString(R.string.net_url_path_error));
                } else if (dataBean != null) {
                    upDateWebPath(dataBean.getId(), name, url);
                } else {
                    addWebPath(name, url);
                }

                break;
        }
    }

}
