package com.starnamu.airlineschdule.fragment;

import android.content.Context;
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
import com.google.android.gms.maps.model.LatLng;
import com.starnamu.airlineschdule.GoogleMapAirCraft.CustomGoogleMarker;
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

        mapView = (MapView) v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);


        map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);


//        MapsInitializer.initialize(this.getActivity());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(37.4692, 126.4406957), 7);
        map.animateCamera(cameraUpdate);

        return v;
    }

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

        CustomGoogleMarker cgm = new CustomGoogleMarker(map);

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