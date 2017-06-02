package com.sinoangel.ctrl.parentalcontrol.account.kids;

import android.content.DialogInterface;
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
import com.sinoangel.ctrl.parentalcontrol.account.kids.bean.KidBean;
import com.sinoangel.ctrl.parentalcontrol.account.parent.bean.TokenBean;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.base.NetBean;
import com.sinoangel.ctrl.parentalcontrol.utils.API;
import com.sinoangel.ctrl.parentalcontrol.utils.AppUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.Constant;
import com.sinoangel.ctrl.parentalcontrol.utils.HttpUtil;
import com.sinoangel.ctrl.parentalcontrol.utils.ImageUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.StaticObjects;
import com.sinoangel.ctrl.parentalcontrol.webview.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreateKidActivity extends BaseActivity implements View.OnClickListener {

    private View iv_back;
    private ImageView iv_head;
    private EditText et_userName;
    private RadioGroup rg_sex, rg_theme;
    private TextView tv_birthday, btn_register;

    private int headId;//头像id
    private String sex;//性别
    private String theme;//主题

    private KidBean.DataBean kidb;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case RESULT_CANCELED:
                    break;
                case RESULT_OK:
                    setResult(Constant.RESULT_SUCCESS_UPDATE);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_kid);

        iv_back = findViewById(R.id.iv_back);
        iv_head = (ImageView) findViewById(R.id.iv_head);
        et_userName = (EditText) findViewById(R.id.et_userName);
        rg_sex = (RadioGroup) findViewById(R.id.rg_sex);
        rg_theme = (RadioGroup) findViewById(R.id.rg_theme);
        btn_register = (TextView) findViewById(R.id.btn_register);
        tv_birthday = (TextView) findViewById(R.id.tv_birthday);

        addLinister();

        Bundle bundle = getIntent().getExtras();

        if (bundle != null)
            kidb = (KidBean.DataBean) bundle.get(Constant.USERBEAN);
        if (kidb != null) {

            String head =kidb.getUsericon();
            if (head != null) {
                if (head.length() == 1) {
                    try {
                        int index = Integer.parseInt(head);
                        if (index >= 0 && index < 6)
                            iv_head.setImageResource(Constant.KidHeadIdList[index]);
                    } catch (Exception e) {
                    }
                } else
                    ImageUtils.showImgUrl(head, iv_head);
            }


            et_userName.setText(kidb.getNickname());
            if (TextUtils.equals(Constant.BOY, kidb.getSex()))
                rg_sex.check(R.id.rb_sex_boy);
            else
                rg_sex.check(R.id.rb_sex_girl);

            switch (kidb.getTheme_id()) {
                case Constant.THEME1:
                    rg_theme.check(R.id.rb_theme1);
                    break;
                case Constant.THEME2:
                    rg_theme.check(R.id.rb_theme2);
                    break;
                case Constant.THEME3:
                    rg_theme.check(R.id.rb_theme3);
                    break;
            }
            tv_birthday.setText(kidb.getBirthday());

            btn_register.setText(R.string.btn_update);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            tv_birthday.setText(sdf.format(new Date()));
            rg_theme.check(R.id.rb_theme1);
            rg_sex.check(R.id.rb_sex_boy);
        }
    }

    private void addLinister() {

        iv_back.setOnClickListener(this);
        iv_head.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        tv_birthday.setOnClickListener(this);

        BtnAnmiUtils btnAnmiUtils = new BtnAnmiUtils();
        btnAnmiUtils.setBtnAnmi(iv_back);
        btnAnmiUtils.setBtnAnmi(iv_head);
        btnAnmiUtils.setBtnAnmi(btn_register);

        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_sex_boy:
                        sex = Constant.BOY;
                        break;
                    case R.id.rb_sex_girl:
                        sex = Constant.GIRL;
                        break;
                }
            }
        });

        rg_theme.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_theme1:
                        theme = Constant.THEME1;
                        break;
                    case R.id.rb_theme2:
                        theme = Constant.THEME2;
                        break;
                    case R.id.rb_theme3:
                        theme = Constant.THEME3;
                        break;
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
            case R.id.iv_head:
                Intent intent = new Intent(CreateKidActivity.this, SelectHeadActivity.class);
                intent.putExtra(Constant.HEAD_FALGE, Constant.HEAD_KIDS);
                startActivityForResult(intent, 200);
                break;
            case R.id.btn_register:
                String name = et_userName.getText().toString().trim();
                String date = tv_birthday.getText().toString();
                if (TextUtils.isEmpty(name))
                    AppUtils.showToast(getString(R.string.register_userName_null));
                else if (!AppUtils.isFormatUser(name))
                    AppUtils.showToast(getString(R.string.register_userName_error));
                else if (kidb == null) {
                    addKid(name, date);
                } else {
                    updateKid(name, date);
                }
                break;
            case R.id.tv_birthday:

                String[] data = tv_birthday.getText().toString().trim().split("-");
                try {
                    int nian = Integer.parseInt(data[0]);
                    int yue = Integer.parseInt(data[1]);
                    int ri = Integer.parseInt(data[2]);
                    final DatePickerDialog dpdialog = new DatePickerDialog(this, new int[]{nian, yue, ri});

                    dpdialog.setConfirmButton(new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            StringBuilder stringBuilder = new StringBuilder();
                            stringBuilder.append(dpdialog.getYear());
                            stringBuilder.append("-");
                            stringBuilder.append(String.format("%02d", dpdialog.getMonth()));
                            stringBuilder.append("-");
                            stringBuilder.append(String.format("%02d", dpdialog.getDay()));
                            tv_birthday.setText(stringBuilder.toString());
                            dialog.dismiss();
                        }
                    });

                    dpdialog.show();
                } catch (Exception e) {

                }

                break;
        }

    }


    private void addKid(String name, String birthday) {

        Map<String, String> mss = new HashMap<>();
        TokenBean.DataBean uidb = StaticObjects.getUidb();
        if (uidb != null)
            mss.put("userId", uidb.getUserId());
        mss.put("nickname", name);
        mss.put("birthday", birthday);
        mss.put("sex", sex);
        mss.put("theme_id", theme);
        mss.put("pic_id", headId + "");
        mss.put("usericon", headId + "");
        mss.put("first_lang", "1");

        HttpUtil.getUtils().getJsonString(API.addKid(mss), new HttpUtil.OnNetResponseListener() {
            @Override
            public void onNetFail() {
                handler.sendEmptyMessage(RESULT_CANCELED);
            }

            @Override
            public void onNetSucceed(String json) {
                try {
                    NetBean netBean = JSON.parseObject(json, NetBean.class);
                    if (netBean.getFlag() == 1) {
                        handler.sendEmptyMessage(RESULT_OK);
                    } else
                        handler.sendEmptyMessage(RESULT_CANCELED);
                } catch (Exception e) {
                    handler.sendEmptyMessage(RESULT_CANCELED);
                }
            }
        });
    }

    private void updateKid(String name, String birthday) {

        Map<String, String> mss = new HashMap<>();
        mss.put("childId", kidb.getId());
        mss.put("nickname", name);
        mss.put("birthday", birthday);
        mss.put("sex", sex);
        mss.put("theme_id", theme);
        mss.put("pic_id", headId + "");
        mss.put("first_lang", "1");
        mss.put("child", "2");

        HttpUtil.getUtils().getJsonString(API.updateKid(mss), new HttpUtil.OnNetResponseListener() {
            @Override
            public void onNetFail() {
                handler.sendEmptyMessage(RESULT_CANCELED);
            }

            @Override
            public void onNetSucceed(String json) {
                try {
                    NetBean netBean = JSON.parseObject(json, NetBean.class);
                    if (netBean.getFlag() == 1) {
                        handler.sendEmptyMessage(RESULT_OK);
                    } else
                        handler.sendEmptyMessage(RESULT_CANCELED);
                } catch (Exception e) {
                    handler.sendEmptyMessage(RESULT_CANCELED);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200 && resultCode == 300) {
            headId = data.getIntExtra(Constant.HEADID, 0);
            iv_head.setImageResource(Constant.KidHeadIdList[headId]);
        }
    }
}
