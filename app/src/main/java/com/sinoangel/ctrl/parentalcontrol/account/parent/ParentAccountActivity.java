package com.sinoangel.ctrl.parentalcontrol.account.parent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.account.parent.bean.ParentBean;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.utils.API;
import com.sinoangel.ctrl.parentalcontrol.utils.AppUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.Constant;
import com.sinoangel.ctrl.parentalcontrol.utils.DialogUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.HttpUtil;
import com.sinoangel.ctrl.parentalcontrol.utils.ImageUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.StaticObjects;

/**
 * Created by Z on 2017/5/26.
 */

public class ParentAccountActivity extends BaseActivity implements View.OnClickListener {
    private View iv_back, btn_updata, btn_exit;
    private ImageView iv_head;
    private TextView tv_username, tv_email, tv_sex, tv_cuntryname;


    private ParentBean.DataBean.UserBean parent;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RESULT_CANCELED:
                    AppUtils.showToast(getString(R.string.tag_net_request_error));
                    break;
                case RESULT_OK:
                    tv_username.setText(parent.getRealname());
                    tv_email.setText(parent.getEmail());
                    if (TextUtils.equals(parent.getSex(), "0"))
                        tv_sex.setText("男");
                    else
                        tv_sex.setText("女");
                    tv_cuntryname.setText(parent.getCountry_name());

                    String head = parent.getUsericon();
                    if (head != null) {
                        if (head.length() == 1) {
                            try {
                                int index = Integer.parseInt(head);
                                if (index >= 0 && index < 6)
                                    iv_head.setImageResource(Constant.ParentHeadIdList[index]);
                            } catch (Exception e) {
                            }
                        } else
                            ImageUtils.showImgUrl(head, iv_head);
                    }
                    break;
            }
            DialogUtils.dismissProgressDialog();
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parentaccount);

        iv_back = findViewById(R.id.iv_back);
        tv_username = (TextView) findViewById(R.id.tv_username);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_sex = (TextView) findViewById(R.id.tv_sex);
        tv_cuntryname = (TextView) findViewById(R.id.tv_cuntryname);
        iv_head = (ImageView) findViewById(R.id.iv_head);

        btn_updata = findViewById(R.id.btn_updata);
        btn_exit = findViewById(R.id.btn_exit);

        iv_back.setOnClickListener(this);
        btn_updata.setOnClickListener(this);
        btn_exit.setOnClickListener(this);

        BtnAnmiUtils btnAnmiUtils = new BtnAnmiUtils();
        btnAnmiUtils.setBtnAnmi(iv_back);
        btnAnmiUtils.setBtnAnmi(btn_updata);
        btnAnmiUtils.setBtnAnmi(btn_exit);

        getData();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        getData();
    }

    private void getData() {

        DialogUtils.showProgressDialog(ParentAccountActivity.this, true);

        HttpUtil.getUtils().getJsonString(API.getUserInfo(), new HttpUtil.OnNetResponseListener() {
            @Override
            public void onNetFail() {
                handler.sendEmptyMessage(RESULT_CANCELED);
            }

            @Override
            public void onNetSucceed(String json) {

                try {
                    ParentBean pb = JSON.parseObject(json, ParentBean.class);

                    if (pb.getFlag() == 1) {
                        parent = pb.getData().getUser();
                        if (parent != null) {
                            Intent intent = new Intent(Constant.ACTION_HEAD_UPDATE);
                            intent.putExtra(Constant.HEAD_FALGE, parent.getUsericon());
                            sendBroadcast(intent);
                            handler.sendEmptyMessage(RESULT_OK);
                        } else
                            handler.sendEmptyMessage(RESULT_CANCELED);
                    } else
                        handler.sendEmptyMessage(RESULT_CANCELED);
                } catch (Exception e) {
                    handler.sendEmptyMessage(RESULT_CANCELED);
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
            case R.id.btn_updata:
                Intent intent = new Intent(ParentAccountActivity.this, RegisterActivity.class);
                intent.putExtra(Constant.USERBEAN, parent);
                startActivityForResult(intent, 0);
                break;
            case R.id.btn_exit:
                DialogUtils.showTwoBtnDialog(ParentAccountActivity.this, getString(R.string.tag_exit), true, new DialogUtils.DialogBtnListener() {
                    @Override
                    public void onBtn_OK() {
                        StaticObjects.setUidb(null);
                        finish();
                    }

                    @Override
                    public void onBtn_NO() {

                    }
                });
                break;
        }
    }
}
