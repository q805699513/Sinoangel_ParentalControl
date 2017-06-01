package com.sinoangel.ctrl.parentalcontrol.webview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.account.parent.bean.TokenBean;
import com.sinoangel.ctrl.parentalcontrol.base.BaseActivity;
import com.sinoangel.ctrl.parentalcontrol.utils.API;
import com.sinoangel.ctrl.parentalcontrol.utils.StaticObjects;

/**
 * Created by Z on 2017/5/23.
 */

public class ShopActivity extends BaseActivity {
    private WebView wv_core;
    private View iv_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        wv_core = (WebView) findViewById(R.id.wv_core);
        iv_back = findViewById(R.id.iv_back);

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        WebSettings settings = wv_core.getSettings();
        //延迟加载图片 优先加载脚本
        settings.setLoadsImagesAutomatically(true);
        //缩放到屏幕大小
        settings.setUseWideViewPort(true);
        //设置编码格式
        settings.setDefaultTextEncodingName("UTF-8");
        //开启硬件加速
        wv_core.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        addWebViewClient();

        TokenBean.DataBean uidb = StaticObjects.getUidb();
        if (uidb != null)
            wv_core.loadUrl(API.comeStore(uidb.getId()));
    }

    private void addWebViewClient() {
        wv_core.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (!view.getSettings().getLoadsImagesAutomatically()) {
                    view.getSettings().setLoadsImagesAutomatically(true);
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.exit(0);
    }
}
