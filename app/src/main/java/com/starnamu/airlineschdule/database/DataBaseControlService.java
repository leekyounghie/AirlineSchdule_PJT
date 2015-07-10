package com.starnamu.airlineschdule.database;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DataBaseControlService extends Service {

    public DataBaseControlService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        onCreateDatabase();
    }

    private void onCreateDatabase() {

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
