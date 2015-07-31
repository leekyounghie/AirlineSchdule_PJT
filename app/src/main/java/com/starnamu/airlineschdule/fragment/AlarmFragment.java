package com.starnamu.airlineschdule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.starnamu.airlineschdule.airlinealarmitemgroup.AlarmAirLineAdapter;
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
                onMyNotifiyInvalidated();
            }
        });

        return v;
    }

    public void onMyNotifiyInvalidated() {
        alarmDBControll.allRemoveData(AlarmTableName);
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
}