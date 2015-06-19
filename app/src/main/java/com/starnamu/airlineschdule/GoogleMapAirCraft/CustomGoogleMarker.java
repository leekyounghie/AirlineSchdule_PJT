package com.starnamu.airlineschdule.GoogleMapAirCraft;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.starnamu.projcet.airlineschedule.R;

import java.util.ArrayList;

/**
 * Created by starnamu on 2015-06-19.
 */
public class CustomGoogleMarker {

    GoogleMap map;
    ArrayList<Marker> AirCraftMarker = null;
    Marker marker;

    public CustomGoogleMarker(GoogleMap map) {
        this.map = map;

        AirCraftMarker = new ArrayList<>();

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

        Marker m = map.addMarker(new MarkerOptions().position(latLng));
        Resources Res = Resources.getSystem();
/*
        Bitmap b = BitmapFactory.decodeResource(Res, R.drawable.airplane);
        m.setIcon(BitmapDescriptorFactory.fromBitmap(b));
*/

        Bitmap src = BitmapFactory.decodeResource(Res, R.drawable.airplane);

        Bitmap aaa = Bitmap.createBitmap(src, 1000, 1000, 10, 10);
        m.setIcon(BitmapDescriptorFactory.fromBitmap(src));

       /* marker = map.addMarker(new MarkerOptions()
                .icon(BitmapDescriptorFactory
                .fromResource(R.drawable.airplane))
                .position(latLng));
        Resources Res = Resources.getSystem();

        Bitmap bitmap = BitmapFactory.decodeResource(Res,R.drawable.ic_play_light);

        marker = map.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.
                fromBitmap(getResizedBitmap(bitmap, 10, 10))).position(latLng));*/

      /*  Resources Res = Resources.getSystem();

        Bitmap bitmap = BitmapFactory.decodeResource(Res, R.drawable.ic_play_light);

        map.addGroundOverlay(new GroundOverlayOptions().image(BitmapDescriptorFactory.fromBitmap(bitmap)));

        AirCraftMarker.add(marker);*/

//        marker.setIcon(new Icon(Resources.getSystem().getDrawable(R.drawable.airplane)));


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
