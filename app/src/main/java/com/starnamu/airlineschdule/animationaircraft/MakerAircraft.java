package com.starnamu.airlineschdule.animationaircraft;

import android.os.Handler;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;


/**
 * Created by starnamu on 2015-06-15.
 * 마커를 일정시간 한방향으로 계속 이동시키는 코드
 */
public class MakerAircraft extends Thread {

    Handler handler;
    Marker marker;
    LatLng latLng;
    int i;
    double Latitude, LastLatitude;
    double Longitude, LastLongitude;
    short Azimuth;

    public MakerAircraft(Marker marker, LatLng latLng) {
        handler = new Handler();
        this.marker = marker;
        this.latLng = latLng;
        this.Latitude = latLng.latitude;
        this.Longitude = latLng.longitude;

    }

    public void run() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (i < 500) {

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            marker.setPosition(latLng);
                            marker.setRotation(Azimuth + 20);
                        }
                    });//handler

                    i++;

                    LastLatitude = Latitude;
                    LastLongitude = Longitude;
                    Latitude = Latitude + 0.005;
                    Longitude = Longitude + 0.005;
                    latLng = new LatLng(Latitude, Longitude);

                    LatLonConversion latLonConversion = new LatLonConversion();
                    //(double P1_latitude, double P1_longitude, double P2_latitude, double P2_longitude)
                    Azimuth = latLonConversion.bearingP1toP2(LastLatitude, LastLongitude, Latitude, Longitude);


                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }//run()
        });//Thread
        thread.start();
    }

    /*

    지도터치시 마커찍기

    private void mark() {
		map = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.mapview)).getMap();

		map.setOnMapClickListener(new OnMapClickListener() {
			@Override
			public void onMapClick(LatLng latLng) {
				MarkerOptions markerOptions = new MarkerOptions();
				markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.map_icon01));
				markerOptions.position(latLng);


				map.clear();
				map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
				map.addMarker(markerOptions);

			}
		});
		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	}*/
}
