package com.example.tanmay.soundrecorder.Services;

import android.app.Service;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.tanmay.soundrecorder.Data.RecordingsContract;
import com.example.tanmay.soundrecorder.Data.RecordingsProvider;
import com.example.tanmay.soundrecorder.Data.UserPreferences;
import com.example.tanmay.soundrecorder.R;

import java.io.File;
import java.io.IOException;

public class RecordingService extends Service {

    // Name of recording
    private String mFileName;
    // Path of recording
    private String mFilePath;

    private MediaRecorder mRecorder;

    private long mStartingTimeMillis;
    private long mElapsedTimeMillis;

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

        // A MediaRecorder is used to record audio and video
        mRecorder = new MediaRecorder();
        // Set input from the device microphone
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        // Set the output format to mp4
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        // Set the output file to store recording
        mRecorder.setOutputFile(mFilePath);
        // Set the audio encoder
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        // Set the audio channels
        mRecorder.setAudioChannels(2);

        // Set the bitrate based on user preferences
        if (UserPreferences.Quality.getQualityPref(this)) {
            mRecorder.setAudioSamplingRate(getResources().getInteger(R.integer.high_quality_sampling_rate));
            mRecorder.setAudioEncodingBitRate(getResources().getInteger(R.integer.high_quality_encoding_bitrate));
        }

        try {
            mRecorder.prepare();
            mRecorder.start();

            mStartingTimeMillis = System.currentTimeMillis();

        } catch (IOException e) {
            Log.e(LOG_TAG, "Failed to prepare MediaRecorder : " + e.getMessage());
        }

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

    private void stopRecording() {


        // Store the length of the recording
        mElapsedTimeMillis = System.currentTimeMillis() - mStartingTimeMillis;
        // Stop the recording
        mRecorder.stop();
        // Release the MediaRecorder
        mRecorder.release();

        // Insert the recording to the database
        insertRecording();

        Toast.makeText(this, "Recording saved to " + mFilePath, Toast.LENGTH_SHORT).show();


    }

    private void insertRecording() {

        ContentValues values = new ContentValues();
        values.put(RecordingsContract.RecordingsEntry.COLUMN_RECORDING_NAME, mFileName);
        values.put(RecordingsContract.RecordingsEntry.COLUMN_FILE_PATH, mFilePath);
        values.put(RecordingsContract.RecordingsEntry.COLUMN_RECORDING_LENGTH, mElapsedTimeMillis);
        values.put(RecordingsContract.RecordingsEntry.COLUMN_RECORDING_TIME, mStartingTimeMillis);

        getContentResolver().insert(RecordingsContract.RecordingsEntry.CONTENT_URI, values);

    }
}
