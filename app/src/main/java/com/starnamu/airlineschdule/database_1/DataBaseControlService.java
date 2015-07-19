package com.starnamu.airlineschdule.database_1;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DataBaseControlService extends Service {

    public DataBaseControlService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //서비스를 설정하다 끝났음
        //##################################################
        //##################################################
        //##################################################
        //##################################################
        //##################################################
        //##################################################
        AlarmDBControll alarmDBControll = new AlarmDBControll(getApplicationContext(), null);
        SchduldDBControll schduldDBControll = new SchduldDBControll(getApplicationContext(), null);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

}
