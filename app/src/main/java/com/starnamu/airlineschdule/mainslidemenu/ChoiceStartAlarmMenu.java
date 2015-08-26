package com.starnamu.airlineschdule.mainslidemenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.starnamu.projcet.airlineschedule.R;

/**
 * Created by starnamu on 2015-07-30.
 */
public class ChoiceStartAlarmMenu extends LinearLayout {

    Button startUpdate;
    Context mContext;

    public ChoiceStartAlarmMenu(Context context) {
        super(context);
        init(context);
    }

    public ChoiceStartAlarmMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mContext = context;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.mainslidemenu, this, true);
/*
        startUpdate = (Button) view.findViewById(R.id.startUpdate);
        startUpdate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //Api를 다시 불러와서 items업데이트 후 Fragment의 Listview를 notify한다.
                AirlineParser airlineParser = new AirlineParser();
            }
        });*/
    }
}
