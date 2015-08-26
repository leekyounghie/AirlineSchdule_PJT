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
public class DepartureAirLineFragment extends Fragment implements CommonConventions {

    public ListView DepartureAirlineListView;
    public AirLineAdapter airlineAdapter;
    /*Temitems는 원하는 데이터로 ArrayList로 가공한 ArrayList */
    ArrayList<AirlineItem> ItemList;
    /*items는 모든 항공사 정보가 들어있는 원본 ArrayList`*/
    ArrayList<AirlineItem> items;
    int SetTime;
    CustomTimeControll customTimeControll;

    public DepartureAirLineFragment() {

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
        View v = inflater.inflate(R.layout.departureairlinefragment, container, false);
        DepartureAirlineListView = (ListView) v.findViewById(R.id.DepartureAirlineListView);
        ItemList = new ArrayList<>();
        airlineAdapter = new AirLineAdapter(getActivity());
        Log.i("Dep", "Strar");

        if (SetTime <= 0) {
            this.SetTime = customTimeControll.userChoiceTime(7200000L);
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
            DepartureAirlineListView.setAdapter(airlineAdapter);
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

    /*Arraylist의 지료를 원하는 형태로 걸러낸다.*/
    private boolean adCheck(String airline) {
        if (airline.equals("D")) {
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