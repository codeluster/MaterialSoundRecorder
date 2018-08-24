package com.example.tanmay.soundrecorder.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanmay.soundrecorder.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreviousRecordingsFragment extends Fragment {


    public PreviousRecordingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_previous_recordings, container, false);
    }

}
