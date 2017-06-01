package com.sinoangel.ctrl.parentalcontrol.index;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.account.parent.LoginActivity;
import com.sinoangel.ctrl.parentalcontrol.account.parent.ParentAccountActivity;
import com.sinoangel.ctrl.parentalcontrol.account.parent.bean.TokenBean;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.base.BaseApplication;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.StaticObjects;
import com.sinoangel.ctrl.parentalcontrol.webview.ShopActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_head, iv_shop;//头像 商店
    private View ll_main_actionStatistics, ll_main_moneyManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }


    private void initView() {
        ll_main_actionStatistics = findViewById(R.id.ll_main_actionStatistics);
        ll_main_moneyManage = findViewById(R.id.ll_main_moneyManage);
        iv_head = (ImageView) findViewById(R.id.iv_head);
        iv_shop = (ImageView) findViewById(R.id.iv_shop);

        addListener();
    }

    private void addListener() {

        iv_head.setOnClickListener(this);
        iv_shop.setOnClickListener(this);
        ll_main_moneyManage.setOnClickListener(this);
        ll_main_actionStatistics.setOnClickListener(this);

        new BtnAnmiUtils().setBtnAnmi(iv_shop);
        new BtnAnmiUtils().setBtnAnmi(iv_head);
        new BtnAnmiUtils().setBtnAnmi(ll_main_moneyManage);
        new BtnAnmiUtils().setBtnAnmi(ll_main_actionStatistics);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TokenBean.DataBean uidb = StaticObjects.getUidb();
        if (uidb == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else
            BaseApplication.getInstance().sendAnalyticsActivity("家长控制首页");
    }

    @Override
    public void onClick(View v) {
        TokenBean.DataBean uidb = StaticObjects.getUidb();
        //点击动画
        switch (v.getId()) {
            case R.id.iv_shop:

                if (uidb != null) {
                    startActivity(new Intent(this, ShopActivity.class));
                }
                break;
            case R.id.iv_head:
                if (uidb != null) {
                    startActivity(new Intent(this, ParentAccountActivity.class));
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
            case R.id.ll_main_moneyManage:
                break;
            case R.id.ll_main_actionStatistics:
                startActivity(new Intent(this, ActionStatisticsActivity.class));
                break;
        }
    }
}
