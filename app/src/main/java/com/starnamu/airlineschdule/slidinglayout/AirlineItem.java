package com.starnamu.airlineschdule.slidinglayout;


import com.starnamu.airlineschdule.comm.CommonConventions;

import java.io.Serializable;

/**
 * Created by starnamu on 2015-05-08.
 */
public class AirlineItem implements CommonConventions, Serializable {


    String[] StrItem = new String[PARSERITEMGROUP.length];

    public AirlineItem(String[] strings) {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i] == null) {
                this.StrItem[i] = "";
            } else {
                this.StrItem[i] = strings[i];
            }
        }
    }

    public String getStriItem(int index) {
        return StrItem[index];
    }
}
