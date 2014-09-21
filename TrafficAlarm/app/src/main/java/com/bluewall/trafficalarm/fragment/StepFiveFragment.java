package com.bluewall.trafficalarm.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluewall.trafficalarm.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

/**
 * A placeholder fragment containing a simple view.
 */
public class StepFiveFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    private GoogleMap map;
    private MapFragment mapFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step_five, container, false);

        mapFragment = MapFragment.newInstance();

        getChildFragmentManager().beginTransaction().add(R.id.map_layout, mapFragment).commit();

        return rootView;
    }

    @Override
    public void onStart() {


        if(map == null) {
            map = mapFragment.getMap();
            map.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                @Override
                public void onMapLoaded() {
                    map.getUiSettings().setAllGesturesEnabled(false);
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-151,39), 10));
                }
            });
        }


        super.onStart();
    }
}