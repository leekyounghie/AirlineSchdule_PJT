package com.starnamu.airlineschdule.GoogleMapAirCraft;

import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by youmyeongsic on 15. 3. 1..
 */
public class AnimateMarker extends Thread {
    int sleep;

    Handler handler = new Handler();
    Interpolator interpolator = new LinearInterpolator();
    Marker marker;
    LatLng latLng;
    final float durationInMs = 1000;
    int rotation;

    public AnimateMarker(final Marker marker, LatLng latLng, int rotation) {

        this.marker = marker;
        this.latLng = latLng;
        this.rotation = rotation;
    }

    @Override
    public void run() {
        super.run();

        final long start = SystemClock.uptimeMillis();




        handler.post(new Runnable() {


            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float time = elapsed / durationInMs;
                float animValue = interpolator.getInterpolation(time);


                marker.setPosition(new LatLngInterpolator.Linear().interpolate(animValue,
                        latLng));

                marker.setRotation(rotation);
                if (time < 1) { //전체시간을 1로 봤을때 1 전까지 핸들러를 계속 작동시키라는 메소드
                    handler.postDelayed(this, 10);

                }

                if (time >= 0.9) { // 전체 이동시간의 90%가 됬을때 비행기를 지움 추후 API 받으면 다른 이동 위치를 물어봐서 지울 예정


                    marker.remove();

                }


            }
        });
    }


}