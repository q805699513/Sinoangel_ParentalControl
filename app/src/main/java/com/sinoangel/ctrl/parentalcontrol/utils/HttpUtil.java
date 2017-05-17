package com.sinoangel.ctrl.parentalcontrol.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.sinoangel.ctrl.parentalcontrol.base.BaseApplication;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 功能说明: 请求网络，获取json的字符串
 * 编写日期:	2016-1-28
 */
public class HttpUtil {
    private static HttpUtil utils;

    //创建okHttpClient对象
    OkHttpClient mOkHttpClient = new OkHttpClient();

    private HttpUtil() {
    }

    public static HttpUtil getUtils() {
        if (utils == null) {
            utils = new HttpUtil();
        }
        return utils;
    }

    public Call getJsonString(String url, final OnNetResponseListener onResponseListener) {


        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (onResponseListener != null)
                    onResponseListener.onNetFail();
                if (e != null)
                    AppUtils.outputLog(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (onResponseListener != null)
                    onResponseListener.onNetSucceed(response.body().string());
            }


        });
        return call;
    }

    public Call getJsonStringByPost(String url, Map<String, String> mss, final OnNetResponseListener onResponseListener) {
        FormBody.Builder builder = new FormBody.Builder();
        for (String key : mss.keySet()) {
            builder.add(key, mss.get(key));
        }

        final Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        Call call = mOkHttpClient.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (onResponseListener != null)
                    onResponseListener.onNetFail();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (onResponseListener != null)
                    onResponseListener.onNetSucceed(response.body().string());
            }
        });
        return call;
    }

    /**
     * 判断是否开启网络
     *
     * @return
     */
    public static boolean isNetworkAvailable() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) BaseApplication.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetworkInfo = mConnectivityManager
                .getActiveNetworkInfo();
        if (mNetworkInfo != null) {
            return mNetworkInfo.isAvailable();
        }
        return false;
    }


    public interface OnNetResponseListener {
        void onNetFail();

        void onNetSucceed(String json);
    }

}
