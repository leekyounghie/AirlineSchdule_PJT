package com.starnamu.airlineschdule.awakeprocess;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Service 안드로이드 Application 컴포넌트 중에 하나이며 사용자와 상호작용 하지 않고,
 * Background(화면뒷단)에서 동작하는 컴포넌트
 * Activity 종료후에도 동작하기 위해서 만들어진 컴포넌트(MP3플레이어 기능)
 * Manifests에 <service></service>를 등록한다.
 * Service내부에서는 Thread작업을 해서는 안된다.
 * Service 주기는 onCreate() -> onStartCommand() 순으로 이어짐으로
 * Service가 실행되고 있는 도중에 다시한번  startService()  메서드가 호출되면
 * onStartCommand() 주기 부터 실행, 마치 Activity의 onResume()의 경우와 같다
 * 그러므로 중요한 작업은 onCreate() 보다는 onStartCommand() 메서드에 구현 한다
 */
public class AwakeService extends Service {

    private static final String TAG = "myLog";

    private static final String ACTION_START = "com.starnamu.projcet.memorize_card.awakeprocess.ACTION_START"; // Action to start
    private static final String ACTION_KEEPALIVE = "com.starnamu.projcet.memorize_card.awakeprocess.ACTION_KEEPALIVE"; // Action to keep alive used by alarm manager

    private static final int KEEP_ALIVE = 3000; // KeepAlive Interval in MS

    private AlarmManager mAlarmManager;

    public static void awaken(Context context) {
        Intent i = new Intent(context, AwakeService.class);
        i.setAction(ACTION_START);
        context.startService(i);
    }

    /**
     * 서비스를 중지 시킬수도 있다
     */
    public static void awakenStop(Context context) {
        Intent i = new Intent(context, AwakeService.class);
        i.setAction(ACTION_START);
        context.stopService(i);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        /**원하는 시간에 특정 작업을 수행하도록 해야 하는 경우*/
        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    /**
     * 서비스 종료후 재 실행시 해당 Intent 초기화 옵션설정 가능
     * START_STICKY // 초기화 시킨다
     * START_NOT_STICKY //Service를 재실행 시키지 않는다
     * START_REDELIVER_INTENT // 초기화 시키지 않는다.
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        String action = intent.getAction();

        Log.d(TAG, "action " + action);
        if (action.equals(ACTION_START)) {

            Intent intentA = new Intent();
            intentA.setClass(this, AwakeService.class);
            //인텐트의 행동을 정의한것
            intentA.setAction(ACTION_KEEPALIVE);

            /**PendingIntent에 Intent를 추가함으로 해당 Intent Action을 가진 Intent를 호출
             * 매개변수는 Ctrl+method onMouse하면 확인 가능*/
            PendingIntent pi = PendingIntent.getService(this, 0, intentA, 0);


            /**반복되는 알람을 예약하는 Method
             * setRepeating(기준시간 및 대기모드 지원여부, 알람을 실행할 시각, 실행할 작업)
             * setInexactRepeating--> 정확한 시간에 알람이 일어나지 않을수도 있다. 정확한 시간보다는 일정 주기로 실행
             * */
            mAlarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    /**실제 시간을 기준으로 합니다.
                     * 대기상태일 경우 단말기를 활성 상태로 전환한 후 작업을 수행합니다.*/
                    System.currentTimeMillis() + KEEP_ALIVE, KEEP_ALIVE, pi);
        } else if (action.equals(ACTION_KEEPALIVE)) {
            Log.d(TAG, "ALRAM");
        }
        /**아래 return(반환값)이 비정상 종료 Service 재실행시 선택옵션 Flag*/
        return START_NOT_STICKY;
    }
}
