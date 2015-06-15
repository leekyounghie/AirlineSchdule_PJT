package com.starnamu.airlineschdule.awakeprocess;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Service �ȵ���̵� Application ������Ʈ �߿� �ϳ��̸� ����ڿ� ��ȣ�ۿ� ���� �ʰ�,
 * Background(ȭ��޴�)���� �����ϴ� ������Ʈ
 * Activity �����Ŀ��� ����
 * <p/>
 * Service �ȵ���̵� Application ������Ʈ �߿� �ϳ��̸� ����ڿ� ��ȣ�ۿ� ���� �ʰ�,
 * Background(ȭ��޴�)���� �����ϴ� ������Ʈ
 * Activity �����Ŀ��� ����
 * <p/>
 * <p/>
 * Service �ȵ���̵� Application ������Ʈ �߿� �ϳ��̸� ����ڿ� ��ȣ�ۿ� ���� �ʰ�,
 * Background(ȭ��޴�)���� �����ϴ� ������Ʈ
 * Activity �����Ŀ��� �����ϱ� ���ؼ� ������� ������Ʈ(MP3�÷��̾� ���)
 * Manifests�� <service></service>�� ����Ѵ�.
 * Service���ο����� Thread�۾��� �ؼ��� �ȵȴ�.
 * Service �ֱ�� onCreate() -> onStartCommand() ������ �̾�������
 * Service�� ����ǰ� �ִ� ���߿� �ٽ��ѹ�  startService()  �޼��尡 ȣ��Ǹ�
 * onStartCommand() �ֱ� ���� ����, ��ġ Activity�� onResume()�� ���� ����
 * �׷��Ƿ� �߿��� �۾��� onCreate() ���ٴ� onStartCommand() �޼��忡 ���� �Ѵ�
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
     * ���񽺸� ���� ��ų���� �ִ�
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
        /**���ϴ� �ð��� Ư�� �۾��� �����ϵ��� �ؾ� �ϴ� ���*/
        mAlarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    /**
     * ���� ������ �� ����� �ش� Intent �ʱ�ȭ �ɼǼ��� ����
     * START_STICKY // �ʱ�ȭ ��Ų��
     * START_NOT_STICKY //Service�� ����� ��Ű�� �ʴ´�
     * START_REDELIVER_INTENT // �ʱ�ȭ ��Ű�� �ʴ´�.
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        String action = intent.getAction();

        Log.d(TAG, "action " + action);
        if (action.equals(ACTION_START)) {

            Intent intentA = new Intent();
            intentA.setClass(this, AwakeService.class);
            //����Ʈ�� �ൿ�� �����Ѱ�
            intentA.setAction(ACTION_KEEPALIVE);

            /**PendingIntent�� Intent�� �߰������� �ش� Intent Action�� ���� Intent�� ȣ��
             * �Ű������� Ctrl+method onMouse�ϸ� Ȯ�� ����*/
            PendingIntent pi = PendingIntent.getService(this, 0, intentA, 0);

            /**�ݺ��Ǵ� �˶��� �����ϴ� Method
             * setRepeating(���ؽð� �� ����� �����, �˶��� ������ �ð�, ������ �۾�)
             * setInexactRepeating--> ��Ȯ�� �ð��� �˶��� �Ͼ�� �������� �ִ�. ��Ȯ�� �ð����ٴ� ���� �ֱ�� ����
             * */
            mAlarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,
                    /**���� �ð��� �������� �մϴ�.
                     * �������� ��� �ܸ��⸦ Ȱ�� ���·� ��ȯ�� �� �۾��� �����մϴ�.*/
                    System.currentTimeMillis() + KEEP_ALIVE, KEEP_ALIVE, pi);
        } else if (action.equals(ACTION_KEEPALIVE)) {
            Log.d(TAG, "ALRAM");
        }
        /**�Ʒ� return(��ȯ��)�� ������ ���� Service ������ ���ÿɼ� Flag*/
        return START_NOT_STICKY;
    }
}
