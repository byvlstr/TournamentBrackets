package com.vlstr.tournamentbracketsexample.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Emil on 21/10/17.
 * Edit by vlstr
 */

public class UiUtils {
    public static int dpToPx(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

}
