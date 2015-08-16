package com.starnamu.airlineschdule.airlinealarmitemgroup;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.starnamu.airlineschdule.comm.FlightNumChange;
import com.starnamu.projcet.airlineschedule.R;

public class AlarmReceiver extends BroadcastReceiver {

    FlightNumChange flightNumChange;

    public AlarmReceiver() {
        flightNumChange = new FlightNumChange();
    }

    private NotificationManager notificationManager = null;

    @Override
    public void onReceive(Context context, Intent intent) {

        String NotiFlightNum = (String) intent.getExtras().get("notifyId");
        int NotiId = flightNumChange.getAscIICode(NotiFlightNum);
        int EstimatedDateTime = 0;
        String Message = NotiFlightNum + "편 항공기 도착시간이" + EstimatedDateTime + "분 남았습니다.";

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Toast.makeText(context, "알람이 울림니다", Toast.LENGTH_SHORT).show();

        Notification notification = new Notification(R.drawable.alarm_icon, Message,
                System.currentTimeMillis() + 3000);

        notification.setLatestEventInfo(context, "도착 예정시간 알림", Message, null);
        notificationManager.notify(NotiId, notification);
    }
}
