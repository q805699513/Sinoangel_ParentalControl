package com.sinoangel.ctrl.parentalcontrol.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.utils.AppUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.Constant;
import com.sinoangel.ctrl.parentalcontrol.utils.HttpUtil;
import com.sinoangel.ctrl.parentalcontrol.utils.StaticObjects;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_head;
    private View iv_back, btn_register;
    private EditText et_userName, et_sex, et_email, et_pwd, et_country;

    private int headId;//头像id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        iv_head = (ImageView) findViewById(R.id.iv_head);
        iv_back = findViewById(R.id.iv_back);
        btn_register = findViewById(R.id.btn_register);

        et_userName = (EditText) findViewById(R.id.et_userName);
        et_sex = (EditText) findViewById(R.id.et_sex);
        et_email = (EditText) findViewById(R.id.et_email);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        et_country = (EditText) findViewById(R.id.et_country);

        addListener();
    }

    private void addListener() {
        iv_head.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        btn_register.setOnClickListener(this);

        //点击动画
        new BtnAnmiUtils().setBtnAnmi(iv_back);
        new BtnAnmiUtils().setBtnAnmi(iv_head);
        new BtnAnmiUtils().setBtnAnmi(btn_register);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_head:
                startActivityForResult(new Intent(RegisterActivity.this, SelectHeadActivity.class), 200);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_register:

                String userName = et_userName.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String pwd = et_pwd.getText().toString().trim();

                if (!HttpUtil.isNetworkAvailable()) {
                    //网络不可用
                    AppUtils.showToast(getString(R.string.net_internet_null));
                } else if (TextUtils.isEmpty(userName)) {
                    //请填用户名
                    AppUtils.showToast(getString(R.string.register_userName_null));
                } else if (!AppUtils.isFormatUser(userName)) {
                    //用户名格式有误!
                    AppUtils.showToast(getString(R.string.register_userName_error));
                } else if (TextUtils.isEmpty(email)) {
                    //请填写邮箱
                    AppUtils.showToast(getString(R.string.login_email_null));
                } else if (!AppUtils.isFormatEmail(email)) {
                    //邮箱格式有误!
                    AppUtils.showToast(getString(R.string.login_email_error));
                } else if (TextUtils.isEmpty(pwd)) {
                    //请输入密码
                    AppUtils.showToast(getString(R.string.login_pwd_null));
                } else {
                    goRegister();
                }
                break;
        }
    }

    private void goRegister() {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == 300) {
            headId = data.getIntExtra(Constant.HEADID, 0);
            iv_head.setImageResource(StaticObjects.HeadIdList[headId]);
        }
    }
}
