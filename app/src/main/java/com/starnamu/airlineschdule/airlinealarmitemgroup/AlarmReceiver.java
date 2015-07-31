package com.starnamu.airlineschdule.airlinealarmitemgroup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    public AlarmReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        /*Toast.makeText(context, "", Toast.LENGTH_LONG).show();*/
        Log.i("알람이 실행됨니다", "알람이 울림니다.");
    }
}
