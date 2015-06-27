package com.starnamu.airlineschdule.slidinglayout;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.starnamu.airlineschdule.SlidingTabLayout;
import com.starnamu.projcet.airlineschedule.R;

/**
 * Created by starnamu on 2015-06-24.
 */
public class SlidingLayoutView extends LinearLayout {

    Context nContext;
    TextView textView;
    Button alramBtn, mapViewBtn, infoBtn;


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
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slidinglayoutview, this, true);

        alramBtn = (Button) view.findViewById(R.id.alramBtn);
        mapViewBtn = (Button) view.findViewById(R.id.mapViewBtn);
        infoBtn = (Button) view.findViewById(R.id.infoBtn);

        alramBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(nContext, textView.getText() + "도착 5분전 알려줌니다", Toast.LENGTH_LONG).show();
            }
        });

        mapViewBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                ViewPager viewPager = null;
                SlidingTabLayout slidingTabLayout = new SlidingTabLayout(nContext);
                viewPager = slidingTabLayout.getMyViewPager();
                viewPager.setCurrentItem(4);
            }
        });
    }


    public void setMotherView(TextView textView02) {
        this.textView = textView02;
    }


}
