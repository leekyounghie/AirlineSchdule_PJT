package com.starnamu.airlineschdule.comm;

import com.starnamu.airlineschdule.slidinglayout.AirlineItem;

import java.util.ArrayList;

/**
 * Created by starnamu on 2015-08-26.
 */
public class AirLineItems {

    ArrayList<AirlineItem> items = new ArrayList<>();

    private static AirLineItems instance;

    private AirLineItems() {
    }

    public static AirLineItems getNewInstance() {
        if (instance == null) {
            instance = new AirLineItems();
        }
        return instance;
    }

    public void setItems(ArrayList<AirlineItem> items) {
        this.items = items;
    }

    public ArrayList<AirlineItem> getItems() {
        return this.items;
    }
}
