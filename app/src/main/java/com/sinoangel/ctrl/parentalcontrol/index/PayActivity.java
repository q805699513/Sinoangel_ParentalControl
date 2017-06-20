package com.sinoangel.ctrl.parentalcontrol.index;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.Constant;

public class PayActivity extends BaseActivity implements View.OnClickListener {

    private View iv_back, btn_pay;
    private RadioGroup rg_dian;
    private TextView tv_jine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        iv_back = findViewById(R.id.iv_back);
        btn_pay = findViewById(R.id.btn_pay);
        rg_dian = (RadioGroup) findViewById(R.id.rg_dian);
        tv_jine = (TextView) findViewById(R.id.tv_jine);


        tv_jine.setText(getIntent().getIntExtra(Constant.PAY_MONEY, 0) + "");

        iv_back.setOnClickListener(this);
        btn_pay.setOnClickListener(this);

        BtnAnmiUtils btnAnmiUtils = new BtnAnmiUtils();
        btnAnmiUtils.setBtnAnmi(iv_back);
        btnAnmiUtils.setBtnAnmi(btn_pay);

        rg_dian.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_wechat:
                        break;
                    case R.id.rb_alipay:
                        break;
                    case R.id.rb_paypal:
                        break;
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
            case R.id.btn_pay:
                goPay();
                break;
        }
    }

    private void goPay() {

    }
}
