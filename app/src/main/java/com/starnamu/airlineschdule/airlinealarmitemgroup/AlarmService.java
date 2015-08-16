package com.starnamu.airlineschdule.airlinealarmitemgroup;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.starnamu.airlineschdule.database.AlarmDBControll;
import com.starnamu.airlineschdule.slidinglayout.AirlineItem;

import java.util.ArrayList;
import java.util.Calendar;

public class AlarmService extends Service {

    AlarmDBControll alarmDBControll;
    ArrayList<AirlineItem> items;
    AlarmManager am;

    private static final String TAG = "TestAlarmManagerActivity";

    public AlarmService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        this.alarmDBControll = new AlarmDBControll();
        this.items = alarmDBControll.selectData(null);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        setAlarm();
        return super.onStartCommand(intent, flags, startId);
    }

    private void setAlarm() {
        Intent intentA;
        ArrayList<PendingIntent> senderArray = new ArrayList<PendingIntent>();
        PendingIntent sender;
        Log.i("알람 서비스의 갯수", Integer.toString(items.size()));

        String strinTime = new java.text.SimpleDateFormat("HHmmss").format(new java.util.Date());
        int intintime = setTimeOfItem(strinTime);
        Log.i("현재시간", Integer.toString(intintime));

        for (int i = 0; i < items.size(); i++) {
            int TimeOfItem = setTimeOfItem(items.get(i).getStriItem(5));
            Log.i("알람 설정시간", Integer.toString(TimeOfItem));
            String AlarmAction = items.get(i).getStriItem(3);
            int setTime = TimeOfItem - intintime;
            Log.i(items.get(i).getStriItem(3) + "의 알람은", Integer.toString(setTime) + "초 후 작동함니다.");

            intentA = new Intent(AlarmService.this, AlarmReceiver.class);
            intentA.setAction(AlarmAction);
            sender = PendingIntent.getBroadcast(this, TimeOfItem, intentA, 0);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, TimeOfItem);
            am.set(AlarmManager.RTC, calendar.getTimeInMillis(), sender);
            senderArray.add(sender);
        }
    }

    // 알람 해제
    private void releaseAlarm() {
        Log.i(TAG, "releaseAlarm()");
        for (int i = 0; i < items.size(); i++) {
            int TimeOfItem = setTimeOfItem(items.get(i).getStriItem(5));
            Log.i("알람 설정시간", Integer.toString(TimeOfItem));
            String AlarmAction = items.get(i).getStriItem(3);

            Intent intentToRele = new Intent(AlarmService.this, AlarmReceiver.class);
            intentToRele.setAction(AlarmAction);
            PendingIntent pIntent = PendingIntent.getActivity(this, TimeOfItem, intentToRele, 0);
            am.cancel(pIntent);
            pIntent.cancel();

            isAlarmActivated(TimeOfItem, AlarmAction);
        }
    }

    private boolean isAlarmActivated(int alarmId, String AlarmAction) {
        boolean result;
        PendingIntent pIntent;

        Intent intentToSend = new Intent(AlarmService.this, AlarmReceiver.class);
        intentToSend.setAction(AlarmAction);

        pIntent = PendingIntent.getBroadcast(this, alarmId, intentToSend, 0);

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseAlarm();
    }
}