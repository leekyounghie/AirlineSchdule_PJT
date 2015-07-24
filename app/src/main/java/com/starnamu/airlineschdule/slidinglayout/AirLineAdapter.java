package com.starnamu.airlineschdule.slidinglayout;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by starnamu on 2015-05-08.
 */
public class AirLineAdapter extends BaseAdapter {

    ArrayList<AirlineItem> items = new ArrayList<>();
    Context mContext;
    AirlineItemView view;

    public AirLineAdapter(Context context) {
        this.mContext = context;
    }

    public void setItemList(ArrayList<AirlineItem> items) {

        this.items = items;
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AirlineItemView view = null;
        if (convertView == null) {
            view = new AirlineItemView(mContext);
        } else {
            view = (AirlineItemView) convertView;
            //재활용시 재활용되는 View의 속성도 재활용 됨으로 재활용 되는 View를 초기화 해줄 필요가 있다.
            view.slidingLayoutView.setVisibility(View.GONE);
        }
        if (position % 2 == 0) {
            view.setBackgroundColor(Color.argb(255, 250, 255, 255));
        } else {
            view.setBackgroundColor(Color.argb(255, 240, 255, 255));
        }
        view.setAirlineItem(items.get(position));

        return view;
    }
}
