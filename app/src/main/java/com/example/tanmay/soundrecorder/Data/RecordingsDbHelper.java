package com.example.tanmay.soundrecorder.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.tanmay.soundrecorder.Data.RecordingsContract.RecordingsEntry.COLUMN_FILE_PATH;
import static com.example.tanmay.soundrecorder.Data.RecordingsContract.RecordingsEntry.COLUMN_RECORDING_LENGTH;
import static com.example.tanmay.soundrecorder.Data.RecordingsContract.RecordingsEntry.COLUMN_RECORDING_NAME;
import static com.example.tanmay.soundrecorder.Data.RecordingsContract.RecordingsEntry.COLUMN_RECORDING_TIME;
import static com.example.tanmay.soundrecorder.Data.RecordingsContract.RecordingsEntry.TABLE_NAME;
import static com.example.tanmay.soundrecorder.Data.RecordingsContract.RecordingsEntry._ID;


public class RecordingsDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "metadata.db";
    private static final int DATABASE_VERSION = 1;

    RecordingsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + " ("
                + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_RECORDING_NAME + " TEXT NOT NULL, "
                + COLUMN_FILE_PATH + "TEXT NOT NULL, "
                + COLUMN_RECORDING_LENGTH + " INTEGER DEFAULT 0,"
                + COLUMN_RECORDING_TIME + " INTEGER DEFAULT 0" + ")";

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
