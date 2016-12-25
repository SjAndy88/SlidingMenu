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

    static int getLayoutDirection(View view) {
        return ViewCompat.getLayoutDirection(view);
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
