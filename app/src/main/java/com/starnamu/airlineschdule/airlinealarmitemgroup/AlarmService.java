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
    private static final String INTENT_ACTION = "arabiannight.tistory.com.alarmmanager";


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
        /*this.stopSelf();*/

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

            /*알람유뮤확인*/
            boolean stateAlarm = isAlarmActivated(TimeOfItem, AlarmAction);

            if (stateAlarm == true) {
                this.stopSelf();
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(System.currentTimeMillis());
            calendar.add(Calendar.SECOND, TimeOfItem);
            am.set(AlarmManager.RTC, calendar.getTimeInMillis(), sender);
            senderArray.add(sender);
        }
    }

    // 알람 해제
    private void releaseAlarm(Context context) {
        Log.i(TAG, "releaseAlarm()");
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent Intent = new Intent(INTENT_ACTION);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, Intent, 0);
        alarmManager.cancel(pIntent);

        // 주석을 풀면 먼저 실행되는 알람이 있을 경우, 제거하고
        // 새로 알람을 실행하게 된다. 상황에 따라 유용하게 사용 할 수 있다.
//      alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 3000, pIntent);
    }

    private boolean isAlarmActivated(int alarmId, String AlarmAction) {
        boolean result;
        PendingIntent pIntent;

        Intent intentToSend = new Intent(AlarmService.this, AlarmReceiver.class);
        intentToSend.setAction(AlarmAction);

        pIntent = PendingIntent.getBroadcast(this, alarmId, intentToSend, PendingIntent.FLAG_NO_CREATE);

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
        Intent intent = new Intent(this, AlarmService.class);
        this.startService(intent);
    }
}