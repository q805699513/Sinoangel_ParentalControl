package com.sinoangel.ctrl.parentalcontrol.index.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.index.AddURLActivity;
import com.sinoangel.ctrl.parentalcontrol.index.InternetManagerActivity;
import com.sinoangel.ctrl.parentalcontrol.index.bean.WebBean;
import com.sinoangel.ctrl.parentalcontrol.utils.API;
import com.sinoangel.ctrl.parentalcontrol.utils.BtnAnmiUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.Constant;
import com.sinoangel.ctrl.parentalcontrol.utils.DialogUtils;
import com.sinoangel.ctrl.parentalcontrol.utils.HttpUtil;
import com.sinoangel.ctrl.parentalcontrol.utils.ImageUtils;

import java.util.List;

/**
 * Created by Z on 2017/5/23.
 */

public class InternetManagerAdapter extends RecyclerView.Adapter<InternetManagerAdapter.ViewHolder> implements View.OnClickListener {

    private List<WebBean.DataBean> list;
    private InternetManagerActivity internetManagerActivity;

    public void setData(List<WebBean.DataBean> data) {
        list = data;
        notifyDataSetChanged();
    }

    public InternetManagerAdapter(InternetManagerActivity internetManagerActivity) {
        this.internetManagerActivity = internetManagerActivity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(internetManagerActivity).inflate(R.layout.item_internetmanager_adapter, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WebBean.DataBean wbdb = list.get(position);
        String urlString = wbdb.getWebsite_url();
        ImageUtils.showImgUrl(urlString + "/favicon.ico", holder.iv_webicon);

        holder.tv_title.setText(wbdb.getWebsite_name());

        holder.iv_edit.setTag(R.id.tag_click_id, wbdb);
        holder.iv_delete.setTag(R.id.tag_click_id, wbdb);
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public void onClick(View v) {

        final WebBean.DataBean dataBean = (WebBean.DataBean) v.getTag(R.id.tag_click_id);
        if (dataBean != null)
            switch (v.getId()) {
                case R.id.iv_edit:
                    Intent intent = new Intent(internetManagerActivity, AddURLActivity.class);
                    intent.putExtra(Constant.WEBBEAN, dataBean);
                    internetManagerActivity.startActivityForResult(intent, 0);
                    break;
                case R.id.iv_delete:
                    DialogUtils.showTwoBtnDialog(internetManagerActivity, internetManagerActivity.getString(R.string.tag_delete), true, new DialogUtils.DialogBtnListener() {
                        @Override
                        public void onBtn_OK() {
                            list.remove(dataBean);
                            notifyDataSetChanged();
                            HttpUtil.getUtils().getJsonString(API.deleteWebPath(dataBean.getId()), null);
                        }

                        @Override
                        public void onBtn_NO() {

                        }
                    });
                    break;
            }
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_webicon, iv_edit, iv_delete;
        TextView tv_title;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_webicon = (ImageView) itemView.findViewById(R.id.iv_webicon);
            iv_edit = (ImageView) itemView.findViewById(R.id.iv_edit);
            iv_delete = (ImageView) itemView.findViewById(R.id.iv_delete);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);

            iv_edit.setOnClickListener(InternetManagerAdapter.this);
            iv_delete.setOnClickListener(InternetManagerAdapter.this);

            BtnAnmiUtils btnAnmiUtils = new BtnAnmiUtils();
            btnAnmiUtils.setBtnAnmi(iv_edit);
            btnAnmiUtils.setBtnAnmi(iv_delete);
        }
    }
}
