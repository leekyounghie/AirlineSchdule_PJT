package com.starnamu.airlineschdule.slidinglayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.starnamu.projcet.airlineschedule.R;

/**
 * Created by starnamu on 2015-06-24.
 */
public class SlideLayoutFragment extends Fragment {

    View view;

    public SlideLayoutFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.slidelayoutfragment, container);

        view.findViewById(R.id.alramBtn).setOnClickListener(onClickListener);

        view.findViewById(R.id.mapViewBtn).setOnClickListener(onClickListener);

        view.findViewById(R.id.infoBtn).setOnClickListener(onClickListener);

        return view;
    }

    private CustomOnClickListener customListener;

    public interface CustomOnClickListener {
        public void onClicked(int id);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            customListener.onClicked(v.getId());
        }
    };

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
        customListener = (CustomOnClickListener) activity;
    }
}
