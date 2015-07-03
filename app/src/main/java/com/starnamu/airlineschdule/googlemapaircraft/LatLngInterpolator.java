/* Copyright 2013 Google Inc.
   Licensed under Apache 2.0: http://www.apache.org/licenses/LICENSE-2.0.html */

package com.starnamu.airlineschdule.googlemapaircraft;

import com.google.android.gms.maps.model.LatLng;

public interface LatLngInterpolator {

    LatLng airportPosition = AirPortPosition.INCHENAIRPORT;

    public LatLng interpolate(float fraction, LatLng finishPosition);


    public class Linear implements LatLngInterpolator {
        @Override
        public LatLng interpolate(float fraction, LatLng startPosition) {

            double latdistance = startPosition.latitude - airportPosition.latitude;
            double lotdistance = startPosition.longitude - airportPosition.longitude; //시작점과 끝점의 거리

            double lat = (startPosition.latitude - latdistance * fraction);
            double lot = (startPosition.longitude - lotdistance * fraction); //시작점에서 끝의 사이값을 먼저 더해준다음(사이거리 값이 음수) 그걸 천천히 늘려줌
            return new LatLng(lat, lot);
        }


    }
}
