package com.starnamu.airlineschdule.fragment;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.LatLng;
import com.starnamu.projcet.airlineschedule.R;

/**
 * Created by starnamu on 2015-06-08.
 */
public class GoogleMapFragment extends Fragment {

    MapView mapView;
    GoogleMap map;
    LocationManager manager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        manager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.some_layout, container, false);

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(this.getActivity());
        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(37.4692, 126.4406957), 12);
        map.animateCamera(cameraUpdate);

        LatLng NEWARk = new LatLng(37.4692, 126.4406957);
        GroundOverlayOptions newarMap = new GroundOverlayOptions()
                .image(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher))
                .position(NEWARk, 8600f, 6500f);

        map.addGroundOverlay(newarMap);
map.setOnIndoorStateChangeListener(new GoogleMap.OnIndoorStateChangeListener() {
    @Override
    public void onIndoorBuildingFocused() {

    }

    @Override
    public void onIndoorLevelActivated(IndoorBuilding indoorBuilding) {

    }
});
        return v;
    }



    /*LatLng NEWARK = new LatLng(40.714086, -74.228697);

    GroundOverlayOptions newarkMap = new GroundOverlayOptions()
            .image(BitmapDescriptorFactory.fromResource(R.drawable.newark_nj_1922))
            .position(NEWARK, 8600f, 6500f);
    map.addGroundOverlay(newarkMap);*/

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
        //내 위치 좌표 깜빡임 멈춤
        map.setMyLocationEnabled(false);
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();

        //내 위치 좌표 깜빡임 시작
        map.setMyLocationEnabled(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}