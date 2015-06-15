package com.starnamu.airlineschdule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.starnamu.projcet.airlineschedule.R;
import com.starnamu.airlineschdule.comm.CommonConventions;
import com.starnamu.airlineschdule.parser.AirLineAdapter;
import com.starnamu.airlineschdule.parser.AirlineItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Edwin on 15/02/2015.
 */
public class ArrivalAirlineFragment extends Fragment implements CommonConventions {

    public ListView ArrivalAirlineListView;
    public AirLineAdapter airlineAdapter;
    ArrayList<AirlineItem> ItemList;
    ArrayList<AirlineItem> items;
    int SetTime = 0;

    public ArrivalAirlineFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.arrivalairlinefragment, container, false);
        ArrivalAirlineListView = (ListView) v.findViewById(R.id.ArrivalAirlineListView);
        ItemList = new ArrayList<>();
        airlineAdapter = new AirLineAdapter(getActivity());
        Log.i("Arr", "Strar");

        Bundle bundle = getArguments();
        items = (ArrayList<AirlineItem>) bundle.getSerializable("items");

        if (SetTime <= 0) {
            this.SetTime = currentTime();
        }

        for (int i = 0; i < items.size(); i++) {
            AirlineItem item = items.get(i);
            if (adCheck(item.getStriItem(10))) {
                if (airlineCheck(item.getStriItem(0))) {
                    if (flightCheck(item.getStriItem(3))) {
                        if (timeCheck(item.getStriItem(4)) > SetTime) {
                            ItemList.add(item);
                        }
                    }
                }
            }
            airlineAdapter.setItemList(ItemList);
            ArrivalAirlineListView.setAdapter(airlineAdapter);
        }
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public int currentTime() {
        long time1 = System.currentTimeMillis();

        long time2 = time1 - 7200000L;
        Date date1 = new Date(time1);
        SimpleDateFormat CurTimeFormat1 = new SimpleDateFormat("HHmm");
        String strCurTime1 = CurTimeFormat1.format(date1);

        Date date2 = new Date(time2);
        SimpleDateFormat CurTimeFormat2 = new SimpleDateFormat("HHmm");
        String strCurTime2 = CurTimeFormat2.format(date2);

        if (time2 <= 0) {
            return Integer.parseInt(strCurTime1);
        }
        return Integer.parseInt(strCurTime2);
    }

    public void choiceTime(int choiceTime) {
        this.SetTime = choiceTime;
    }

    /*Arraylist의 지료를 원하는 형태로 걸러낸다.*/
    private boolean adCheck(String airline) {
        if (airline.equals("A")) {
            return true;
        }
        return false;
    }

    private int timeCheck(String time) {
        int intTime = Integer.parseInt(time);
        return intTime;
    }

    private boolean airlineCheck(String airline) {
        if (airline.equals("아시아나항공")) {
            return true;
        }
        return false;
    }

    private boolean flightCheck(String flight) {
        if (flight.length() <= 5) {
            return true;
        }
        return false;
    }
}