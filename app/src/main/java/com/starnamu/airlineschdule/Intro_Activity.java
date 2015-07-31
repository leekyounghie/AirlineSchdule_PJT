package com.starnamu.airlineschdule;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;

import com.starnamu.airlineschdule.parser.AirlineParser;
import com.starnamu.airlineschdule.slidinglayout.AirlineItem;
import com.starnamu.projcet.airlineschedule.R;

import java.util.ArrayList;

public class Intro_Activity extends ActionBarActivity {

    // intro 화면에서 사용할 핸들러를 인스턴트를 생성한다.
    Handler handler_intro;

    AirlineParser parser;
    static ArrayList<AirlineItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        items = null;

        // 핸들러를 실질적으로 정의하고, 핸들러를 시작하는 지연시간을 설정한다.
        handler_intro = new Handler();
        handler_intro.postDelayed(run_intro, 500);

       /* parser = new AirlineParser(getApplicationContext(), PDEPARTURES, PARRIVALS);
        items = parser.getArrayList();*/
    }

    /*
     *  run_intro : intro 핸들러에서 하는 일을 정의한다.
     *  현재 페이지(IntroActivity)를 종료하면서 다음 페이지(MainActivity)를 실행한다.
     *  복잡하지 않으므로, 내부 선언으로 만족시켰다.
     */
    Runnable run_intro = new Runnable() {
        public void run() {
            parser = new AirlineParser();
            items = parser.getArrayList();

            Bundle bundle = new Bundle();
            bundle.putSerializable("airlinetime", items);

            Intent intent = new Intent(Intro_Activity.this, MainActivity.class);
            startActivity(intent);
            finish();
            // 화면이 Fade in-out효과와 함께 작동하도로 등록해준다.
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };



    /*
     *  onBackPressed function override
     *   - intro 화면이 나오는 중에는 back 버튼을 사용하지 못하게 한다.
     *   - 핸들러에 의해서 잘못된 결과를 나타낼 수 있다.
     */
    @Override
    public void onBackPressed() {
    }
}


