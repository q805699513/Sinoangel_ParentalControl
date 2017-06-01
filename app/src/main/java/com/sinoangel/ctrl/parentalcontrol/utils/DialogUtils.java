package com.sinoangel.ctrl.parentalcontrol.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.base.BaseApplication;
import com.sinoangel.ctrl.parentalcontrol.customview.WheelView;

import java.util.List;

/**
 * Created by Z on 2017/5/17.
 */

public class DialogUtils {

    private static Dialog progressDialog;//进度条弹窗
    private static Dialog dialog;//其他弹窗

    /**
     * 显示等待进度条
     */
    public static void showProgressDialog(Context context, boolean isCancelable) {
        if (progressDialog == null) {
            progressDialog = new Dialog(context, R.style.App_Dialog);
            progressDialog.setCancelable(isCancelable);
            progressDialog.setContentView(R.layout.dialog_progress_layout);
//            WindowManager.LayoutParams lp = progressDialog.getWindow().getAttributes();
//            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//            progressDialog.getWindow().setAttributes(lp);

            progressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    progressDialog = null;
                }
            });

            progressDialog.show();
        } else if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }


    /**
     * 隐藏等待进度条
     */
    public static void dismissProgressDialog() {
        try {
            if (null != progressDialog && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
        }
    }


    /**
     * 显示一个单选弹窗
     */
    public static void showOneBtnDialog(Activity activity, String str, boolean isCancelable, final DialogBtnListener dialogBtnListener) {

        if (dialog == null && !activity.isDestroyed()) {
            dialog = new Dialog(activity, R.style.App_Dialog);
            dialog.setCancelable(isCancelable);
            dialog.setContentView(R.layout.dialog_onebtn_layout);

            ((ImageView) dialog.findViewById(R.id.background)).setImageBitmap(ImageUtils.getBulrBit(activity.getWindow()));

            ((TextView) dialog.findViewById(R.id.tv_title)).setText(str);

            if (dialogBtnListener != null) {
                dialog.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBtnListener.onBtn_OK();
                        dialog.dismiss();
                    }
                });
            }

            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setAttributes(lp);

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface di) {
                    dialog = null;
                }
            });

            dialog.show();//可能抛异常
        } else {
            dialog.dismiss();
        }
    }

    /**
     * 显示双选弹窗
     */
    public static void showTwoBtnDialog(Activity activity, String str, boolean isCancelable, final DialogBtnListener dialogBtnListener) {

        if (dialog == null && !activity.isDestroyed()) {
            dialog = new Dialog(activity, R.style.App_Dialog);
            dialog.setCancelable(isCancelable);
            dialog.setContentView(R.layout.dialog_twobtn_layout);

            ((ImageView) dialog.findViewById(R.id.background)).setImageBitmap(ImageUtils.getBulrBit(activity.getWindow()));

            ((TextView) dialog.findViewById(R.id.tv_title)).setText(str);

            if (dialogBtnListener != null) {
                dialog.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBtnListener.onBtn_OK();
                        dialog.dismiss();
                    }
                });

                dialog.findViewById(R.id.btn_no).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogBtnListener.onBtn_NO();
                        dialog.dismiss();
                    }
                });
            }

            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setAttributes(lp);

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface di) {
                    dialog = null;
                }
            });

            dialog.show();//可能抛异常
        } else {
            dialog.dismiss();
        }
    }

    /**
     * 时间列表选择弹窗
     */
    public static void showTimeSelectItemDialog(Activity activity, List<String> lsit, int index, int stringid, final SelectItemDialogListener dialogBtnListener) {

        if (dialog == null && !activity.isDestroyed()) {
            dialog = new Dialog(activity, R.style.App_Dialog);
            dialog.setContentView(R.layout.dialog_selectitem_layout);

            ((ImageView) dialog.findViewById(R.id.background)).setImageBitmap(ImageUtils.getBulrBit(activity.getWindow()));
            ((TextView) dialog.findViewById(R.id.tv_unit)).setText(stringid);

            final WheelView wv_core = (WheelView) dialog.findViewById(R.id.wv_core);
            wv_core.setSeletion(index);
            wv_core.setOffset(2);
            wv_core.setItems(lsit);

            if (dialogBtnListener != null) {
                dialog.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = wv_core.getSeletedIndex();
                        dialogBtnListener.onBtn_OK(pos);
                        dialog.dismiss();
                    }
                });
            }

            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setAttributes(lp);

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface di) {
                    dialog = null;
                }
            });

            dialog.show();//可能抛异常
        } else {
            dialog.dismiss();
        }
    }


    /**
     * 国家列表选择弹窗
     */
    public static void showCountrySelectItemDialog(Activity activity, List<String> lsit, int index, final SelectItemDialogListener dialogBtnListener) {

        if (dialog == null && !activity.isDestroyed()) {
            dialog = new Dialog(activity, R.style.App_Dialog);
            dialog.setContentView(R.layout.dialog_select_countryitem_layout);

            ((ImageView) dialog.findViewById(R.id.background)).setImageBitmap(ImageUtils.getBulrBit(activity.getWindow()));

            final WheelView wv_core = (WheelView) dialog.findViewById(R.id.wv_core);

            wv_core.setOffset(2);//先后顺序不能错
            wv_core.setSeletion(index);
            wv_core.setItems(lsit);

            if (dialogBtnListener != null) {
                dialog.findViewById(R.id.btn_ok).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = wv_core.getSeletedIndex();
                        dialogBtnListener.onBtn_OK(pos);
                        dialog.dismiss();
                    }
                });
            }

            WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setAttributes(lp);

            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface di) {
                    dialog = null;
                }
            });

            dialog.show();//可能抛异常
        } else {
            dialog.dismiss();
        }
    }

    public interface DialogBtnListener {
        void onBtn_OK();

        void onBtn_NO();

    }

    public interface SelectItemDialogListener {
        void onBtn_OK(int pos);
    }
}
