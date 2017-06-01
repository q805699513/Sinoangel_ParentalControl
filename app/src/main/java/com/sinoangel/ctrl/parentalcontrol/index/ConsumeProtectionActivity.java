package com.sinoangel.ctrl.parentalcontrol.index;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.account.parent.ParentAccountActivity;
import com.sinoangel.ctrl.parentalcontrol.account.parent.bean.ParentBean;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.base.NetBean;
import com.sinoangel.ctrl.parentalcontrol.index.bean.LimitBean;
import com.sinoangel.ctrl.parentalcontrol.utils.API;
import com.sinoangel.ctrl.parentalcontrol.utils.AppUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.DialogUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.HttpUtil;

import java.util.ArrayList;
import java.util.List;

public class ConsumeProtectionActivity extends BaseActivity implements View.OnClickListener {

    private View iv_back, iv_scoin, iv_term, btn_save;
    private CheckBox cb_allow_pay;
    private TextView tv_sino_coin, tv_term;

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
                    cb_allow_pay.setChecked(TextUtils.equals("1", limitBean.getAllow_child_buy()));
                    tv_sino_coin.setText(limitBean.getPayment_sinocoin_limit());
                    tv_term.setText(limitBean.getPayment_cycle_day());
                    break;
                case RESULT_FIRST_USER:
                    AppUtils.showToast(getString(R.string.tag_net_request_success));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumer_protection);
        iv_back = findViewById(R.id.iv_back);
        iv_scoin = findViewById(R.id.iv_scoin);
        iv_term = findViewById(R.id.iv_term);
        btn_save = findViewById(R.id.btn_save);
        cb_allow_pay = (CheckBox) findViewById(R.id.cb_allow_pay);
        tv_sino_coin = (TextView) findViewById(R.id.tv_sino_coin);
        tv_term = (TextView) findViewById(R.id.tv_term);


        iv_back.setOnClickListener(this);
        iv_scoin.setOnClickListener(this);
        iv_term.setOnClickListener(this);
        btn_save.setOnClickListener(this);

        BtnAnmiUtils btnAnmiUtils = new BtnAnmiUtils();
        btnAnmiUtils.setBtnAnmi(iv_back);
        btnAnmiUtils.setBtnAnmi(iv_scoin);
        btnAnmiUtils.setBtnAnmi(iv_term);
        btnAnmiUtils.setBtnAnmi(btn_save);

        getData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_scoin:

                final List<String> sinolist = new ArrayList<>();

                for (int i = 100; i > 0; i--) {
                    sinolist.add(i + "");
                }
                DialogUtils.showTimeSelectItemDialog(ConsumeProtectionActivity.this, sinolist, 0, R.string.unit_coin, new DialogUtils.SelectItemDialogListener() {
                    @Override
                    public void onBtn_OK(int pos) {
                        tv_sino_coin.setText(sinolist.get(pos));
                        limitBean.setPayment_sinocoin_limit(sinolist.get(pos));
                    }
                });
                break;
            case R.id.iv_term:
                final List<String> daylist = new ArrayList<>();
                daylist.add("0");
                daylist.add("5");
                daylist.add("10");
                daylist.add("15");
                daylist.add("20");
                daylist.add("25");
                daylist.add("30");

                DialogUtils.showTimeSelectItemDialog(ConsumeProtectionActivity.this, daylist, 0, R.string.unit_d, new DialogUtils.SelectItemDialogListener() {
                    @Override
                    public void onBtn_OK(int pos) {
                        tv_term.setText(daylist.get(pos));
                        limitBean.setPayment_cycle_day(daylist.get(pos));
                    }
                });
                break;
            case R.id.btn_save:

                String isPay;
                if (cb_allow_pay.isChecked())
                    isPay = "1";
                else
                    isPay = "0";

                upDate(limitBean.getPayment_sinocoin_limit(), limitBean.getPayment_cycle_day(), isPay);
                break;
        }
    }

    private void getData() {

        DialogUtils.showProgressDialog(ConsumeProtectionActivity.this, true);

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

    private void upDate(String sino, String day, String isPay) {
        DialogUtils.showProgressDialog(ConsumeProtectionActivity.this, true);
        HttpUtil.getUtils().getJsonString(API.updateLimit(sino, day, isPay), new HttpUtil.OnNetResponseListener() {
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
                        finish();
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
