package com.sinoangel.ctrl.parentalcontrol.utils;

import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.sinoangel.ctrl.parentalcontrol.R;
import com.sinoangel.ctrl.parentalcontrol.base.BaseApplication;

/**
 * Created by Z on 2017/5/22.
 */

public class BtnAnmiUtils {

    public void setBtnAnmi(View v) {
        v.setOnTouchListener(vot);
    }

    private View.OnTouchListener vot = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            //设置Animation
            Animation animDwon = AnimationUtils.loadAnimation(BaseApplication.getInstance(), R.anim.btn_down);
            Animation animUp = AnimationUtils.loadAnimation(BaseApplication.getInstance(), R.anim.btn_up);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    v.startAnimation(animDwon);
                    animDwon.setFillAfter(true);
                    break;

                case MotionEvent.ACTION_UP:
                    v.startAnimation(animUp);
                    animUp.setFillAfter(true);
                    break;
            }
            return false;    //这时必须返回true，不然 MotionEvent.ACTION_UP 没效果
        }
    };
}
