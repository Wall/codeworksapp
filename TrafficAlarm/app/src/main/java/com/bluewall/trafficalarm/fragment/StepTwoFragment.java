package com.bluewall.trafficalarm.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import com.bluewall.trafficalarm.R;
import com.bluewall.trafficalarm.adapter.PlacesAutoCompleteAdapter;

/**
 * A placeholder fragment containing a simple view.
 */
public class StepTwoFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static StepTwoFragment newInstance(int sectionNumber) {
        StepTwoFragment fragment = new StepTwoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public StepTwoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_step_two, container, false);
        AutoCompleteTextView autoCompView = (AutoCompleteTextView) rootView.findViewById(R.id.auto_complete_location);
        autoCompView.setAdapter(new PlacesAutoCompleteAdapter(getActivity()
                , android.R.layout.simple_list_item_1));
        autoCompView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });


        return rootView;
    }
}
