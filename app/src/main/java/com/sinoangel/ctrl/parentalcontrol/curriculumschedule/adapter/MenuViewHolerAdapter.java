package com.sinoangel.ctrl.parentalcontrol.curriculumschedule.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.curriculumschedule.bean.CurriculIndex;

import java.util.List;

/**
 * Created by Z on 2017/6/5.
 */

public class MenuViewHolerAdapter extends RecyclerView.Adapter<MenuViewHolerAdapter.ViewHolder> {

    private List<CurriculIndex.DataBean.NavBean> lcinb;

    public MenuViewHolerAdapter(List<CurriculIndex.DataBean.NavBean> nav) {
        lcinb = nav;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_holder, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CurriculIndex.DataBean.NavBean navBean = lcinb.get(position);


//        holder.tv_con.setText(navBean.getNav_name());
    }

    @Override
    public int getItemCount() {
        return lcinb == null ? 0 : lcinb.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_img;
        TextView tv_con;

        public ViewHolder(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            tv_con = (TextView) itemView.findViewById(R.id.tv_con);
        }
    }
}
