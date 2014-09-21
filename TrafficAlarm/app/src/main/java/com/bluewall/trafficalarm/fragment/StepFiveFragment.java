package com.bluewall.trafficalarm.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.bluewall.trafficalarm.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.List;
import java.util.ArrayList;
import android.graphics.Color;
import java.util.Arrays;

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

    private static final LatLng[] PATH = {
            new LatLng(-33.863707, 151.208719),
            new LatLng(-33.863618, 151.208955),
            new LatLng(-33.861427, 151.209245),
            new LatLng(-33.861320, 151.208290),
            new LatLng(-33.860723, 151.208526),
            new LatLng( -33.859235, 151.208805),
            new LatLng(-33.858415, 151.209116),
            new LatLng(-33.856794, 151.208311),
            new LatLng(-33.856580, 151.208086),
            new LatLng(-33.849889, 151.212302),
            new LatLng(-33.848133, 151.212753),
            new LatLng(-33.842992, 151.210929)
    };


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
            mapFragment.getView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    map.getUiSettings().setAllGesturesEnabled(false);
                    //map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-33.86785, 151.20732), 10));

                    //ArrayList<LatLng> points = new ArrayList<LatLng>();
                    //points.add(new LatLng(-33.8481, 150.2106));
                    //points.add(new LatLng(-33.8600, 151.2094));
                    drawLine(Arrays.asList(PATH));
                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    for (LatLng latlng : PATH) {
                        builder.include(latlng);
                    }

                    map.moveCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 250));

                    mapFragment.getView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
        }

        super.onStart();
    }

    public void drawLine(List<LatLng> points) {
        PolylineOptions options = new PolylineOptions();
        options.color(Color.RED);
        options.width(10);
        options.visible(true);
        options.addAll(points);
        if (map != null)
            map.addPolyline(options);
        MarkerOptions markerOptions = new MarkerOptions();
        MarkerOptions markerOptions2 = new MarkerOptions();
        markerOptions.position(points.get(points.size() - 1));
        markerOptions2.position(points.get(0));
        map.addMarker(markerOptions);
        map.addMarker(markerOptions2);
    }
}