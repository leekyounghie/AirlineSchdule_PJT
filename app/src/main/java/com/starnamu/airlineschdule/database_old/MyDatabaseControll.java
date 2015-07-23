package com.starnamu.airlineschdule.database_old;

import android.content.Context;

import com.starnamu.airlineschdule.slidinglayout.AirlineItem;

import java.util.ArrayList;

/**
 * Created by starnamu on 2015-07-11.
 */
public class MyDatabaseControll {

    Context mContext;
    ArrayList<AirlineItem> items;

    public MyDatabaseControll(Context context) {
        this.mContext = context;
        this.items = null;
        thread.start();
    }

    public MyDatabaseControll(Context context, ArrayList<AirlineItem> items) {
        this.mContext = context;
        this.items = items;
        thread.start();
    }

    Thread thread = new Thread() {

    };


}
