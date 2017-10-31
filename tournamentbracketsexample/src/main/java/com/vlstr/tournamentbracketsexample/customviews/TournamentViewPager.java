package com.vlstr.tournamentbracketsexample.customviews;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by Emil on 21/10/17.
 * Edit by vlstr
 */

public class TournamentViewPager extends ViewPager {

    private Context context;

    public TournamentViewPager(Context context) {
        super(context);
        this.context = context;
    }

    public TournamentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int height = 0;

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);

            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));

            int h = child.getMeasuredHeight();

            if (h > height) height = h;

            int screenHeight = context.getResources().getDisplayMetrics().heightPixels;
            if (screenHeight > height)
                height = screenHeight;
        }

        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}