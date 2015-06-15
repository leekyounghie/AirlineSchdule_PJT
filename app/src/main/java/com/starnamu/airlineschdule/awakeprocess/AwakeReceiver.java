package com.starnamu.airlineschdule.awakeprocess;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AwakeReceiver extends BroadcastReceiver {
    /**
     * ��⿡ ��� �̺�Ʈ(�۾�) �߻��ϸ� �̸� �ް��� �ϴ°��� �˷��ִ� ���
     */

    public static final String ACTION_START = "com.starnamu.projcet.memorize_card.awakeprocess.ACTION_START";

    /**
     * ���ϴ� ����Ʈ�� ��ε�ĳ��Ʈ �Ǹ� onReceive() �޼ҵ尡 �ڵ� ȣ��ȴ�.
     * AndroidManifest.xml �� <receiver></receiver>�� ����(�ڵ忡���� ����)
     * ���� ������ ������ �ʿ䰡 �ִ� Ư�� �̺�Ʈ ��ε�ĳ��Ʈ�� ���� �ϱ�
     * ������ manifest ���Ͽ� RECEIVE_BOOT_COMPLETED �۹̼��� �־�� �Ѵ�.
     */
    @Override
    public void onReceive(Context context, Intent intent) {

        /*�ڵ����� �Ѱܹ��� �̺�Ʈ�� �Ǵ��Ͽ� ���ǿ� �´� method(���๮)�� ����
        ���⼭�� Service�� ��ӹ��� AwakeService Service�� �����Ŵ*/
        String action = intent.getAction();
        if (action.equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
            AwakeService.awaken(context);
        } else if (action.equalsIgnoreCase(ACTION_START)) {
            AwakeService.awaken(context);
        }
    }
}
