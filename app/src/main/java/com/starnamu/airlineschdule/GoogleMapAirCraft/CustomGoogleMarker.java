package com.starnamu.airlineschdule.GoogleMapAirCraft;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.starnamu.airlineschdule.animationaircraft.LatLonConversion;
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

        BitmapDrawable drawable = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.airplane_10);
        Bitmap bitmap = drawable.getBitmap();


        marker = map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.
                fromBitmap(bitmap)).position(latLng));
        LatLonConversion latLonConversion = new LatLonConversion();

        short a = latLonConversion.bearingP1toP2(latLng.latitude, latLng.longitude, AirPortPosition.INCHENAIRPORT.latitude, AirPortPosition.INCHENAIRPORT.longitude);
        String MarkerId = marker.getId();
        AnimateMarker airCraftMarker = new AnimateMarker(marker, latLng, a);
        airCraftMarker.start();


        AirCraftMarker.put(MarkerId, marker);
    }


}
