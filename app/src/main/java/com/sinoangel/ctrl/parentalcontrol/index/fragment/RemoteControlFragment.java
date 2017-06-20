package com.sinoangel.ctrl.parentalcontrol.index.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.curriculumschedule.CurriculumScheduleActivity;
import com.sinoangel.ctrl.parentalcontrol.index.AboutActivity;
import com.sinoangel.ctrl.parentalcontrol.index.ActionStatisticsActivity;
import com.sinoangel.ctrl.parentalcontrol.index.ConsumeProtectionActivity;
import com.sinoangel.ctrl.parentalcontrol.index.EyeshieldSettingActivity;
import com.sinoangel.ctrl.parentalcontrol.index.FeedbackActivity;
import com.sinoangel.ctrl.parentalcontrol.index.SinoCoinActivity;
import com.sinoangel.ctrl.parentalcontrol.webview.ShopActivity;

/**
 * Created by Z on 2017/5/16.
 */

public class RemoteControlFragment extends Fragment implements View.OnClickListener {

    private Context mContext;

    private View ll_box_eyeshield, ll_box_consumptionplan, ll_box_sino_coin, ll_box_about, ll_box_feedback,
            ll_box_plan_class, ll_box_statistics, ll_box_store;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_remotecontrol, null);

        ll_box_statistics = view.findViewById(R.id.ll_box_statistics);
        ll_box_plan_class = view.findViewById(R.id.ll_box_plan_class);
        ll_box_store = view.findViewById(R.id.ll_box_store);
        ll_box_eyeshield = view.findViewById(R.id.ll_box_eyeshield);
        ll_box_consumptionplan = view.findViewById(R.id.ll_box_consumptionplan);
        ll_box_sino_coin = view.findViewById(R.id.ll_box_sino_coin);
        ll_box_about = view.findViewById(R.id.ll_box_about);
        ll_box_feedback = view.findViewById(R.id.ll_box_feedback);

        ll_box_statistics.setOnClickListener(this);
        ll_box_plan_class.setOnClickListener(this);
        ll_box_store.setOnClickListener(this);
        ll_box_eyeshield.setOnClickListener(this);
        ll_box_consumptionplan.setOnClickListener(this);
        ll_box_sino_coin.setOnClickListener(this);
        ll_box_about.setOnClickListener(this);
        ll_box_feedback.setOnClickListener(this);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_box_statistics:
                startActivity(new Intent(mContext, ActionStatisticsActivity.class));
                break;
            case R.id.ll_box_plan_class:
                startActivity(new Intent(mContext, CurriculumScheduleActivity.class));
                break;
            case R.id.ll_box_store:
                startActivity(new Intent(mContext, ShopActivity.class));
                break;
            case R.id.ll_box_eyeshield:
                startActivity(new Intent(mContext, EyeshieldSettingActivity.class));
                break;
            case R.id.ll_box_consumptionplan:
                startActivity(new Intent(mContext, ConsumeProtectionActivity.class));
                break;
            case R.id.ll_box_sino_coin:
                startActivity(new Intent(mContext, SinoCoinActivity.class));
                break;
            case R.id.ll_box_about:
                startActivity(new Intent(mContext, AboutActivity.class));
                break;
            case R.id.ll_box_feedback:
                startActivity(new Intent(mContext, FeedbackActivity.class));
                break;
        }
    }
}
