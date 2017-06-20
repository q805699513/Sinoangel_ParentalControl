package com.sinoangel.ctrl.parentalcontrol.webview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.just.library.AgentWeb;
import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.account.parent.bean.TokenBean;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.utils.API;
import com.sinoangel.ctrl.parentalcontrol.utils.StaticObjects;

/**
 * Created by Z on 2017/5/23.
 */

public class ShopActivity extends BaseActivity {
    private AgentWeb mAgentWeb;
    private View iv_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        iv_back = findViewById(R.id.iv_back);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rl_box);

        TokenBean.DataBean uidb = StaticObjects.getUidb();
        if (uidb != null)
            mAgentWeb = AgentWeb.with(this)
                    .setAgentWebParent(relativeLayout, new RelativeLayout.LayoutParams(-1, -1))//
                    .useDefaultIndicator()
                    .setIndicatorColorWithHeight(ContextCompat.getColor(this,R.color.sino_red),3)
//                .setReceivedTitleCallback(mCallback)
                    .setSecutityType(AgentWeb.SecurityType.strict)
                    .createAgentWeb()//
                    .ready()
                    .go(API.comeStore(uidb.getId()));


        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


//            mAgentWeb.getLoader().loadUrl();
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //mAgentWeb.destroy();
        mAgentWeb.getWebLifeCycle().onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
