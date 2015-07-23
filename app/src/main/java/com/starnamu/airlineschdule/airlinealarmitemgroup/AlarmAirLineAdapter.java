package com.starnamu.airlineschdule.airlinealarmitemgroup;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.starnamu.airlineschdule.database.AlarmDBControll;
import com.starnamu.airlineschdule.slidinglayout.AirlineItem;

import java.util.ArrayList;

/**
 * Created by starnamu on 2015-07-23.
 */
public class AlarmAirLineAdapter extends BaseAdapter {

    ArrayList<AirlineItem> items;
    Context mContext;
    AlarmDBControll alarmDBControll;
    AlarmItemViewGroup view;
    int mPostion;

    public AlarmAirLineAdapter(Context context, AlarmDBControll alarmDBControll) {
        this.mContext = context;
        this.alarmDBControll = alarmDBControll;
        this.items = alarmDBControll.selectData(null);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        this.mPostion = position;
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            view = new AlarmItemViewGroup(mContext);
        } else {
            view = (AlarmItemViewGroup) convertView;
        }

        if (position % 2 == 0) {
            view.setBackgroundColor(Color.argb(255, 250, 255, 255));
        } else {
            view.setBackgroundColor(Color.argb(255, 240, 255, 255));
        }
        view.setAlarmItems(items.get(position));
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                alarmDBControll.removeData(position);
                onInvalidataMethod();
                return false;
            }
        });
        return view;
    }


    public void onInvalidataMethod() {
        this.notifyDataSetInvalidated();
    }
}
