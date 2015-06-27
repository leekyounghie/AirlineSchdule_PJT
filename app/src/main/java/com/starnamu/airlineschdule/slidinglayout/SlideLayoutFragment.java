package com.starnamu.airlineschdule.slidinglayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.starnamu.projcet.airlineschedule.R;

/**
 * Created by starnamu on 2015-06-24.
 */
public class SlideLayoutFragment extends Fragment {

    Button alramBtn, mapView, infoBtn;

    public SlideLayoutFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.slidelayoutfragment, container);
        alramBtn = (Button) view.findViewById(R.id.alramBtn);
        mapView = (Button) view.findViewById(R.id.mapViewBtn);
        infoBtn = (Button) view.findViewById(R.id.infoBtn);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }
}
