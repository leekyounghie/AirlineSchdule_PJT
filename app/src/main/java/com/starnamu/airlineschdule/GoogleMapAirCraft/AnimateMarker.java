package com.starnamu.airlineschdule.GoogleMapAirCraft;

import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.BounceInterpolator;
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
                LatLng airPlanPosition = new LatLngInterpolator.Linear().interpolate(animValue,
                        latLng);

                marker.setPosition(airPlanPosition);
                if (time < 1) {
                    handler.postDelayed(this, 10);
                }
                if (airPlanPosition == AirPortPosition.airportPosition) {
                    marker.remove();
                }


            }
        });
    }

}