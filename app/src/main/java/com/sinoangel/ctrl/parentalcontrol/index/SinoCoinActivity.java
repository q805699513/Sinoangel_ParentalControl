package com.sinoangel.ctrl.parentalcontrol.index;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;

public class SinoCoinActivity extends BaseActivity implements View.OnClickListener {

    private View iv_back, iv_alipay, iv_wechat;
    private RadioGroup rg_dian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinocoin);

        iv_back = findViewById(R.id.iv_back);
        iv_alipay = findViewById(R.id.iv_alipay);
        iv_wechat = findViewById(R.id.iv_wechat);
        rg_dian = (RadioGroup) findViewById(R.id.rg_dian);


        iv_back.setOnClickListener(this);
        iv_alipay.setOnClickListener(this);
        iv_wechat.setOnClickListener(this);

        BtnAnmiUtils btnAnmiUtils = new BtnAnmiUtils();
        btnAnmiUtils.setBtnAnmi(iv_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_alipay:
                rg_dian.check(R.id.rb_alipay);
                break;
            case R.id.iv_wechat:
                rg_dian.check(R.id.rb_wechat);
                break;
        }
    }
}
