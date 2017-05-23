package com.sinoangel.ctrl.parentalcontrol.account;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.account.bean.UserInfo;
import com.sinoangel.ctrl.parentalcontrol.utils.API;
import com.sinoangel.ctrl.parentalcontrol.utils.AppUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.HttpUtil;
import com.sinoangel.ctrl.parentalcontrol.utils.StaticObjects;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Z on 2017/5/16.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {


    private static final int RESULT_LOGIN_SUCCESS = 1;//登录成功
    private static final int RESULT_LOGIN_FAILD = 2;//登录失败
    private static final int RESULT_LOGIN_NO_VERIFICATION = 3;//邮箱未验证
    private static final int RESULT_LOGIN_ACCOUNTORPWD_ERROR = 4;//邮箱或密码错误

    private EditText et_email, et_pwd;
    private View btn_login, tv_forgetpwd, btn_register, iv_back;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RESULT_LOGIN_SUCCESS:
                    break;
                case RESULT_LOGIN_FAILD:
                    break;
                case RESULT_LOGIN_NO_VERIFICATION:
                    AppUtils.showToast(getString(R.string.login_email_no_verification));
                    break;
                case RESULT_LOGIN_ACCOUNTORPWD_ERROR:
                    AppUtils.showToast(getString(R.string.login_pwdoraccount_error));
                    break;

            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        et_email = (EditText) findViewById(R.id.et_email);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        btn_login = findViewById(R.id.btn_login);
        tv_forgetpwd = findViewById(R.id.tv_forgetpwd);
        btn_register = findViewById(R.id.btn_register);
        iv_back = findViewById(R.id.iv_back);

        iv_back.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        tv_forgetpwd.setOnClickListener(this);

        //点击动画
        new BtnAnmiUtils().setBtnAnmi(iv_back);
        new BtnAnmiUtils().setBtnAnmi(btn_login);
        new BtnAnmiUtils().setBtnAnmi(btn_register);
        new BtnAnmiUtils().setBtnAnmi(tv_forgetpwd);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_login:

                String email = et_email.getText().toString().trim();
                String pwd = et_pwd.getText().toString().trim();
                if (!HttpUtil.isNetworkAvailable()) {
                    //网络不可用
                    AppUtils.showToast(getString(R.string.net_internet_null));
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
                    goLogin(email, pwd);
                }
                break;
            case R.id.tv_forgetpwd:
                startActivity(new Intent(LoginActivity.this, ForgetPWDActivity.class));
                break;
            case R.id.btn_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void goLogin(String email, String pwd) {
        Map<String, String> mss = new HashMap<>();
        mss.put("email", email);
        mss.put("password", pwd);
        HttpUtil.getUtils().getJsonStringByPost(API.NET_USER_LOGIN, mss, new HttpUtil.OnNetResponseListener() {
            @Override
            public void onNetFail() {
                handler.sendEmptyMessage(RESULT_LOGIN_FAILD);
            }

            @Override
            public void onNetSucceed(String json) {
                UserInfo ui = null;
                try {
                    ui = JSON.parseObject(json, UserInfo.class);
                } catch (Exception e) {
                }
                if (ui != null) {
                    if (ui.getFlag() == 1) {
                        StaticObjects.setUidb(ui.getData());
                        finish();
                    } else if (ui.getFlag() == -2) {
                        //邮箱未认证
                        handler.sendEmptyMessage(RESULT_LOGIN_NO_VERIFICATION);
                    } else
                        handler.sendEmptyMessage(RESULT_LOGIN_FAILD);
                } else
                    handler.sendEmptyMessage(RESULT_LOGIN_FAILD);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
