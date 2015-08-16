package com.starnamu.airlineschdule.airlinealarmitemgroup;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.starnamu.airlineschdule.comm.FlightNumChange;
import com.starnamu.airlineschdule.database.AlarmDBControll;
import com.starnamu.airlineschdule.slidinglayout.AirlineItem;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by starnamu on 2015-08-13.
 */
public class CustomAlarm {

    private PendingIntent pendingIntent;
    private long now;
    public Date date;
    private AlarmDBControll alarmDBControll;
    private ArrayList<AirlineItem> items;
    private ArrayList<PendingIntent> PendingItems;
    private FlightNumChange flightNumChange;
    private Context mContext;
    private AlarmManager manager;

    public CustomAlarm(Context context) {

        this.now = System.currentTimeMillis();
        this.date = new Date(now);
        mContext = context;
        init();
    }

    private void init() {

        flightNumChange = new FlightNumChange();
        this.alarmDBControll = new AlarmDBControll();
        this.items = alarmDBControll.selectData(null);
        manager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        PendingItems = new ArrayList<PendingIntent>();


        //모든 알람을 삭제한다.
        //DB를 바탕으로 알람을 다시 등록한다.
        //Old Flight를 저장하다가.
        //New가 오면 Old를 삭제하고 New를 제등록 하는 형태?

        for (int i = 0; i < items.size(); i++) {
            releaseAlarm(items.get(i).getStriItem(5));
        }

        for (int i = 0; i < items.size(); i++) {
            setAlarm(items.get(i).getStriItem(5));
        }
    }

    private void setAlarm(String FlightNum) {
        Intent alarmIntent = new Intent(mContext, AlarmReceiver.class);
        int AlarmIndex = flightNumChange.getAscIICode(FlightNum);
        Bundle bundle = new Bundle();
        bundle.putString("notifyId", FlightNum);
        alarmIntent.putExtras(bundle);
        pendingIntent = PendingIntent.getBroadcast(mContext, AlarmIndex, alarmIntent, 0);
        int interval = setTimeOfItem(FlightNum);
        manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + interval, pendingIntent);
        PendingItems.add(pendingIntent);
        Log.i("Alarm Set", FlightNum);
        /*Toast.makeText(mContext, "Alarm Set", Toast.LENGTH_SHORT).show();*/
    }

    private void releaseAlarm(String FlightNum) {
        Intent alarmIntent = new Intent(mContext, AlarmReceiver.class);
        int AlarmIndex = flightNumChange.getAscIICode(FlightNum);
        pendingIntent = PendingIntent.getBroadcast(mContext, AlarmIndex, alarmIntent, PendingIntent.FLAG_NO_CREATE);
        manager.cancel(pendingIntent);
    }

    public void onAlarmCancel() {
        manager.cancel(pendingIntent);
        Toast.makeText(mContext, "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }


    private boolean isAlarmActivated(int alarmId, String AlarmAction) {
        boolean result;
        PendingIntent pIntent;

        Intent intentToSend = new Intent(mContext, AlarmReceiver.class);
        intentToSend.setAction(AlarmAction);
        pIntent = PendingIntent.getBroadcast(mContext, alarmId, intentToSend, PendingIntent.FLAG_NO_CREATE);
        result = pIntent != null;
        Log.i("알람유뮤확인", "[isAlarmActivated] " + result + " - " + pIntent);
        return result;
    }

    private int setTimeOfItem(String str) {
        int hour = Integer.parseInt(str.substring(0, 2));
        int min = Integer.parseInt(str.substring(2, 4));
        int time = (hour * 60 * 60) + (min * 60);
        return time;
    }

    /*
    문자를 유니코드로 변환중
    */
}
