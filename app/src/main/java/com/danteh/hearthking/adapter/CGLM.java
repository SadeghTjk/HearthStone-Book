package com.danteh.hearthking.adapter;

import android.content.Context;

import android.util.AttributeSet;

import androidx.recyclerview.widget.GridLayoutManager;

public class CGLM extends GridLayoutManager {
    @Override
    public boolean supportsPredictiveItemAnimations() {
        return false;
    }

    public CGLM(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CGLM(Context context, int spanCount) {
        super(context, spanCount);
    }

    public CGLM(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }
}
