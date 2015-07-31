package com.starnamu.airlineschdule;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.starnamu.airlineschdule.airlinealarmitemgroup.AlarmService;
import com.starnamu.airlineschdule.comm.CommonConventions;
import com.starnamu.airlineschdule.database.SchduldDBControll;
import com.starnamu.airlineschdule.mainslidemenu.ChoiceStartAlarmMenu;
import com.starnamu.airlineschdule.slidinglayout.AirlineItem;
import com.starnamu.projcet.airlineschedule.R;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements CommonConventions {

    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[] = {"도착편", "출발편", "OAL 도착", "OAL 출발", "정보", "알람", "지도"};
    int Numboftabs = Titles.length;
    DrawerLayout dlDrawer;
    ActionBarDrawerToggle dtToggle;
    ArrayList<AirlineItem> items;
    private static Context mainContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainContext = this;
        items = Intro_Activity.items;
        try {
            stateUrlConnation();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        thread.start();
    }

    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            new SchduldDBControll(getApplicationContext(), items);
        }
    });

    public static Context getMainActivityContext() {
        return mainContext;
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
        pager = (ViewPager) findViewById(R.id.pager);
        adapter = new ViewPagerAdapter(getSupportFragmentManager(), Titles, Numboftabs, items);
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
        ChoiceStartAlarmMenu choiceStartAlarmMenu = new ChoiceStartAlarmMenu(this);
        FrameLayout drawer = (FrameLayout) findViewById(R.id.drawer);
        drawer.addView(choiceStartAlarmMenu);
    }

    public void startAlart() {
        Intent intent = new Intent(this, AlarmService.class);
        startService(intent);
    }

    public void stopAlart() {
        Intent intent = new Intent(this, AlarmService.class);
        stopService(intent);
        Log.i("알람 서비스 중지", "알람서비스가 중지 되었습니다");
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