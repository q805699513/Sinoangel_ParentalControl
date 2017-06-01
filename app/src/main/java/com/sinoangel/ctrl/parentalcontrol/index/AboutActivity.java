package com.sinoangel.ctrl.parentalcontrol.index;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.alibaba.fastjson.JSON;
import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.index.bean.HelpBean;
import com.sinoangel.ctrl.parentalcontrol.utils.API;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.HttpUtil;
import com.sinoangel.ctrl.parentalcontrol.utils.ImageUtils;

import java.util.List;

public class AboutActivity extends BaseActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    private List<String> ls;

    private View iv_back;
    private ViewPager viewPager;
    private RadioGroup rg_dian;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            for (int j = 0; j < ls.size(); j++) {
                RadioButton rb = new RadioButton(AboutActivity.this);

                rb.setLayoutParams(new RadioGroup.LayoutParams(20, 20));
                rb.setBackgroundResource(R.drawable.rb_dian);
                rb.setButtonDrawable(R.drawable.bk_null);
                rb.setEnabled(false);
                rb.setPadding(5, 0, 5, 0);
                rb.setId(j);
                if (j == 0) {
                    rb.setChecked(true);
                }
                rg_dian.addView(rb);
            }

            pa.notifyDataSetChanged();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        iv_back = findViewById(R.id.iv_back);
        viewPager = (ViewPager) findViewById(R.id.vp_list);
        rg_dian = (RadioGroup) findViewById(R.id.rg_dian);

        viewPager.addOnPageChangeListener(this);

        iv_back.setOnClickListener(this);

        BtnAnmiUtils btnAnmiUtils = new BtnAnmiUtils();
        btnAnmiUtils.setBtnAnmi(iv_back);

        viewPager.setAdapter(pa);
        getNetDate();
    }

    private PagerAdapter pa = new PagerAdapter() {
        @Override
        public int getCount() {
            return ls == null ? 0 : ls.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView ivimg = new ImageView(AboutActivity.this);
            ivimg.setScaleType(ImageView.ScaleType.FIT_XY);
            ImageUtils.showImgUrl(ls.get(position), ivimg);
            container.addView(ivimg);
            return ivimg;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };

    private void getNetDate() {
        HttpUtil.getUtils().getJsonString(API.getHelpImg(), new HttpUtil.OnNetResponseListener() {
            @Override
            public void onNetFail() {
            }

            @Override
            public void onNetSucceed(String json) {
                try {
                    HelpBean helpBean = JSON.parseObject(json, HelpBean.class);
                    ls = helpBean.getData();
                    handler.sendEmptyMessage(0);
                } catch (Exception e) {
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
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ((RadioButton) rg_dian.getChildAt(position)).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
