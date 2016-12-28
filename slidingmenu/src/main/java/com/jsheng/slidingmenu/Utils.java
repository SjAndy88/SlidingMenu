package com.jsheng.slidingmenu;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewCompat;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by shengjun on 2016/12/24.
 */

final class Utils {

    static Point point = new Point();

    /**
     * Returns the resolved layout direction for this view.
     *
     * @param view View to get layout direction for
     * @return {ViewCompat.LAYOUT_DIRECTION_RTL} if the layout direction is RTL or returns
     * {ViewCompat.LAYOUT_DIRECTION_LTR} if the layout direction is not RTL.
     *
     * For compatibility, this will return {ViewCompat.LAYOUT_DIRECTION_LTR} if API version
     * is lower than Jellybean MR1 (API 17)
     *
     * @deprecated
     * 此方法在Activity的onCreate方法中返回一直是ltr的方向
     */
    static int getLayoutDirection(View view) {
        return ViewCompat.getLayoutDirection(view);
    }

    static boolean isLtrDirection(Context context) {
        return context.getResources().getBoolean(R.bool.is_ltr);
    }

    static int getScreenWidth(View view) {
        if (point == null || point.y == 0) {
            getScreenPoint(view, point);
        }
        return point.x;
    }

    static int getScreenHeight(View view) {
        if (point == null || point.x == 0) {
           getScreenPoint(view, point);
        }
        return point.y;
    }

    private static void getScreenPoint(View view, Point point) {
        WindowManager wm = (WindowManager) view.getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getSize(point);
    }
}
