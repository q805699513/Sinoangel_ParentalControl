package com.sinoangel.ctrl.parentalcontrol.index.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.base.BaseApplication;
import com.sinoangel.ctrl.parentalcontrol.index.bean.StatisticsBean;
import com.sinoangel.ctrl.parentalcontrol.utils.ImageUtils;

import java.util.List;

/**
 * Created by Z on 2017/5/25.
 */

public class ActionStatisticsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TAG_HEAD = 100;
    private static final int TAG_ITEM = 200;
    private static final int TAG_BOWN = 300;

    private List<StatisticsBean.DataBean> datelist;
    private String time;

    public void setDate(List<StatisticsBean.DataBean> list, String time) {
        if (list != null && list.size() > 0) {
            list.add(0, list.get(0));
            datelist = list;
            this.time = time;
        }
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TAG_HEAD) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statistic_head, null);
            return new HeadViewHolder(view);
        } else if (viewType == TAG_BOWN) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statistic_bown, null);
            return new BownViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statistic_comm, null);
            return new ComViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        StatisticsBean.DataBean dataBean = datelist.get(position);

        if (holder instanceof HeadViewHolder) {
            HeadViewHolder headViewHolder = (HeadViewHolder) holder;
            headViewHolder.tv_context.setText(time);

        } else if (holder instanceof ComViewHolder) {
            ComViewHolder comViewHolder = (ComViewHolder) holder;
            ImageUtils.showImgUrl(dataBean.getImg(), comViewHolder.iv_icon);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(dataBean.getPackName());
            stringBuilder.append(BaseApplication.getInstance().getString(R.string.actionstatistics_lab1));
            stringBuilder.append("《");
            stringBuilder.append(dataBean.getContext());
            stringBuilder.append("》");
            comViewHolder.tv_context.setText(stringBuilder.toString());

            long usetime = 0;
            try {
                usetime = Long.parseLong(dataBean.getUseTime());
            } catch (Exception e) {

            }

            long miao = usetime / 1000 % 60;
            long fen = usetime / 1000 / 60;

            StringBuilder sb_usertiem = new StringBuilder();
            sb_usertiem.append(BaseApplication.getInstance().getString(R.string.actionstatistics_lab2));
            sb_usertiem.append(String.format("%02d", fen));
            sb_usertiem.append(BaseApplication.getInstance().getString(R.string.unit_m));
            sb_usertiem.append(String.format("%02d", miao));
            sb_usertiem.append(BaseApplication.getInstance().getString(R.string.unit_s));
            comViewHolder.tv_usertime.setText(sb_usertiem.toString());

            try {
                String[] time = dataBean.getTime().split(" ");
                comViewHolder.tv_time.setText(time[1]);
            } catch (Exception e) {

            }
        } else if (holder instanceof BownViewHolder) {
            BownViewHolder bownViewHolder = (BownViewHolder) holder;
            ImageUtils.showImgUrl(dataBean.getImg(), bownViewHolder.iv_icon);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(dataBean.getPackName());
            stringBuilder.append(BaseApplication.getInstance().getString(R.string.actionstatistics_lab1));
            stringBuilder.append("《");
            stringBuilder.append(dataBean.getContext());
            stringBuilder.append("》");
            bownViewHolder.tv_context.setText(stringBuilder.toString());

            long usetime = 0;
            try {
                usetime = Long.parseLong(dataBean.getUseTime());
            } catch (Exception e) {

            }

            long miao = usetime / 1000 % 60;
            long fen = usetime / 1000 / 60;

            StringBuilder sb_usertiem = new StringBuilder();
            sb_usertiem.append(BaseApplication.getInstance().getString(R.string.actionstatistics_lab2));
            sb_usertiem.append(String.format("%02d", fen));
            sb_usertiem.append(BaseApplication.getInstance().getString(R.string.unit_m));
            sb_usertiem.append(String.format("%02d", miao));
            sb_usertiem.append(BaseApplication.getInstance().getString(R.string.unit_s));
            bownViewHolder.tv_usertime.setText(sb_usertiem.toString());

            try {
                String[] time = dataBean.getTime().split(" ");
                bownViewHolder.tv_time.setText(time[1]);
            } catch (Exception e) {

            }
        }

    }

    @Override
    public int getItemCount() {
        return datelist == null ? 0 : datelist.size();
    }

    class HeadViewHolder extends RecyclerView.ViewHolder {
        TextView tv_context;

        public HeadViewHolder(View itemView) {
            super(itemView);
            tv_context = (TextView) itemView.findViewById(R.id.tv_context);
        }
    }

    class ComViewHolder extends RecyclerView.ViewHolder {
        TextView tv_time, tv_context, tv_usertime;
        ImageView iv_icon;

        public ComViewHolder(View itemView) {
            super(itemView);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_context = (TextView) itemView.findViewById(R.id.tv_context);
            tv_usertime = (TextView) itemView.findViewById(R.id.tv_usertime);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);

        }
    }

    class BownViewHolder extends RecyclerView.ViewHolder {
        TextView tv_time, tv_context, tv_usertime;
        ImageView iv_icon;

        public BownViewHolder(View itemView) {
            super(itemView);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_context = (TextView) itemView.findViewById(R.id.tv_context);
            tv_usertime = (TextView) itemView.findViewById(R.id.tv_usertime);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TAG_HEAD;
        } else if (position == (datelist.size() - 1)) {
            return TAG_BOWN;
        } else {
            return TAG_ITEM;
        }
    }
}
