package com.sinoangel.ctrl.parentalcontrol.account;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.utils.AppUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.Constant;
import com.sinoangel.ctrl.parentalcontrol.utils.StaticObjects;

public class SelectHeadActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView iv_head, iv_sele_head1, iv_sele_head2, iv_sele_head3, iv_sele_head4, iv_sele_head5, iv_sele_head6;
    private View iv_back, btn_ok;
    private int headId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_head);
        iv_head = (ImageView) findViewById(R.id.iv_head);
        iv_sele_head1 = (ImageView) findViewById(R.id.iv_sele_head1);
        iv_sele_head2 = (ImageView) findViewById(R.id.iv_sele_head2);
        iv_sele_head3 = (ImageView) findViewById(R.id.iv_sele_head3);
        iv_sele_head4 = (ImageView) findViewById(R.id.iv_sele_head4);
        iv_sele_head5 = (ImageView) findViewById(R.id.iv_sele_head5);
        iv_sele_head6 = (ImageView) findViewById(R.id.iv_sele_head6);
        iv_back = findViewById(R.id.iv_back);
        btn_ok = findViewById(R.id.btn_ok);

        iv_back.setOnClickListener(this);
        btn_ok.setOnClickListener(this);
        iv_sele_head1.setOnClickListener(this);
        iv_sele_head2.setOnClickListener(this);
        iv_sele_head3.setOnClickListener(this);
        iv_sele_head4.setOnClickListener(this);
        iv_sele_head5.setOnClickListener(this);
        iv_sele_head6.setOnClickListener(this);


        //点击动画
        new BtnAnmiUtils().setBtnAnmi(iv_back);
        new BtnAnmiUtils().setBtnAnmi(btn_ok);
        new BtnAnmiUtils().setBtnAnmi(iv_sele_head1);
        new BtnAnmiUtils().setBtnAnmi(iv_sele_head2);
        new BtnAnmiUtils().setBtnAnmi(iv_sele_head3);
        new BtnAnmiUtils().setBtnAnmi(iv_sele_head4);
        new BtnAnmiUtils().setBtnAnmi(iv_sele_head5);
        new BtnAnmiUtils().setBtnAnmi(iv_sele_head6);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_sele_head1:
                headId = 0;
                iv_head.setImageResource(StaticObjects.HeadIdList[headId]);
                break;
            case R.id.iv_sele_head2:
                headId = 1;
                iv_head.setImageResource(StaticObjects.HeadIdList[headId]);
                break;
            case R.id.iv_sele_head3:
                headId = 2;
                iv_head.setImageResource(StaticObjects.HeadIdList[headId]);
                break;
            case R.id.iv_sele_head4:
                headId = 3;
                iv_head.setImageResource(StaticObjects.HeadIdList[headId]);
                break;
            case R.id.iv_sele_head5:
                headId = 4;
                iv_head.setImageResource(StaticObjects.HeadIdList[headId]);
                break;
            case R.id.iv_sele_head6:
                headId = 5;
                iv_head.setImageResource(StaticObjects.HeadIdList[headId]);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_ok:
                Intent data = new Intent();
                data.putExtra(Constant.HEADID, headId);
                setResult(300, data);
                finish();
                break;
        }
    }
}
