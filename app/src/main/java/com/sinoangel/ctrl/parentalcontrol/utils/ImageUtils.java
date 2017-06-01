package com.sinoangel.ctrl.parentalcontrol.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.renderscript.Type;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sinoangel.ctrl.parentalcontrol.base.BaseApplication;

/**
 * Created by Z on 2017/5/22.
 */

public class ImageUtils {

    public static void showImgUrl(String url, ImageView iv) {
        if (TextUtils.isEmpty(url))
            return;
        Glide.with(BaseApplication.getInstance()).load(url).dontAnimate().into(iv);
    }

    public static Bitmap getBulrBit(Window window) {
        return blurBitmap(myShot(window), 3);
    }

    private static Bitmap blurBitmap(Bitmap bitmap, float radius) {
        //Create renderscript

        RenderScript rs = RenderScript.create(BaseApplication.getInstance());

        //Create allocation from Bitmap
        Allocation allocation = Allocation.createFromBitmap(rs, bitmap);

        Type t = allocation.getType();

        //Create allocation with the same type
        Allocation blurredAllocation = Allocation.createTyped(rs, t);

        //Create script
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        //Set blur radius (maximum 25.0)
        blurScript.setRadius(radius);
        //Set input for script
        blurScript.setInput(allocation);
        //Call script for output allocation
        blurScript.forEach(blurredAllocation);

        //Copy script result into bitmap
        blurredAllocation.copyTo(bitmap);

        //Destroy everything to free memory
        try {
            blurredAllocation.destroy();
        } catch (Exception e) {
        }

        try {
            allocation.destroy();
        } catch (Exception e) {
        }
        try {
            t.destroy();
        } catch (Exception e) {
        }
        try {
            rs.destroy();
            blurScript.destroy();
        } catch (Exception e) {
        }
        try {
            blurScript.destroy();
        } catch (Exception e) {
        }

        return bitmap;
    }

    //获取屏幕截图
    private static Bitmap myShot(Window window) {
        // 获取windows中最顶层的view
        int hei = AppUtils.getHei() / 10;
        int wei = AppUtils.getWei() / 10;
        Bitmap bmp = Bitmap.createBitmap(wei, hei, Bitmap.Config.ARGB_4444);
        View view = window.getDecorView();

        view.buildDrawingCache();

        // 允许当前窗口保存缓存信息
        view.setDrawingCacheEnabled(true);

        Canvas canvas = new Canvas(bmp);
        Bitmap bitmap = view.getDrawingCache();
        if (bitmap != null && !bitmap.isRecycled())
            canvas.drawBitmap(bitmap, new Rect(0, 50, bitmap.getWidth(), bitmap.getHeight()), new Rect(0, 0, wei, hei), null);
        // 销毁缓存信息
        view.destroyDrawingCache();
        return bmp;
    }
}
