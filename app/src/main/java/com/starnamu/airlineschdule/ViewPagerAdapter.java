package com.starnamu.airlineschdule;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.starnamu.airlineschdule.fragment.ArrivalAirlineFragment;
import com.starnamu.airlineschdule.fragment.DepartureAirLineFragment;
import com.starnamu.airlineschdule.fragment.GoogleMapFragment;
import com.starnamu.airlineschdule.fragment.OALArrivalAirlineFragment;
import com.starnamu.airlineschdule.fragment.OALDepartureAirLineFragment;
import com.starnamu.airlineschdule.parser.AirlineItem;

import java.util.ArrayList;


/**
 * Created by Edwin on 15/02/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    ArrayList<AirlineItem> items;
    FragmentManager fm;


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb, ArrayList<AirlineItem> items) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mTitles.length;
        this.items = items;
        this.fm = fm;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("items", items);

        if (position == 0) {// if the position is 0 we are returning the First tab
            ArrivalAirlineFragment arrivalAirlineFragment = new ArrivalAirlineFragment();
            arrivalAirlineFragment.setArguments(bundle);
            fm.beginTransaction().add(arrivalAirlineFragment, "arr");
            Log.i("Arr", "Strar");

            return arrivalAirlineFragment;
        } else if (position == 1) {            // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
            DepartureAirLineFragment departureAirLineFragment = new DepartureAirLineFragment();
            departureAirLineFragment.setArguments(bundle);
            fm.beginTransaction().add(departureAirLineFragment, "dep");
            Log.i("dep", "Strar");

            return departureAirLineFragment;
        } else if (position == 2) {
            OALArrivalAirlineFragment oalArrivalAirlineFragment = new OALArrivalAirlineFragment();
            oalArrivalAirlineFragment.setArguments(bundle);
            fm.beginTransaction().add(oalArrivalAirlineFragment, "oalarr");
            Log.i("oalarr", "Strar");

            return oalArrivalAirlineFragment;
        } else if (position == 3) {
            OALDepartureAirLineFragment oalDepartureAirLineFragment = new OALDepartureAirLineFragment();
            oalDepartureAirLineFragment.setArguments(bundle);
            fm.beginTransaction().add(oalDepartureAirLineFragment, "oaldep");
            Log.i("oaldep", "Strar");

            return oalDepartureAirLineFragment;
        } else if (position == 4) {
            GoogleMapFragment googleMapFragment = new GoogleMapFragment();
            googleMapFragment.setArguments(bundle);
            fm.beginTransaction().add(googleMapFragment, "goolmf");

            return googleMapFragment;
        }
        return null;
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(
            int position) {
        return Titles[position];
    }

    @Override
    public int getCount() {
        return NumbOfTabs;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public int getItemPosition(Object item) {
        return POSITION_NONE;
    }
}