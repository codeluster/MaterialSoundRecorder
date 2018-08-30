package com.example.tanmay.soundrecorder.Actvities;

import android.content.ContentUris;
import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.tanmay.soundrecorder.Data.RecordingItem;
import com.example.tanmay.soundrecorder.Data.RecordingsContract;
import com.example.tanmay.soundrecorder.R;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        RecordingItem recordingItem = new RecordingItem("rec1", "path1", 18, 230, 3489);

        ContentValues values = new ContentValues();

        values.put(RecordingsContract.RecordingsEntry.COLUMN_RECORDING_NAME, recordingItem.getName());
        values.put(RecordingsContract.RecordingsEntry.COLUMN_FILE_PATH, recordingItem.getFilePath());
        values.put(RecordingsContract.RecordingsEntry.COLUMN_RECORDING_LENGTH, recordingItem.getLength());
        values.put(RecordingsContract.RecordingsEntry.COLUMN_RECORDING_TIME, recordingItem.getTime());

        getContentResolver().insert(RecordingsContract.RecordingsEntry.CONTENT_URI, values);

    }
}
