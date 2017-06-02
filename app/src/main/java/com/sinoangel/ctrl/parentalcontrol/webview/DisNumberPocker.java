package com.sinoangel.ctrl.parentalcontrol.webview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;

/**
 * Created by pc on 2016/5/25.
 */
public class DisNumberPocker extends NumberPicker {

    public DisNumberPocker(Context context) {
        super(context);
    }

    public DisNumberPocker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DisNumberPocker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public void addView(View child) {
        super.addView(child);
        updateView(child);
    }

    @Override
    public void addView(View child, int index,
                        android.view.ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        updateView(child);
    }

    @Override
    public void addView(View child, android.view.ViewGroup.LayoutParams params) {
        super.addView(child, params);
        updateView(child);
    }

    public void updateView(View view) {
        if (view instanceof EditText) {
            //这里修改字体的属性
            ((EditText) view).setTextColor(Color.parseColor("#FFFFFF"));
            ((EditText) view).setTextSize(22);
        }
    }
}
