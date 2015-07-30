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

    public AlarmService() {
        this.alarmDBControll = new AlarmDBControll();
        this.items = alarmDBControll.selectData(null);
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
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent intentA;
        ArrayList<PendingIntent> senderArray = new ArrayList<PendingIntent>();
        PendingIntent sender;

        for (int i = 0; i > items.size(); i++) {

            int TimeOfItem = setTimeOfItem(items.get(i).getStriItem(5));
            intentA = new Intent(this, AlarmReceiver.class);
            sender = PendingIntent.getBroadcast(this, TimeOfItem, intentA, 0);

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, TimeOfItem);

            am.set(AlarmManager.RTC, calendar.getTimeInMillis(), sender);
            senderArray.add(sender);
            Log.i("알람 서비스 실행", "알람서비스가 실행 되었습니다");
        }
        return super.onStartCommand(intent, flags, startId);
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
    }
}
