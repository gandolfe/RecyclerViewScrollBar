package com.example.recyclescroller;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

public class CustomLinearLayoutManager extends LinearLayoutManager {
    public CustomLinearLayoutManager(Context context) {
        super(context);
    }

    public CustomLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public CustomLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        LinearSmoothScroller linearSmoothScroller =
                new LinearSmoothScroller(recyclerView.getContext()) {
                    @Override
                    protected int calculateTimeForScrolling(int dx) {

                        Log.w("smoothScrollToPosition","dx:" + dx);
                        // 此函数计算滚动dx的距离需要多久，当要滚动的距离很大时，比如说52000，
                        // 经测试，系统会多次调用此函数，每10000距离调一次，所以总的滚动时间
                        // 是多次调用此函数返回的时间的和，所以修改每次调用该函数时返回的时间的
                        // 大小就可以影响滚动需要的总时间，可以直接修改些函数的返回值，也可以修改
                        // dx的值，这里暂定使用后者.
                        // (See LinearSmoothScroller.TARGET_SEEK_SCROLL_DISTANCE_PX)
                        if (dx > 2000) {
                            dx = 2000;
                        }
                        return super.calculateTimeForScrolling(dx);
                    }
                };
        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }

    public int timeElement = 2000;

    public void setTimeElement(int timeElement){
        this.timeElement = timeElement;
    }
}
