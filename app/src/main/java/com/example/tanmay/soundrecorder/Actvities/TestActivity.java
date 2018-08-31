package com.example.tanmay.soundrecorder.Actvities;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.example.tanmay.soundrecorder.Data.RecordingItem;
import com.example.tanmay.soundrecorder.Data.RecordingsContract;
import com.example.tanmay.soundrecorder.R;

public class TestActivity extends AppCompatActivity {

    private ContentResolver resolver;
    private String LOG_TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        LOG_TAG = TestActivity.class.getSimpleName();

        resolver = getContentResolver();

        RecordingItem recordingItem = new RecordingItem("rec1", "path1", 18, 230, 3489);

        ContentValues values = new ContentValues();

        values.put(RecordingsContract.RecordingsEntry.COLUMN_RECORDING_NAME, recordingItem.getName());
        values.put(RecordingsContract.RecordingsEntry.COLUMN_FILE_PATH, recordingItem.getFilePath());
        values.put(RecordingsContract.RecordingsEntry.COLUMN_RECORDING_LENGTH, recordingItem.getLength());
        values.put(RecordingsContract.RecordingsEntry.COLUMN_RECORDING_TIME, recordingItem.getTime());

        resolver.insert(RecordingsContract.RecordingsEntry.CONTENT_URI, values);

        Cursor cursor = resolver.query(RecordingsContract.RecordingsEntry.CONTENT_URI, null, null, null, null, null);

        Log.v(LOG_TAG, "Received cursor. Cursor count = " + cursor.getCount());

        resolver.delete(RecordingsContract.RecordingsEntry.CONTENT_URI, null, null);

    }
}
