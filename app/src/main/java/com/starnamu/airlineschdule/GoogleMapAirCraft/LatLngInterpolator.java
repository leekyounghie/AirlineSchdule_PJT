/* Copyright 2013 Google Inc.
   Licensed under Apache 2.0: http://www.apache.org/licenses/LICENSE-2.0.html */

package com.starnamu.airlineschdule.GoogleMapAirCraft;

import com.google.android.gms.maps.model.LatLng;

public interface LatLngInterpolator {

    LatLng airportPosition = new LatLng(37.3713, 126.654);

    public LatLng interpolate(float fraction, LatLng finishPosition);

    public LatLng Bounce(float fraction, LatLng finishPosition);

    public class Linear implements LatLngInterpolator {
        @Override
        public LatLng interpolate(float fraction, LatLng startPosition) {

            double latdistance = startPosition.latitude - airportPosition.latitude;
            double lotdistance = startPosition.longitude - airportPosition.longitude;

            double lat = (airportPosition.latitude + latdistance + (-latdistance * fraction));
            double lot = (airportPosition.longitude + lotdistance + (-lotdistance * fraction));
            return new LatLng(lat, lot);
        }

        @Override
        public LatLng Bounce(float fraction, LatLng finishPosition) {
            double lat = (finishPosition.latitude + 0.015 + (-0.015 * fraction));
            //Log.d("MapActivity", "lat : "+lat+", fraction :"+fraction+", Finish Position :"+finishPosition.latitude);
            return new LatLng(lat, finishPosition.longitude);
        }
    }
}
