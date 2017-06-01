package com.sinoangel.ctrl.parentalcontrol.account.parent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.utils.API;
import com.sinoangel.ctrl.parentalcontrol.utils.AppUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.DialogUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.HttpUtil;

import java.util.HashMap;
import java.util.Map;

public class ForgetPWDActivity extends BaseActivity implements View.OnClickListener {

    private View btn_continue, iv_back;
    private EditText et_email;

    private static final int RESULT_SUCCESS = 1;//提交成功
    private static final int RESULT_FAILD = 2;//提交失败

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            DialogUtils.dismissProgressDialog();
            switch (msg.what) {
                case RESULT_SUCCESS:
                    DialogUtils.showOneBtnDialog(ForgetPWDActivity.this, getString(R.string.login_reset_pwd), false, new DialogUtils.DialogBtnListener() {
                        @Override
                        public void onBtn_OK() {
                            finish();
                        }

                        @Override
                        public void onBtn_NO() {

                        }
                    });
                    break;
                case RESULT_FAILD:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        et_email = (EditText) findViewById(R.id.et_email);
        btn_continue = findViewById(R.id.btn_continue);
        iv_back = findViewById(R.id.iv_back);

        iv_back.setOnClickListener(this);
        btn_continue.setOnClickListener(this);

        //点击动画
        BtnAnmiUtils btnAnmiUtils = new BtnAnmiUtils();
        btnAnmiUtils.setBtnAnmi(iv_back);
        btnAnmiUtils.setBtnAnmi(btn_continue);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_continue:
                String email = et_email.getText().toString().trim();
                if (!HttpUtil.isNetworkAvailable()) {
                    //网络不可用
                    AppUtils.showToast(getString(R.string.tag_net_null));
                } else if (TextUtils.isEmpty(email)) {
                    //请填写邮箱
                    AppUtils.showToast(getString(R.string.login_email_null));
                } else if (!AppUtils.isFormatEmail(email)) {
                    //邮箱格式有误!
                    AppUtils.showToast(getString(R.string.login_email_error));
                } else {
                    DialogUtils.showProgressDialog(ForgetPWDActivity.this, true);
                    goCommit();
                }
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void goCommit() {
        Map<String, String> mss = new HashMap<>();
        mss.put("email", et_email.getText().toString());
        mss.put("lang", "2");
        String url = API.NET_FINDPWDBYEMAIL;
        HttpUtil.getUtils().getJsonStringByPost(url, mss, new HttpUtil.OnNetResponseListener() {
            @Override
            public void onNetFail() {
                handler.sendEmptyMessage(RESULT_FAILD);
            }

            @Override
            public void onNetSucceed(String json) {

                handler.sendEmptyMessage(RESULT_SUCCESS);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
