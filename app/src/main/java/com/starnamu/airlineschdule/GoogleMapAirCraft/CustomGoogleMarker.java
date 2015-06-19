package com.starnamu.airlineschdule.GoogleMapAirCraft;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.starnamu.projcet.airlineschedule.R;

import java.util.HashMap;

/**
 * Created by starnamu on 2015-06-19.
 */
public class CustomGoogleMarker {

    GoogleMap map;
    Marker marker;
    Context mContext;
    HashMap<String, Marker> AirCraftMarker;
    short Azimuth;
    Handler handler;
    LatLng ICNSTATE = new LatLng(37.4692, 126.4406);

    public CustomGoogleMarker(GoogleMap map, Context context) {
        this.map = map;
        this.mContext = context;
        handler = new Handler();

        AirCraftMarker = new HashMap<>();

        CustomMapTouchEvent();
    }

    private void CustomMapTouchEvent() {
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                CustomAddMarker(latLng);
            }
        });
    }

    private void CustomAddMarker(LatLng latLng) {

        BitmapDrawable drawable = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.airplane);
        Bitmap bitmap = drawable.getBitmap();
        final LatLng nowLanLon = latLng;

        marker = map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.
                fromBitmap(getResizedBitmap(bitmap, 40, 40))).position(latLng));

        String MarkerId = marker.getId();

       /* Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean isNow = true;


                while (isNow) {

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

        thread.start();*/

        AirCraftMarker.put(MarkerId, marker);
    }

    public interface LatLngInterpolator {
        public LatLng interpolate(float fraction, LatLng finishPosition);

        public class Linear implements LatLngInterpolator {
            @Override
            public LatLng interpolate(float fraction, LatLng finishPosition) {
                double lat = (finishPosition.latitude + 0.015 + (-0.015 * fraction));
                //Log.d("MapActivity", "lat : "+lat+", fraction :"+fraction+", Finish Position :"+finishPosition.latitude);
                return new LatLng(lat, finishPosition.longitude);
            }
        }
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }
}
