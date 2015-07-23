package com.starnamu.airlineschdule.airlinealarmitemgroup;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.starnamu.airlineschdule.slidinglayout.AirlineItem;
import com.starnamu.projcet.airlineschedule.R;

/**
 * Created by starnamu on 2015-07-23.
 */
public class AlarmItemViewGroup extends LinearLayout {

    public TextView textView02, textView03, textView04, textView05, textView06, textView07;

    public AlarmItemViewGroup(Context context) {
        super(context);
        init(context);
    }

    public AlarmItemViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private String stringFormConversion(String str) {
        String Hour = str.substring(0, 2);
        String Minute = str.substring(2, 4);
        String timeChange = String.format("%s  :  %s", Hour, Minute);
        return timeChange;
    }

    public void setAlarmItems(AirlineItem item) {

        String flightId = item.getStriItem(3);
        textView02.setText(flightId);

        String scheduleDateTime = item.getStriItem(4);
        textView03.setText(stringFormConversion(scheduleDateTime));

        String estimatedDateTime = item.getStriItem(5);
        textView04.setText(stringFormConversion(estimatedDateTime));

        String gatenumber = item.getStriItem(7);
        textView05.setText(gatenumber);

        String remark = item.getStriItem(9);
        textView06.setText(remark);

        String carousel = item.getStriItem(8);
        textView07.setText(carousel);
    }

    private void init(Context context) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listviewitem, this, true);

        textView02 = (TextView) view.findViewById(R.id.textView02);
        textView03 = (TextView) view.findViewById(R.id.textView03);
        textView04 = (TextView) view.findViewById(R.id.textView04);
        textView05 = (TextView) view.findViewById(R.id.textView05);
        textView06 = (TextView) view.findViewById(R.id.textView06);
        textView07 = (TextView) view.findViewById(R.id.textView07);

        view.findViewById(R.id.slidingLayout).setVisibility(GONE);
    }
}
