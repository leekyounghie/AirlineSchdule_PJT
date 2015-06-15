package com.starnamu.airlineschdule.choiceoption;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.starnamu.projcet.airlineschedule.R;

/**
 * Created by starnamu on 2015-05-30.
 */
public class OptionFragmentLineLayout extends LinearLayout {
    public OptionFragmentLineLayout(Context context) {
        super(context);
        init(context);
    }

    public OptionFragmentLineLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.optionfragmentlinelayout, this, true);
    }
}
