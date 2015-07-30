package com.starnamu.airlineschdule.mainslidemenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.starnamu.airlineschdule.MainActivity;
import com.starnamu.projcet.airlineschedule.R;

/**
 * Created by starnamu on 2015-07-30.
 */
public class ChoiceStartAlarmMenu extends LinearLayout {


    public ChoiceStartAlarmMenu(Context context) {
        super(context);
        init(context);
    }

    public ChoiceStartAlarmMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(final Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.mainslidemenu, this, true);

        view.findViewById(R.id.startAlarm).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).startAlart();
            }
        });
        view.findViewById(R.id.stopAlarm).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).stopAlart();
            }
        });
    }
}
