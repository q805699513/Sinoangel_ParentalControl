package com.sinoangel.ctrl.parentalcontrol.index;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.account.LoginActivity;
import com.sinoangel.ctrl.parentalcontrol.account.bean.UserInfo;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.base.BaseApplication;
import com.sinoangel.ctrl.parentalcontrol.index.fragment.ActionStatisticsFragment;
import com.sinoangel.ctrl.parentalcontrol.index.fragment.MoneyManageFragment;
import com.sinoangel.ctrl.parentalcontrol.index.fragment.RemoteControlFragment;
import com.sinoangel.ctrl.parentalcontrol.utils.AppUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.StaticObjects;

import java.util.List;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_title;//标题
    private FragmentManager fm;//碎片管理器
    private FragmentTransaction ft;//事物
    private Fragment asf, mmf, rcf;//行为统计 金币管理 远程控制
    private RadioGroup rg_main_menu;//底部菜单
    private ImageView iv_head;//头像

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment();

    }

    private void initFragment() {
        rcf = new RemoteControlFragment();
        asf = new ActionStatisticsFragment();
        mmf = new MoneyManageFragment();

        fm = getSupportFragmentManager();//布局管理器
        rg_main_menu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                ft = fm.beginTransaction();
                List<Fragment> lf = fm.getFragments();
                switch (checkedId) {
                    case R.id.rb_main_actionStatistics:
                        if (lf == null || !lf.contains(asf)) {
                            ft.add(R.id.content_frame, asf);
                        }
                        ft.show(asf);
                        ft.hide(mmf);
                        ft.hide(rcf);
                        tv_title.setText(getString(R.string.main_statistics));
                        break;
                    case R.id.rb_main_moneyManage:
                        if (lf == null || !lf.contains(mmf)) {
                            ft.add(R.id.content_frame, mmf);
                        }
                        ft.show(mmf);
                        ft.hide(asf);
                        ft.hide(rcf);
                        tv_title.setText(getString(R.string.main_cion));
                        break;
                    case R.id.rb_main_remoteControl:
                        if (lf == null || !lf.contains(rcf)) {
                            ft.add(R.id.content_frame, rcf);
                        }
                        ft.show(rcf);
                        ft.hide(mmf);
                        ft.hide(asf);
                        tv_title.setText(getString(R.string.main_control));
                        break;
                }
                ft.commit();
            }
        });


        ((RadioButton) rg_main_menu.getChildAt(1)).setChecked(true);
    }

    private void initView() {
        rg_main_menu = (RadioGroup) findViewById(R.id.rg_main_menu);
        tv_title = (TextView) findViewById(R.id.tv_title);
        iv_head = (ImageView) findViewById(R.id.iv_head);
        addListener();
    }

    private void addListener() {

        iv_head.setOnClickListener(this);

        new BtnAnmiUtils().setBtnAnmi(iv_head);
    }

    @Override
    protected void onResume() {
        super.onResume();
        BaseApplication.getInstance().sendAnalyticsActivity("家长控制首页");
    }

    @Override
    public void onClick(View v) {
        //点击动画
        switch (v.getId()) {
            case R.id.iv_head:
//                UserInfo.DataBean uidb = StaticObjects.getUidb();
//                if (uidb != null) {
//                } else {
                startActivity(new Intent(this, LoginActivity.class));
//                }
                break;
        }
    }
}
