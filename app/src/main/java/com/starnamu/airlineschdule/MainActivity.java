package com.starnamu.airlineschdule;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.starnamu.airlineschdule.comm.CommonConventions;
import com.starnamu.airlineschdule.fragment.ArrivalAirlineFragment;
import com.starnamu.airlineschdule.fragment.DepartureAirLineFragment;
import com.starnamu.airlineschdule.fragment.OALArrivalAirlineFragment;
import com.starnamu.airlineschdule.parser.AirlineItem;
import com.starnamu.airlineschdule.slidinglayout.SlideLayoutFragment;
import com.starnamu.projcet.airlineschedule.R;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements CommonConventions, SlideLayoutFragment.CustomOnClickListener {

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"도착편", "출발편", "OAL 도착", "OAL 출발", "지도"};
    int Numboftabs = Titles.length;
    DrawerLayout dlDrawer;
    ActionBarDrawerToggle dtToggle;
    ArrayList<AirlineItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = Intro_Activity.items;
        try {
            stateUrlConnation();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void stateUrlConnation() throws InterruptedException {
        boolean state = true;

        while (state) {
            if (items == null) {
                Log.i("from Intro_Activity", "Null");
                state = true;
            }
            if (items != null) {
                startMetrialView();
                state = false;
                Thread.sleep(1000);
                Log.i("from Intro_Activity", "Not Null");
            }
        }
    }

    private void startMetrialView() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        dlDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        dtToggle = new ActionBarDrawerToggle(this, dlDrawer, R.string.app_name, R.string.hello_world);
        dlDrawer.setDrawerListener(dtToggle);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs, items);
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
        tabs.setViewPager(pager);
    }

    public void fragmentReplace(Fragment fragment) {


        final FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();

        transaction.replace(R.id.pager, fragment);
        transaction.commit();
    }

    public void onClicked(int id) {

        switch (id) {

            case R.id.alramBtn:
                ArrivalAirlineFragment arrivalAirlineFragment = new ArrivalAirlineFragment();
                fragmentReplace(arrivalAirlineFragment);
                break;
            case R.id.mapview:
                DepartureAirLineFragment departureAirLineFragment = new DepartureAirLineFragment();
                fragmentReplace(departureAirLineFragment);
                break;
            case R.id.infoBtn:
                OALArrivalAirlineFragment oalArrivalAirlineFragment = new OALArrivalAirlineFragment();
                fragmentReplace(oalArrivalAirlineFragment);
                break;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        /**프로세스 완전 종료 방법*/
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        dtToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        dtToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (dtToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}