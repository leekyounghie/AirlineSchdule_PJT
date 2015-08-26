package com.starnamu.airlineschdule.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.starnamu.airlineschdule.comm.AirLineItems;
import com.starnamu.airlineschdule.comm.CommonConventions;
import com.starnamu.airlineschdule.comm.CustomTimeControll;
import com.starnamu.airlineschdule.slidinglayout.AirLineAdapter;
import com.starnamu.airlineschdule.slidinglayout.AirlineItem;
import com.starnamu.projcet.airlineschedule.R;

import java.util.ArrayList;

/**
 * Created by Edwin on 15/02/2015.
 */
public class OALDepartureAirLineFragment extends Fragment implements CommonConventions {

    public ListView OalDepartureAirlineListView;
    public AirLineAdapter airlineAdapter;
    ArrayList<AirlineItem> Temitems;
    ArrayList<AirlineItem> items;
    int SetTime;
    CustomTimeControll customTimeControll;

    public OALDepartureAirLineFragment() {
        AirLineItems airLineItems = AirLineItems.getNewInstance();
        this.items = airLineItems.getItems();
        customTimeControll = new CustomTimeControll();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.oaldepartureairlinefragment, container, false);
        OalDepartureAirlineListView = (ListView) v.findViewById(R.id.OalDepartureAirlineListView);

        Temitems = new ArrayList<>();
        airlineAdapter = new AirLineAdapter(getActivity());
        Log.i("OalDep", "Strar");

        if (SetTime <= 0) {
            this.SetTime = customTimeControll.userChoiceTime(7200000L);
        }

        for (int i = 0; i < items.size(); i++) {
            AirlineItem item = items.get(i);
            if (adCheck(item.getStriItem(10))) {
                if (airlineCheck(item.getStriItem(0))) {
                    if (flightCheck(item.getStriItem(3))) {
                        if (timeCheck(item.getStriItem(4)) > SetTime) {
                            Temitems.add(item);
                        }
                    }
                }
            }
            airlineAdapter.setItemList(Temitems);
            OalDepartureAirlineListView.setAdapter(airlineAdapter);
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

    public void choiceTime(int choiceTime) {
        this.SetTime = choiceTime;
    }

    private int timeCheck(String time) {
        int intTime = Integer.parseInt(time);
        return intTime;
    }

    private boolean adCheck(String airline) {
        if (airline.equals("D")) {
            return true;
        }
        return false;
    }

    private boolean airlineCheck(String airline) {
        for (int i = 0; i < AIRLINENAME.length; i++) {
            if (airline.equals(AIRLINENAME[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean flightCheck(String flight) {
        if (flight.length() <= 6) {
            return true;
        }
        return false;
    }
}