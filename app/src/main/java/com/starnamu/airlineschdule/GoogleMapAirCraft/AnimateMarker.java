package com.starnamu.airlineschdule.GoogleMapAirCraft;

import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by youmyeongsic on 15. 3. 1..
 */
public class AnimateMarker extends Thread {
    int sleep;

    Handler handler = new Handler();
    Interpolator interpolator = new BounceInterpolator();
    Marker marker;
    LatLng latLng;
    final float durationInMs = 10000;
    int waitTime;

    public AnimateMarker(final Marker marker, LatLng latLng, int waitTime) {

        this.marker = marker;
        this.latLng = latLng;
        this.waitTime = waitTime;
    }

    @Override
    public void run() {
        super.run();
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        final long start = SystemClock.uptimeMillis();
        handler.post(new Runnable() {


            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float time = elapsed / durationInMs;
                float animValue = interpolator.getInterpolation(time);


                marker.setPosition(new LatLngInterpolator.Linear().interpolate(animValue,
                        latLng));
                if (time < 1) {
                    handler.postDelayed(this, 10);
                }
                /*if (time > 1) {
                    marker.setPosition(latLng);
                } */
                //위치고정

            }
        });
    }

}