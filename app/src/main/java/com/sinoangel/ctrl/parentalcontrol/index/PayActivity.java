package com.sinoangel.ctrl.parentalcontrol.index;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;

public class PayActivity extends BaseActivity implements View.OnClickListener {

    private View iv_back, iv_alipay, iv_wechat, btn_pay;
    private RadioGroup rg_dian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        iv_back = findViewById(R.id.iv_back);
        iv_alipay = findViewById(R.id.iv_alipay);
        iv_wechat = findViewById(R.id.iv_wechat);
        btn_pay = findViewById(R.id.btn_pay);
        rg_dian = (RadioGroup) findViewById(R.id.rg_dian);


        iv_back.setOnClickListener(this);
        iv_alipay.setOnClickListener(this);
        iv_wechat.setOnClickListener(this);
        btn_pay.setOnClickListener(this);

        BtnAnmiUtils btnAnmiUtils = new BtnAnmiUtils();
        btnAnmiUtils.setBtnAnmi(iv_back);
        btnAnmiUtils.setBtnAnmi(btn_pay);
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
            case R.id.btn_pay:
                goPay();
                break;
        }
    }

    private void goPay() {
        
    }
}
