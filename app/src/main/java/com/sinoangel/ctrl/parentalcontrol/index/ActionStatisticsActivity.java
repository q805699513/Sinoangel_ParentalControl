package com.sinoangel.ctrl.parentalcontrol.index;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.account.parent.bean.TokenBean;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.customview.CalendarCard;
import com.sinoangel.ctrl.parentalcontrol.customview.CustomDate;
import com.sinoangel.ctrl.parentalcontrol.index.adapter.ActionStatisticsAdapter;
import com.sinoangel.ctrl.parentalcontrol.index.bean.StatisticsBean;
import com.sinoangel.ctrl.parentalcontrol.utils.API;
import com.sinoangel.ctrl.parentalcontrol.utils.AppUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.DialogUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.HttpUtil;
import com.sinoangel.ctrl.parentalcontrol.utils.ImageUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.StaticObjects;

import java.util.Calendar;
import java.util.List;

public class ActionStatisticsActivity extends BaseActivity implements View.OnClickListener {

    private View iv_back, iv_setdate;
    private ActionStatisticsAdapter actionStatisticsAdapter;

    private List<StatisticsBean.DataBean> list;
    private int dyear, dmonth, dday;//日期选择弹窗的时间值

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RESULT_CANCELED:
                    AppUtils.showToast(getString(R.string.tag_net_request_error));
                    break;
                case RESULT_OK:
                    actionStatisticsAdapter.setDate(list, getWeekName(dyear, dmonth, dday));
                    break;
            }
            DialogUtils.dismissProgressDialog();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_statistics);

        RecyclerView rv_list = (RecyclerView) findViewById(R.id.rv_list);
        iv_setdate = findViewById(R.id.iv_setdate);
        iv_back = findViewById(R.id.iv_back);

        iv_back.setOnClickListener(this);
        iv_setdate.setOnClickListener(this);
        BtnAnmiUtils btnAnmiUtils = new BtnAnmiUtils();
        btnAnmiUtils.setBtnAnmi(iv_back);
        btnAnmiUtils.setBtnAnmi(iv_setdate);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_list.setLayoutManager(linearLayoutManager);

        actionStatisticsAdapter = new ActionStatisticsAdapter();
        rv_list.setAdapter(actionStatisticsAdapter);

        Calendar calendar = Calendar.getInstance();
        dyear = calendar.get(Calendar.YEAR);
        dmonth = calendar.get(Calendar.MONTH) + 1;
        dday = calendar.get(Calendar.DAY_OF_MONTH);

        getData(String.format("%02d-%02d-%02d", dyear, dmonth, dday));
    }

    private void getData(String time) {

        DialogUtils.showProgressDialog(this, true);

        TokenBean.DataBean uidb = StaticObjects.getUidb();
        if (uidb != null)
            HttpUtil.getUtils().getJsonString(API.getKidsActions(uidb.getUserId(), time), new HttpUtil.OnNetResponseListener() {
                @Override
                public void onNetFail() {
                    handler.sendEmptyMessage(RESULT_CANCELED);
                }

                @Override
                public void onNetSucceed(String json) {

                    try {
                        StatisticsBean statisticsBean = JSON.parseObject(json, StatisticsBean.class);
                        if (statisticsBean.getFlag() == 1) {
                            list = statisticsBean.getData();
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
            case R.id.iv_setdate:
                showDateWindow();
                break;
            case R.id.btn_ok:
                break;
            case R.id.btn_no:
                break;
        }
    }

    private void showDateWindow() {
        final Dialog dateWindow = new Dialog(this, R.style.App_Dialog);
        View contentView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_date_selection, null);
        dateWindow.setContentView(contentView);

        View preImgBtn = contentView.findViewById(R.id.btnPreMonth);
        View nextImgBtn = contentView.findViewById(R.id.btnNextMonth);
        final TextView monthText = (TextView) contentView.findViewById(R.id.tvCurrentMonth);
        View okImgBtn = contentView.findViewById(R.id.btn_ok);
        View onImgBtn = contentView.findViewById(R.id.btn_no);
        RelativeLayout rl = (RelativeLayout) contentView.findViewById(R.id.vp_calendar);

        ((ImageView) contentView.findViewById(R.id.iv_background)).setImageBitmap(ImageUtils.getBulrBit(getWindow()));

        WindowManager.LayoutParams lp = dateWindow.getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dateWindow.getWindow().setAttributes(lp);


        CustomDate customDate = new CustomDate(dyear, dmonth, dday);
        final CalendarCard cc = new CalendarCard(this, customDate, new CalendarCard.OnCellClickListener() {
            @Override
            public void clickDate(CustomDate date) {
                dyear = date.year;
                dmonth = date.month;
                dday = date.day;
            }

            @Override
            public void changeDate(CustomDate date) {
                StringBuilder str = new StringBuilder();
                str.append(date.year).append("-")
                        .append((date.month < 10) ? "0" + date.month : date.month);
                monthText.setText(str);
            }
        });

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_ok:
                        dateWindow.dismiss();

                        getData(String.format("%02d-%02d-%02d", dyear, dmonth, dday));
                        break;
                    case R.id.btn_no:
                        dateWindow.dismiss();
                        break;
                    case R.id.btnPreMonth:
                        cc.leftSlide();
                        break;
                    case R.id.btnNextMonth:
                        cc.rightSlide();
                        break;
                }
            }
        };

        preImgBtn.setOnClickListener(onClickListener);
        nextImgBtn.setOnClickListener(onClickListener);
        okImgBtn.setOnClickListener(onClickListener);
        onImgBtn.setOnClickListener(onClickListener);

        BtnAnmiUtils btnAnmiUtils = new BtnAnmiUtils();
        btnAnmiUtils.setBtnAnmi(preImgBtn);
        btnAnmiUtils.setBtnAnmi(nextImgBtn);
        btnAnmiUtils.setBtnAnmi(okImgBtn);
        btnAnmiUtils.setBtnAnmi(onImgBtn);


        rl.removeAllViews();
        rl.addView(cc);
        dateWindow.show();
    }

    private int getWeek(int y, int m, int d) {
        if (m < 3) {
            m += 12;
            --y;
        }
        int w = (d + 1 + 2 * m + 3 * (m + 1) / 5 + y + (y >> 2) - y / 100 + y / 400) % 7;
        return w;
    }

    private String getWeekName(int y, int m, int d) {
        String weekName = "";
        switch (getWeek(y, m, d)) {
            case 0:
                weekName = getString(R.string.sunday);
                break;
            case 1:
                weekName = getString(R.string.monday);
                break;
            case 2:
                weekName = getString(R.string.thesday);
                break;
            case 3:
                weekName = getString(R.string.wednesday);
                break;
            case 4:
                weekName = getString(R.string.thursday);
                break;
            case 5:
                weekName = getString(R.string.friday);
                break;
            case 6:
                weekName = getString(R.string.saturday);
                break;
        }
        return String.format("%02d-%02d-%02d  %s", y, m, d, weekName);
    }

}
