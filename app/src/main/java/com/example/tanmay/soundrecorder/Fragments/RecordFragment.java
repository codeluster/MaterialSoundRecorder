package com.example.tanmay.soundrecorder.Fragments;


import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Chronometer;
import android.widget.Toast;

import com.example.tanmay.soundrecorder.R;
import com.example.tanmay.soundrecorder.Services.RecordingService;

import java.io.File;

public class RecordFragment extends Fragment {

    private String LOG_TAG;

    private final int REQUEST_PERM_AUDIO_CODE = 2803;
    private final int REQUEST_PERM_WRITE_EXT_CODE = 6233;
    private Intent service;
    private Activity activity;
    // Track if currently recording (true) or not (false)
    private boolean mRecording = false;

    private Chronometer mChronometer;
    private FloatingActionButton fab;

    public RecordFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LOG_TAG = RecordFragment.class.getSimpleName();

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_record, container, false);

        mChronometer = v.findViewById(R.id.recording_chronometer);
        fab = v.findViewById(R.id.toogle_recording_fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toggleFab();

                if (!mRecording) {
                    // Check for permission before starting recording
                    if (getPermissions()) {
                        onStartRecording();
                        Log.d(LOG_TAG, "Beginning recording...");
                    } else {
                        Log.d(LOG_TAG, "Recording cancelled. Lacking necessary permissions.");
                    }
                } else onPauseRecording();

            }
        });

        return v;
    }

    private void onStartRecording() {

        // Intent to launch service
        service = new Intent(getActivity(), RecordingService.class);

        String pathname = Environment.getExternalStorageDirectory() + "/" + getString(R.string.default_file_directory);

        File folder = new File(pathname);
        // Create folder if it doesn't exist
        if (!folder.exists()) {
            folder.mkdir();
            Log.d(LOG_TAG, "Folder didn't exist. Created : " + pathname);
        }

        // Start recording
        activity.startService(service);

        // Reset the chronometer
        mChronometer.setBase(SystemClock.elapsedRealtime());
        // Start the chronometer
        mChronometer.start();

        // Inform the user
        Toast.makeText(getActivity(), getString(R.string.prompt_recording_started), Toast.LENGTH_SHORT).show();

        mRecording = true;

        // Prevent the screen from timing out and turning off
        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }

    private void onPauseRecording() {

        getActivity().stopService(service);

        // Stop the chronometer
        mChronometer.stop();

        mRecording = false;

        // Allow the screen to timeout and turn off
        activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }

    private void toggleFab() {

        if (!mRecording) {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_stop_white_24dp));
        } else {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_white_24dp));
        }
    }

    private boolean getPermissions() {


        Activity activity = getActivity();

        boolean record_audio = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
        boolean write_ext_storage = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        if (!record_audio)
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_PERM_AUDIO_CODE);

        if (!write_ext_storage)
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERM_WRITE_EXT_CODE);

        return record_audio && write_ext_storage;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERM_AUDIO_CODE:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //Check if all other permissions also granted
                    getPermissions();
                    // Start recording
                    onStartRecording();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setMessage(R.string.runtime_permissions_denied_message)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (dialog != null) dialog.dismiss();
                                }
                            }).show();

                    return;

                }

                break;


            case REQUEST_PERM_WRITE_EXT_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    //Check if all other permissions also granted
                    getPermissions();
                    // Start recording
                    onStartRecording();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                    builder.setMessage(R.string.runtime_permissions_denied_message)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (dialog != null) dialog.dismiss();
                                }
                            }).show();

                }

                break;


        }
    }
}
