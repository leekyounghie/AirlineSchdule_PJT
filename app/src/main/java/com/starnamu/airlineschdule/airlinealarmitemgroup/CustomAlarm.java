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

import java.text.SimpleDateFormat;
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
        manager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        PendingItems = new ArrayList<PendingIntent>();

        setBeginsAlarm();

        //모든 알람을 삭제한다.
        //DB를 바탕으로 알람을 다시 등록한다.
        //Old Flight를 저장하다가.
        //New가 오면 Old를 삭제하고 New를 제등록 하는 형태?

    }

    private void setBeginsAlarm() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<AirlineItem> items = alarmDBControll.selectData(null);
                for (int i = 0; i < items.size(); i++) {
                    releaseAlarm(items.get(i).getStriItem(3));
                }

                for (int i = 0; i < items.size(); i++) {
                    setAlarm(items.get(i).getStriItem(3), items.get(i).getStriItem(5));
                }
            }
        });
        thread.start();
    }

    public void setAlarm(String FlightNum, String EstimatedDateTime) {
        Intent alarmIntent = new Intent(mContext, AlarmReceiver.class);
        int AlarmIndex = flightNumChange.getAscIICode(FlightNum);
        Bundle bundle = new Bundle();
        bundle.putString("notifyId", FlightNum);
        alarmIntent.putExtras(bundle);
        pendingIntent = PendingIntent.getBroadcast(mContext, AlarmIndex, alarmIntent, 0);
        int interval = setTimeOfItem(EstimatedDateTime);
        manager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + interval, pendingIntent);
        PendingItems.add(pendingIntent);
        Log.i("Alarm Set", FlightNum);
        /*Toast.makeText(mContext, "Alarm Set", Toast.LENGTH_SHORT).show();*/
    }

    public void allReleaseAlarm() {
        ArrayList<AirlineItem> items = alarmDBControll.selectData(null);
        for (int i = 0; i < items.size(); i++) {
            releaseAlarm(items.get(i).getStriItem(5));
            Log.i("CustomAlarm", "모든 알람이 삭제되었습니다");

        }
    }

    public void releaseAlarm(String FlightNum) {
        Intent alarmIntent = new Intent(mContext, AlarmReceiver.class);
        int AlarmIndex = flightNumChange.getAscIICode(FlightNum);
        pendingIntent = PendingIntent.getBroadcast(mContext, AlarmIndex, alarmIntent, PendingIntent.FLAG_NO_CREATE);
        manager.cancel(pendingIntent);
        Log.i("CustomAlarm", "알람이 삭제되었습니다");
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

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdfNow = new SimpleDateFormat("HHmm");
        String strNow = sdfNow.format(date);

        Log.i("현재시간>>>>>>", ">>>>>>>>>>>>>>" + strNow);
        // 현재 시간이 잘나온 상태

        return time - (int) System.currentTimeMillis();
    }
}
