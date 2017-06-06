package com.sinoangel.ctrl.parentalcontrol.account.parent;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.account.SelectHeadActivity;
import com.sinoangel.ctrl.parentalcontrol.account.parent.bean.Country;
import com.sinoangel.ctrl.parentalcontrol.account.parent.bean.ParentBean;
import com.sinoangel.ctrl.parentalcontrol.account.parent.bean.TokenBean;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.base.NetBean;
import com.sinoangel.ctrl.parentalcontrol.utils.API;
import com.sinoangel.ctrl.parentalcontrol.utils.AppUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.Constant;
import com.sinoangel.ctrl.parentalcontrol.utils.DialogUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.HttpUtil;
import com.sinoangel.ctrl.parentalcontrol.utils.StaticObjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private ImageView iv_head;
    private View iv_back, ll_box_pwd, v_line;
    private EditText et_userName, et_email, et_pwd;
    private TextView tv_country, btn_register;
    private RadioGroup rg_sex;

    private int headId;//头像id
    private String sex;//0男孩 1 女孩
    private String countryId = "2";
    private ParentBean.DataBean.UserBean parent;//家长信息
    private List<Country.DataBean> countryArrayList;//国家列表


    private static final int RESULT_REGISTER_SUCCESS = 1;//注册成功
    private static final int RESULT_REGISTER_FAILD = 2;//注册失败
    private static final int RESULT_REGISTER_EMAIL_REPEAT = 3;//邮箱已经被注册
    private static final int RESULT_REGISTER_USERNAME_REPEAT = 4;//用户名已存在

    private static final int RESULT_UPDATE_SUCCESS = 5;//修改成功
    private static final int RESULT_UPDATE_FAILD = 6;//修改失败

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            DialogUtils.dismissProgressDialog();
            switch (msg.what) {
                case RESULT_REGISTER_SUCCESS:
                    DialogUtils.showOneBtnDialog(RegisterActivity.this, getString(R.string.register_success), false, new DialogUtils.DialogBtnListener() {
                        @Override
                        public void onBtn_OK() {
                            finish();
                        }

                        @Override
                        public void onBtn_NO() {

                        }
                    });
                    break;
                case RESULT_REGISTER_FAILD:
                    AppUtils.showToast(getString(R.string.tag_net_request_error));
                    break;
                case RESULT_REGISTER_EMAIL_REPEAT:
                    AppUtils.showToast(getString(R.string.register_email_repat));
                    break;
                case RESULT_REGISTER_USERNAME_REPEAT:
                    AppUtils.showToast(getString(R.string.register_username_repat));
                    break;
                case RESULT_UPDATE_SUCCESS:
                    AppUtils.showToast(getString(R.string.tag_net_request_success));
                    Intent intent = new Intent(RegisterActivity.this, ParentAccountActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case RESULT_UPDATE_FAILD:
                    AppUtils.showToast(getString(R.string.tag_net_request_error));
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
            parent = (ParentBean.DataBean.UserBean) bundle.get(Constant.USERBEAN);
        if (parent != null) {
            et_userName.setText(parent.getRealname());
            et_email.setText(parent.getEmail());
            et_email.setEnabled(false);
            ll_box_pwd.setVisibility(View.GONE);
            v_line.setVisibility(View.GONE);
            tv_country.setText(parent.getCountry_name());
            if (TextUtils.equals("0", parent.getSex()))
                rg_sex.check(R.id.rb_sex_boy);
            else
                rg_sex.check(R.id.rb_sex_girl);
            btn_register.setText(getString(R.string.btn_save));

            countryId = parent.getCountry_id();
            sex = parent.getSex();
        }

        HttpUtil.getUtils().getJsonString(API.getCountryList(), new HttpUtil.OnNetResponseListener() {
            @Override
            public void onNetFail() {

            }

            @Override
            public void onNetSucceed(String json) {
                Country bean = JSON.parseObject(json, Country.class);
                countryArrayList = bean.getData();
            }
        });
    }

    private void initView() {
        iv_head = (ImageView) findViewById(R.id.iv_head);
        iv_back = findViewById(R.id.iv_back);
        btn_register = (TextView) findViewById(R.id.btn_register);
        rg_sex = (RadioGroup) findViewById(R.id.rg_sex);
        ll_box_pwd = findViewById(R.id.ll_box_pwd);

        et_userName = (EditText) findViewById(R.id.et_userName);
        et_email = (EditText) findViewById(R.id.et_email);
        et_pwd = (EditText) findViewById(R.id.et_pwd);
        v_line = findViewById(R.id.v_line);
        tv_country = (TextView) findViewById(R.id.tv_country);

        addListener();
    }

    private void addListener() {
        iv_head.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        tv_country.setOnClickListener(this);

        //点击动画
        BtnAnmiUtils btnAnmiUtils = new BtnAnmiUtils();
        btnAnmiUtils.setBtnAnmi(iv_back);
        btnAnmiUtils.setBtnAnmi(iv_head);
        btnAnmiUtils.setBtnAnmi(btn_register);

        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_sex_boy:
                        sex = "0";
                        break;
                    case R.id.rb_sex_girl:
                        sex = "1";
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.iv_head:
                Intent intent = new Intent(RegisterActivity.this, SelectHeadActivity.class);
                intent.putExtra(Constant.HEAD_FALGE, Constant.HEAD_PARENT);
                intent.putExtra(Constant.HEADID, headId);
                startActivityForResult(intent, 200);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_country:

                List<String> list = new ArrayList<>();
                if (parent != null)
                    countryId = parent.getCountry_id();
                int pos = 0, index = 0;
                for (Country.DataBean item : countryArrayList) {
                    list.add(item.getCountry());
                    if (TextUtils.equals(countryId, item.getId()))
                        index = pos;
                    pos++;
                }

                DialogUtils.showCountrySelectItemDialog(RegisterActivity.this, list, index, new DialogUtils.SelectItemDialogListener() {
                    @Override
                    public void onBtn_OK(int pos) {
                        String name = countryArrayList.get(pos).getCountry();
                        countryId = countryArrayList.get(pos).getId();
                        tv_country.setText(name);
                    }
                });
                break;
            case R.id.btn_register:

                String userName = et_userName.getText().toString().trim();
                String email = et_email.getText().toString().trim();
                String pwd = et_pwd.getText().toString().trim();

                if (!HttpUtil.isNetworkAvailable()) {
                    //网络不可用
                    AppUtils.showToast(getString(R.string.tag_net_null));
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
                } else if (parent == null && TextUtils.isEmpty(pwd)) {
                    //请输入密码
                    AppUtils.showToast(getString(R.string.login_pwd_null));
                } else {
                    DialogUtils.showProgressDialog(RegisterActivity.this, false);
                    Map<String, String> mss = new HashMap<>();
                    if (parent == null) {
                        mss.put("username", userName);
                        mss.put("email", email);
                        mss.put("password", pwd);
                        mss.put("lang", String.valueOf(AppUtils.systemLanguageType()));
                        goRegister(mss);
                    } else {
                        TokenBean.DataBean uidb = StaticObjects.getUidb();
                        if (uidb != null)
                            mss.put("userId", uidb.getUserId());
                        mss.put("country_id", countryId);
                        mss.put("realname", userName);
                        mss.put("sex", sex);
                        mss.put("pic_id", headId + "");
                        mss.put("usericon", headId + "");
                        goUpdate(mss);
                    }
                }
                break;
        }
    }

    private void goUpdate(Map<String, String> mss) {

        HttpUtil.getUtils().getJsonString(API.upDateUserInfo(mss), new HttpUtil.OnNetResponseListener() {
            @Override
            public void onNetFail() {
                handler.sendEmptyMessage(RESULT_UPDATE_FAILD);
            }

            @Override
            public void onNetSucceed(String json) {

                try {
                    NetBean pb = JSON.parseObject(json, NetBean.class);

                    if (pb.getFlag() == 1) {
                        handler.sendEmptyMessage(RESULT_UPDATE_SUCCESS);
                    } else
                        handler.sendEmptyMessage(RESULT_UPDATE_FAILD);
                } catch (Exception e) {
                    handler.sendEmptyMessage(RESULT_UPDATE_FAILD);
                }
            }
        });
    }

    private void goRegister(Map<String, String> mss) {
        HttpUtil.getUtils().getJsonString(API.userRegister(mss), new HttpUtil.OnNetResponseListener() {
            @Override
            public void onNetFail() {
                handler.sendEmptyMessage(RESULT_REGISTER_FAILD);
            }

            @Override
            public void onNetSucceed(String json) {
                TokenBean ui = null;
                try {
                    ui = JSON.parseObject(json, TokenBean.class);
                } catch (Exception e) {
                    handler.sendEmptyMessage(RESULT_REGISTER_FAILD);
                    return;
                }
                if (ui.getFlag() == 1) {
                    //注册成功
                    handler.sendEmptyMessage(RESULT_REGISTER_SUCCESS);
                    Map<String, String> mss = new HashMap<>();
                    mss.put("country_id", countryId);
                    mss.put("realname", et_userName.getText().toString().trim());
                    mss.put("sex", sex);
                    mss.put("pic_id", headId + "");
                    mss.put("usericon", headId + "");
                    HttpUtil.getUtils().getJsonString(API.upDateUserInfo(mss), null);
                } else {
                    if (TextUtils.equals("1", ui.getError())) {
                        //用户名已存在
                        handler.sendEmptyMessage(RESULT_REGISTER_USERNAME_REPEAT);
                    } else if (TextUtils.equals("2", ui.getError())) {
                        //邮箱已存在
                        handler.sendEmptyMessage(RESULT_REGISTER_EMAIL_REPEAT);
                    } else {
                        //未知错误
                        handler.sendEmptyMessage(RESULT_REGISTER_FAILD);
                    }
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == 300) {
            headId = data.getIntExtra(Constant.HEADID, 0);
            iv_head.setImageResource(Constant.ParentHeadIdList[headId]);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
