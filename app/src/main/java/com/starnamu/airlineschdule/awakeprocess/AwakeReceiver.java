package com.starnamu.airlineschdule.awakeprocess;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AwakeReceiver extends BroadcastReceiver {
    /**
     * 기기에 어떠한 이벤트(작업)가 발생하면 이를 받고자 하는곳을 알려주는 기능
     */

    public static final String ACTION_START = "com.starnamu.projcet.memorize_card.awakeprocess.ACTION_START";

    /**
     * 원하는 인텐트가 브로드캐스트 되면 onReceive() 메소드가 자동 호출된다.
     * AndroidManifest.xml 에 <receiver></receiver>를 설정(코드에서도 가능)
     * 보안 설정에 선언할 필요가 있는 특정 이벤트 브로드캐스트를 들어야 하기
     * 때문에 manifest 파일에 RECEIVE_BOOT_COMPLETED 퍼미션이 있어야 한다.
     */

    @Override
    public void onReceive(Context context, Intent intent) {

        /**
         * 자동으로 넘겨받은 이벤트를 판단하여 조건에 맞는 method(실행문)를 실행
         * 여기서는 Service를 상속받은 AwakeService Service를 실행시킴
         */
        String action = intent.getAction();
        if (action.equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
            AwakeService.awaken(context);
        } else if (action.equalsIgnoreCase(ACTION_START)) {
            AwakeService.awaken(context);
        }
    }
}
