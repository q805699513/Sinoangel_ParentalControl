package com.sinoangel.ctrl.parentalcontrol.curriculumschedule.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sinoangel.ctrl.parentalcontrol.R;

import java.util.List;

/**
 * Created by Z on 2017/6/7.
 */

public class TakeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TAG_TITLE = 100;
    private static final int TAG_INPUT = 200;
    private static final int TAG_CONTEXT = 300;
    private static final int TAG_RECOMMEND = 400;


    private Activity activity;

    private List<Object> list = null;

    public void setData(List<Object> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = new View(parent.getContext());
        switch (viewType) {
            case TAG_TITLE:
                return new TitleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_take_title, null));
            case TAG_INPUT:
                return new InputViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_take_input, null));
            case TAG_CONTEXT:
                return new ContextViewHolder(view);
            default:
                return new RecommendViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TitleViewHolder) {

        } else if (holder instanceof InputViewHolder) {

        } else if (holder instanceof ContextViewHolder) {

        } else {

        }
    }

    @Override
    public int getItemCount() {
        return list == null ? 10 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TAG_TITLE;
        else if (position == 1)
            return TAG_INPUT;
        else if (position == 9)
            return TAG_CONTEXT;
        else
            return TAG_RECOMMEND;
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {
        public TitleViewHolder(View itemView) {
            super(itemView);
        }
    }

    class InputViewHolder extends RecyclerView.ViewHolder {
        public InputViewHolder(View itemView) {
            super(itemView);
        }
    }

    class ContextViewHolder extends RecyclerView.ViewHolder {
        public ContextViewHolder(View itemView) {
            super(itemView);
        }
    }

    class RecommendViewHolder extends RecyclerView.ViewHolder {
        public RecommendViewHolder(View itemView) {
            super(itemView);
        }
    }

}
