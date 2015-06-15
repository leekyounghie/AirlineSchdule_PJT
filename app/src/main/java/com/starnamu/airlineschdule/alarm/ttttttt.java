package com.starnamu.airlineschdule.alarm;

/**
 * Created by starnamu on 2015-06-06.
 */
public class ttttttt {
    /*package com.starnamu.projcet.mylocation;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    TextView LoactionTextveiw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        LoactionTextveiw = (TextView) findViewById(R.id.myLocation);

//        LocationFragment fragment = new LocationFragment(this);
    }

    public void onButton1Clicked(View v) {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        long minTime = 10000;//Update 시간(간격)
        float minDistance = 0;//0은 항상 Update 클수록 해당 숫자의 거리만큼마다 Update

        MyLocationListener listener = new MyLocationListener();

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                minTime, minDistance, listener);

        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                minTime, minDistance, listener);

        Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (lastLocation != null) {
            Double latitude = lastLocation.getLatitude();//위도 y값
            Double longitude = lastLocation.getLongitude();// 경도 값 x값

            LoactionTextveiw.setText("이전 위치 : " + latitude + " , " + longitude);
            LoactionTextveiw.invalidate();
        }
    }

    class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            Double latitude = location.getLatitude();//위도 y값
            Double longitude = location.getLongitude();// 경도 값 x값

            LoactionTextveiw.setText("내위치 : " + latitude + " , " + longitude);
            LoactionTextveiw.invalidate();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
*/
}
