package com.example.tanmay.soundrecorder.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.tanmay.soundrecorder.Data.RecordingsProvider;
import com.example.tanmay.soundrecorder.R;

import java.io.File;

public class RecordingService extends Service {

    private String mFileName;
    private String mFilePath;

    private static final String LOG_TAG = RecordingService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startRecording();
        // Service is not sticky as no point recording audio once app is killed
        return START_NOT_STICKY;
    }

    private void startRecording() {

        // Creates a new filename and path and updates global variables
        setFileNameAndPath();
    }

    /* Each new file is named as for eg. /SoundRecorder/My Recording_4.mp4
     * By checking if My_Recording_3.mp4 exists that filename is skipped*/
    private void setFileNameAndPath() {

        int counter = 0;

        File file;

        do {
            StringBuilder filename = new StringBuilder(getString(R.string.default_file_name));
            filename.append("_");
            // Gets the number of items in the database as the least number in  preexisting filename
            // but still need to add a counter and check because if say My Recording_3 is deleted
            // number of items in databse is reduced but there still exists recording such as #4, #5 etc...
            filename.append(new RecordingsProvider().getCount() + counter);
            filename.append(getString(R.string.default_file_extension));
            mFileName = filename.toString();

            StringBuilder filepath = new StringBuilder(Environment.getExternalStorageDirectory().getAbsolutePath());
            filepath.append("/");
            filepath.append(getString(R.string.default_file_directory));
            filepath.append("/");
            filepath.append(mFileName);
            mFilePath = filepath.toString();


            file = new File(mFilePath);

        }
        // This is true while there already exists a file at the location
        while (file.exists() && !file.isDirectory());

    }
}
