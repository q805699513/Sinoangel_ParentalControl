package com.sinoangel.ctrl.parentalcontrol.index;

import android.os.Bundle;
import android.view.View;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.DialogUtils;

import java.util.ArrayList;
import java.util.List;

public class EyeshieldSettingActivity extends BaseActivity implements View.OnClickListener {

    private View iv_back, iv_edit1, iv_edit2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eyeshield_setting);

        iv_back = findViewById(R.id.iv_back);
        iv_edit1 = findViewById(R.id.iv_edit1);
        iv_edit2 = findViewById(R.id.iv_edit2);

        iv_back.setOnClickListener(this);
        iv_edit1.setOnClickListener(this);
        iv_edit2.setOnClickListener(this);

        BtnAnmiUtils btnAnmiUtils = new BtnAnmiUtils();
        btnAnmiUtils.setBtnAnmi(iv_back);
        btnAnmiUtils.setBtnAnmi(iv_edit1);
        btnAnmiUtils.setBtnAnmi(iv_edit2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_edit1:
                List<String> list = new ArrayList<>();
                list.add("one");
                list.add("two");
                list.add("two");
                list.add("two");
                list.add("two");
                list.add("two");
                list.add("two");
                DialogUtils.showTimeSelectItemDialog(EyeshieldSettingActivity.this, list, 2, R.string.unit_h, new DialogUtils.SelectItemDialogListener() {
                    @Override
                    public void onBtn_OK(int pos) {

                    }
                });
                break;
            case R.id.iv_edit2:
                break;
        }
    }
}
