package com.starnamu.airlineschdule.airlinealarmitemgroup;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.starnamu.airlineschdule.comm.CommonConventions;
import com.starnamu.airlineschdule.database.AlarmDBControll;
import com.starnamu.airlineschdule.slidinglayout.AirlineItem;
import com.starnamu.projcet.airlineschedule.R;

import java.util.ArrayList;

/**
 * Created by Edwin on 15/02/2015.
 */
public class AlarmFragment extends Fragment implements CommonConventions {

    AlarmDBControll alarmDBControll;
    ListView AlarmListView;
    AlarmAirLineAdapter airlineAdapter;
    ArrayList<AirlineItem> items;

    public AlarmFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.alarmfragment, container, false);
        alarmDBControll = new AlarmDBControll();
        AlarmListView = (ListView) v.findViewById(R.id.AlarmListView);
        items = alarmDBControll.selectData(null);
        airlineAdapter = new AlarmAirLineAdapter(getActivity(), alarmDBControll);

        AlarmListView.setAdapter(airlineAdapter);

        v.findViewById(R.id.onDeletAlarmBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmDBControll.allRemoveData(AlarmTableName);
                onAlarmNotify();
            }
        });
        return v;
    }

    public void onAlarmNotify() {
        airlineAdapter = new AlarmAirLineAdapter(getActivity(), alarmDBControll);
        AlarmListView.setAdapter(airlineAdapter);
        airlineAdapter.notifyDataSetInvalidated();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    class AlarmAirLineAdapter extends BaseAdapter {

        ArrayList<AirlineItem> items;
        AlarmDBControll alarmDBControll;
        AlarmItemViewGroup view;
        int mPostion;
        Context mContext;
        Handler handler = new Handler();

        public AlarmAirLineAdapter(Context context, AlarmDBControll alarmDBControll) {
            this.alarmDBControll = alarmDBControll;
            this.items = alarmDBControll.selectData(null);
            this.mContext = context;
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

            view = new AlarmItemViewGroup(mContext);

            if (position % 2 == 0) {
                view.setBackgroundColor(Color.argb(255, 250, 255, 255));
            } else {
                view.setBackgroundColor(Color.argb(255, 240, 255, 255));
            }
            view.setAlarmItems(items.get(position));
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            alarmDBControll.removeData(position);

                            onAlarmNotify();
                        }
                    });
                    return false;
                }
            });
            return view;
        }
    }
}