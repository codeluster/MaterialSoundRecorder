package com.example.tanmay.soundrecorder.Fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;

import com.example.tanmay.soundrecorder.R;

public class RecordFragment extends Fragment {

    Chronometer chronometer;
    FloatingActionButton fab;

    public RecordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_record, container, false);

        chronometer = v.findViewById(R.id.recording_chronometer);
        fab = v.findViewById(R.id.toogle_recording_fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStartRecording();
            }
        });

        return v;
    }


    private void onStartRecording() {



    }

}
