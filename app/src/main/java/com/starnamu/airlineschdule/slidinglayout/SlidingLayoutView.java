package com.starnamu.airlineschdule.slidinglayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.starnamu.projcet.airlineschedule.R;

/**
 * Created by starnamu on 2015-06-24.
 */
public class SlidingLayoutView extends LinearLayout {

    Context nContext;

    public SlidingLayoutView(Context context) {
        super(context);
        init(context);
    }

    public SlidingLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(final Context context) {
        this.nContext = context;
        this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.slidinglayoutview, this, true);
    }


}
