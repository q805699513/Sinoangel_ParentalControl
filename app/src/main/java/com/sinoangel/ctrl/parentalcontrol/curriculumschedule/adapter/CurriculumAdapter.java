package com.sinoangel.ctrl.parentalcontrol.curriculumschedule.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.curriculumschedule.CurriculumVedioActivity;
import com.sinoangel.ctrl.parentalcontrol.curriculumschedule.bean.CurriculIndex;
import com.sinoangel.ctrl.parentalcontrol.utils.ImageUtils;
import com.sinoangel.ctrl.parentalcontrol.customview.RecyclerBanner;

/**
 * Created by Z on 2017/6/5.
 */

public class CurriculumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TAG_BANNER = 100;
    private static final int TAG_MENU = 200;
    private static final int TAG_ITEM = 300;

    private Activity activity;
    private CurriculIndex.DataBean cidb;

    public void setData(CurriculIndex.DataBean data) {
        cidb = data;
        notifyDataSetChanged();
    }

    public CurriculumAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case TAG_BANNER:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, null);

                return new BannerViewHoler(view);
            case TAG_MENU:
                RecyclerView recyclerView = new RecyclerView(parent.getContext());
                LinearLayoutManager llm = new LinearLayoutManager(parent.getContext(), LinearLayoutManager.HORIZONTAL, false);
                recyclerView.setLayoutManager(llm);
                return new MenuViewHoler(recyclerView);
            case TAG_ITEM:
                View bView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_img, null);
                return new ItemViewHoler(bView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof BannerViewHoler) {


        } else if (holder instanceof MenuViewHoler) {
//            MenuViewHoler mholder = (MenuViewHoler) holder;


        } else {
            ItemViewHoler iholder = (ItemViewHoler) holder;
            ImageUtils.showImgUrl("http://www.jitu5.com/uploads/allimg/121120/260529-121120232T546.jpg", iholder.iv_img);

        }

    }

    @Override
    public int getItemCount() {
        if (cidb != null)
            return cidb.getCont() == null ? 0 : cidb.getCont().size() + 2;
        else
            return 0;
    }

    class BannerViewHoler extends RecyclerView.ViewHolder {

        RecyclerBanner recyclerBanner;

        public BannerViewHoler(View itemView) {
            super(itemView);
            recyclerBanner = (RecyclerBanner) itemView.findViewById(R.id.rb_banner);

            recyclerBanner.setDatas(cidb.getBan());

            recyclerBanner.setOnPagerClickListener(new RecyclerBanner.OnPagerClickListener() {
                @Override
                public void onClick(RecyclerBanner.BannerEntity entity) {
                    Intent i = new Intent(activity, CurriculumVedioActivity.class);
                    i.putExtra("vid", "XMTM2MDQ5MzUxMg==");
                    activity.startActivity(i);
                }
            });
        }
    }

    class MenuViewHoler extends RecyclerView.ViewHolder {

        private RecyclerView recyclerView;

        public MenuViewHoler(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView;

            recyclerView.setAdapter(new MenuViewHolerAdapter(cidb.getNav()));

        }
    }

    class ItemViewHoler extends RecyclerView.ViewHolder {

        ImageView iv_img, iv_icon;
        TextView tv_con;

        public ItemViewHoler(View itemView) {
            super(itemView);
            iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tv_con = (TextView) itemView.findViewById(R.id.tv_con);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TAG_BANNER;
        } else if (position == 1) {
            return TAG_MENU;
        } else {
            return TAG_ITEM;
        }
    }
}
