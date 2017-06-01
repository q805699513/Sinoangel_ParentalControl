package com.sinoangel.ctrl.parentalcontrol.index.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.account.kids.CreateKidActivity;
import com.sinoangel.ctrl.parentalcontrol.index.KidAccountActivity;
import com.sinoangel.ctrl.parentalcontrol.account.kids.bean.KidBean;
import com.sinoangel.ctrl.parentalcontrol.utils.Constant;
import com.sinoangel.ctrl.parentalcontrol.utils.ImageUtils;

import java.util.List;

/**
 * Created by Z on 2017/5/23.
 */

public class KidAccountAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private static final int TYEP_COMM = 11;
    private static final int TYEP_ADD = 22;

    private List<KidBean.DataBean> dataBeanList;
    private KidAccountActivity kidAccountActivity;

    public void setData(List<KidBean.DataBean> data) {
        dataBeanList = data;
        notifyDataSetChanged();
    }

    public KidAccountAdapter(KidAccountActivity kidAccountActivity) {
        this.kidAccountActivity = kidAccountActivity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYEP_COMM) {
            view = LayoutInflater.from(kidAccountActivity).inflate(R.layout.item_kidaccount_adapter, null);
            return new ViewHolder_Com(view);
        } else {
            view = LayoutInflater.from(kidAccountActivity).inflate(R.layout.item_add_icon, null);
            return new ViewHolder_Bot(view);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder bvHolder, int position) {

        if (bvHolder instanceof ViewHolder_Com) {
            ViewHolder_Com holder = (ViewHolder_Com) bvHolder;
            KidBean.DataBean kidBean = dataBeanList.get(position);
            holder.dataBean = kidBean;

            if (kidBean.getUsericon() != null && kidBean.getUsericon().length() == 1) {
                int picId = 0;
                try {
                    picId = Integer.parseInt(kidBean.getPic_id());
                } catch (Exception e) {
                }
                holder.iv_head.setImageResource(Constant.KidHeadIdList[picId]);
            } else
                ImageUtils.showImgUrl(kidBean.getUsericon(), holder.iv_head);
            switch (kidBean.getTheme_id()) {
                case "0":
                    holder.iv_theme.setImageResource(R.mipmap.icon_theme1);
                    break;
                case "1":
                    holder.iv_theme.setImageResource(R.mipmap.icon_theme2);
                    break;
                default:
                    holder.iv_theme.setImageResource(R.mipmap.icon_theme3);
                    break;
            }
//            ImageUtils.showImgUrl(kidBean.getTheme_id(), holder.iv_theme);
            holder.iv_name.setText(kidBean.getNickname());
            holder.tv_birthday.setText(kidBean.getBirthday());
            if (TextUtils.equals("0", kidBean.getSex()))
                holder.tv_sex.setText(R.string.register_user_boy);
            else
                holder.tv_sex.setText(R.string.register_user_girl);
        }

    }

    @Override
    public int getItemCount() {
        return dataBeanList == null ? 0 : dataBeanList.size();
    }

    @Override
    public void onClick(View v) {
        KidBean.DataBean dataBean = (KidBean.DataBean) v.getTag(R.id.tag_click_id);
        switch (v.getId()) {
            case R.id.iv_edit:
                Intent intent = new Intent(kidAccountActivity, CreateKidActivity.class);
                intent.putExtra(Constant.USERBEAN, dataBean);
                kidAccountActivity.startActivity(intent);
                break;
            case R.id.iv_delete:

                break;
            case R.id.rl_box:
                kidAccountActivity.startActivity(new Intent(kidAccountActivity, CreateKidActivity.class));
                break;
        }

    }

    class ViewHolder_Com extends RecyclerView.ViewHolder {
        public ImageView iv_head, iv_theme;
        private TextView iv_name, tv_birthday, tv_sex;
        private View iv_edit, iv_delete;

        public KidBean.DataBean dataBean;

        public ViewHolder_Com(View itemView) {
            super(itemView);
            iv_head = (ImageView) itemView.findViewById(R.id.iv_head);
            iv_theme = (ImageView) itemView.findViewById(R.id.iv_theme);
            iv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_birthday = (TextView) itemView.findViewById(R.id.tv_birthday);
            tv_sex = (TextView) itemView.findViewById(R.id.tv_sex);

            iv_edit = itemView.findViewById(R.id.iv_edit);
            iv_delete = itemView.findViewById(R.id.iv_delete);
            iv_edit.setOnClickListener(KidAccountAdapter.this);
            iv_delete.setOnClickListener(KidAccountAdapter.this);

            iv_edit.setTag(R.id.tag_click_id, dataBean);
            iv_delete.setTag(R.id.tag_click_id, dataBean);
        }

    }

    class ViewHolder_Bot extends RecyclerView.ViewHolder {
        View rl_box;

        public ViewHolder_Bot(View itemView) {
            super(itemView);
            rl_box = itemView.findViewById(R.id.rl_box);

            rl_box.setOnClickListener(KidAccountAdapter.this);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == dataBeanList.size()-1) {
            return TYEP_ADD;
        } else {
            return TYEP_COMM;
        }
    }
}
