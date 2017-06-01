package com.sinoangel.ctrl.parentalcontrol.account.kids;

import android.os.Bundle;
import android.view.View;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;

public class CreateKidActivity extends BaseActivity implements View.OnClickListener {

    private View iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_kid);
        iv_back = findViewById(R.id.iv_back);

        iv_back.setOnClickListener(this);

        BtnAnmiUtils btnAnmiUtils = new BtnAnmiUtils();
        btnAnmiUtils.setBtnAnmi(iv_back);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                break;
        }

    }
}
