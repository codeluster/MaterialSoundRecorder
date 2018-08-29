package com.example.tanmay.soundrecorder.Fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.Toast;

import com.example.tanmay.soundrecorder.R;
import com.example.tanmay.soundrecorder.Services.RecordingService;

import java.io.File;

public class RecordFragment extends Fragment {

    Chronometer chronometer;
    FloatingActionButton fab;
    private final int AUDIO_PERM_REQUEST_CODE = 2803;

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

                // Check for permission before starting recording
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, AUDIO_PERM_REQUEST_CODE);
                } else onStartRecording();
            }
        });

        return v;
    }


    private void onStartRecording() {

        // Intent to launch service
        Intent service = new Intent(getActivity(), RecordingService.class);

        File folder = new File(Environment.getExternalStorageDirectory() + "/" + getString(R.string.default_file_directory));
        // Create folder if it doesn't exist
        if (!folder.exists()) {
            folder.mkdir();
        }

        // Start recording
        getActivity().startService(service);
        // Inform the user
        Toast.makeText(getActivity(), getString(R.string.prompt_recording_started), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case AUDIO_PERM_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted
                    // Start recording
                    onStartRecording();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setMessage(R.string.audio_perm_denied_message)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (dialog != null) dialog.dismiss();
                                }
                            }).show();

                }
                return;
        }
    }
}
