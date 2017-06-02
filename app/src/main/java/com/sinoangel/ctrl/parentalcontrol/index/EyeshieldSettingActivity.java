package com.sinoangel.ctrl.parentalcontrol.index;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.base.NetBean;
import com.sinoangel.ctrl.parentalcontrol.index.bean.EyeBean;
import com.sinoangel.ctrl.parentalcontrol.utils.API;
import com.sinoangel.ctrl.parentalcontrol.utils.AppUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.DialogUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.HttpUtil;

import java.util.ArrayList;
import java.util.List;

public class EyeshieldSettingActivity extends BaseActivity implements View.OnClickListener {

    private View iv_back, iv_edit1, iv_edit2, iv_edit3, btn_save;
    private TextView iv_count_time, iv_once_time, iv_wait_time;

    private EyeBean.DataBean.UserBean eyebean;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RESULT_CANCELED:
                    AppUtils.showToast(getString(R.string.tag_net_request_error));
                    break;
                case RESULT_OK:
                    iv_count_time.setText(eyebean.getDay_total_useage_time());
                    iv_once_time.setText(eyebean.getEach_usage_time());
                    iv_wait_time.setText(eyebean.getBreak_time());
                    break;
                case RESULT_FIRST_USER:
                    AppUtils.showToast(getString(R.string.tag_net_request_success));
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eyeshield_setting);

        iv_back = findViewById(R.id.iv_back);
        btn_save = findViewById(R.id.btn_save);
        iv_count_time = (TextView) findViewById(R.id.iv_count_time);
        iv_once_time = (TextView) findViewById(R.id.iv_once_time);
        iv_wait_time = (TextView) findViewById(R.id.iv_wait_time);

        iv_edit1 = findViewById(R.id.iv_edit1);
        iv_edit2 = findViewById(R.id.iv_edit2);
        iv_edit3 = findViewById(R.id.iv_edit3);

        iv_back.setOnClickListener(this);
        btn_save.setOnClickListener(this);
        iv_edit1.setOnClickListener(this);
        iv_edit2.setOnClickListener(this);
        iv_edit3.setOnClickListener(this);
        iv_count_time.setOnClickListener(this);
        iv_once_time.setOnClickListener(this);
        iv_wait_time.setOnClickListener(this);

        BtnAnmiUtils btnAnmiUtils = new BtnAnmiUtils();
        btnAnmiUtils.setBtnAnmi(iv_back);
        btnAnmiUtils.setBtnAnmi(iv_count_time);
        btnAnmiUtils.setBtnAnmi(iv_once_time);
        btnAnmiUtils.setBtnAnmi(iv_wait_time);

        getData();
    }

    private void getData() {
        HttpUtil.getUtils().getJsonString(API.getUserInfo(), new HttpUtil.OnNetResponseListener() {
            @Override
            public void onNetFail() {
                handler.sendEmptyMessage(RESULT_CANCELED);
            }

            @Override
            public void onNetSucceed(String json) {
                try {
                    EyeBean eyeBean = JSON.parseObject(json, EyeBean.class);
                    if (eyeBean.getFlag() == 1) {
                        eyebean = eyeBean.getData().getUser();
                        if (eyeBean != null)
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
            case R.id.iv_edit1:
                final List<String> list = new ArrayList<>();
                list.add("1");
                list.add("2");
                list.add("3");
                list.add("5");
                list.add("8");
                list.add("10");
                list.add("12");
                DialogUtils.showTimeSelectItemDialog(EyeshieldSettingActivity.this, list, 0, R.string.unit_h, new DialogUtils.SelectItemDialogListener() {
                    @Override
                    public void onBtn_OK(int pos) {
                        iv_count_time.setText(list.get(pos));
                    }
                });
                break;
            case R.id.iv_edit2:
                final List<String> list1 = new ArrayList<>();
                list1.add("15");
                list1.add("30");
                list1.add("60");
                list1.add("120");
                DialogUtils.showTimeSelectItemDialog(EyeshieldSettingActivity.this, list1, 0, R.string.unit_m, new DialogUtils.SelectItemDialogListener() {
                    @Override
                    public void onBtn_OK(int pos) {
                        iv_once_time.setText(list1.get(pos));
                    }
                });
                break;
            case R.id.iv_edit3:
                final List<String> list2 = new ArrayList<>();
                list2.add("15");
                list2.add("30");
                list2.add("60");
                list2.add("120");
                DialogUtils.showTimeSelectItemDialog(EyeshieldSettingActivity.this, list2, 0, R.string.unit_m, new DialogUtils.SelectItemDialogListener() {
                    @Override
                    public void onBtn_OK(int pos) {
                        iv_wait_time.setText(list2.get(pos));
                    }
                });
                break;
            case R.id.btn_save:
                upDate();
                break;
        }
    }

    private void upDate() {

        String count = iv_count_time.getText().toString();
        String once = iv_once_time.getText().toString();
        String wait = iv_wait_time.getText().toString();

        HttpUtil.getUtils().getJsonString(API.updateEyeshieldSetting(count, once, wait), new HttpUtil.OnNetResponseListener() {
            @Override
            public void onNetFail() {
                handler.sendEmptyMessage(RESULT_CANCELED);
            }

            @Override
            public void onNetSucceed(String json) {
                try {
                    NetBean netBean = JSON.parseObject(json, NetBean.class);
                    if (netBean.getFlag() == 1) {
                        handler.sendEmptyMessage(RESULT_FIRST_USER);
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
}
