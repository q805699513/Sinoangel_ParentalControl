package com.sinoangel.ctrl.parentalcontrol.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.NumberPicker;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.utils.ImageUtils;

import java.lang.reflect.Field;
import java.util.Calendar;

public class DatePickerDialog extends Dialog {


    private int[] mInitShowDates;
    private NumberPicker mYearView, mMonthView, mDayView;
    private int mYear, mMonth, mDay;

    private int mSystemYear, mSystemMonth, mSystemDay;

    private OnClickListener mConfirmClickListener;

    private Activity activity;

    public DatePickerDialog(Activity context, int[] aInitShowDates) {
        super(context, R.style.DateDialog);
        mInitShowDates = aInitShowDates;
        activity = context;
    }

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.birthdaydialog);
        mYearView = (NumberPicker) findViewById(R.id.np1);
        mMonthView = (NumberPicker) findViewById(R.id.np2);
        mDayView = (NumberPicker) findViewById(R.id.np3);

        mYearView.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mMonthView.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        mDayView.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        Calendar lCalendar = Calendar.getInstance();
        mSystemYear = lCalendar.get(Calendar.YEAR);
        mSystemMonth = lCalendar.get(Calendar.MONTH) + 1;
        mSystemDay = lCalendar.get(Calendar.DAY_OF_MONTH);

        if (mInitShowDates != null) {// 如果初始显示的时间大于当前取到的系统时间，就以当前初始显示时间作为上限值
            if (mInitShowDates[0] > mSystemYear) {
                mSystemYear = mInitShowDates[0];
                mSystemMonth = mInitShowDates[1];
                mSystemDay = mInitShowDates[2];
            } else if (mInitShowDates[0] == mSystemYear) {
                if (mInitShowDates[1] > mSystemMonth) {
                    mSystemMonth = mInitShowDates[1];
                    mSystemDay = mInitShowDates[2];
                } else if (mInitShowDates[1] == mSystemMonth && mInitShowDates[2] > mSystemDay) {
                    mSystemDay = mInitShowDates[2];
                }
            }
        }

        mYear = mInitShowDates != null ? mInitShowDates[0] : mSystemYear;
        mMonth = mInitShowDates != null ? mInitShowDates[1] : mSystemMonth;
        mDay = mInitShowDates != null ? mInitShowDates[2] : mSystemDay;


        mYearView.setMinValue(1920);
        mYearView.setMaxValue(mSystemYear);

        mMonthView.setMinValue(1);
        mMonthView.setMaxValue(mYear == mSystemYear ? mSystemMonth : 12);

        mDayView.setMinValue(1);
        mDayView.setMaxValue(mYear == mSystemYear && mMonth == mSystemMonth ? mSystemDay : getMaxDay(mYear, mMonth));

        mYearView.setValue(mYear);
        mMonthView.setValue(mMonth);
        mDayView.setValue(mDay);

        mYearView.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker arg0, int arg1, int arg2) {
                mYear = mYearView.getValue();

                mMonthView.setMaxValue(mYear == mSystemYear ? mSystemMonth : 12);
                mMonth = mMonthView.getValue();

                mDayView.setMaxValue(mYear == mSystemYear && mMonth == mSystemMonth ? mSystemDay : getMaxDay(mYear, mMonth));
                mDay = mDayView.getValue();
            }
        });

        mMonthView.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker arg0, int arg1, int arg2) {
                mMonth = mMonthView.getValue();

                mDayView.setMaxValue(mYear == mSystemYear && mMonth == mSystemMonth ? mSystemDay : getMaxDay(mYear, mMonth));
                mDay = mDayView.getValue();
            }
        });

        mDayView.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker arg0, int arg1, int arg2) {
                mDay = mDayView.getValue();
            }
        });


        setNumberPickerDividerColor(mYearView);
        setNumberPickerDividerColor(mMonthView);
        setNumberPickerDividerColor(mDayView);
        View lConfirmView = findViewById(R.id.dialog_confirm);
        if (mConfirmClickListener != null && lConfirmView != null) {
            lConfirmView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    mConfirmClickListener.onClick(DatePickerDialog.this, DialogInterface.BUTTON_NEGATIVE);
                    dismiss();
                }
            });
        }

        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);

        ((ImageView) findViewById(R.id.background)).setImageBitmap(ImageUtils.getBulrBit(activity.getWindow()));
    }

    private int getMaxDay(int aYear, int aMonth) {
        if (aMonth == 1 || aMonth == 3 || aMonth == 5 || aMonth == 7 || aMonth == 8 || aMonth == 10 || aMonth == 12) {
            return 31;
        }
        if (aMonth == 4 || aMonth == 6 || aMonth == 9 || aMonth == 11) {
            return 30;
        }
        if (aYear % 4 == 0 && aYear % 100 != 0 || aYear % 400 == 0) {
            return 29;
        }
        return 28;
    }

    public void setConfirmButton(OnClickListener listener) {
        this.mConfirmClickListener = listener;
    }

    public String getDate() {
        return mYear + "-" + mMonth + "-" + mDay;
    }

    public int getYear() {
        return mYear;
    }

    public int getMonth() {
        return mMonth;
    }

    public int getDay() {
        return mDay;
    }

    private void setNumberPickerDividerColor(NumberPicker numberPicker) {
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields)
            if ("mSelectionDivider".equals(pf.getName())) {
                pf.setAccessible(true);
                try {//设置分割线的颜色值
                    pf.set(numberPicker, new ColorDrawable(Color.TRANSPARENT));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
    }
}
