package com.example.tanmay.soundrecorder.Data;

import android.content.ContentProvider;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.CancellationSignal;

public class RecordingsProvider extends ContentProvider {

    public static final String LOG_TAG = RecordingsProvider.class.getSimpleName();

    private static final int RECORDINGS = 1029;
    private static final int RECORDINGS_ID = 3048;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(RecordingsContract.CONTENT_AUTHORITY, RecordingsContract.PATH_RECORDINGS, RECORDINGS);
        sUriMatcher.addURI(RecordingsContract.CONTENT_AUTHORITY, RecordingsContract.PATH_RECORDINGS + "/#", RECORDINGS_ID);
    }

    private RecordingsDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new RecordingsDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, CancellationSignal cancellationSignal) {

        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        Cursor cursor;

        switch (sUriMatcher.match(uri)) {
            case RECORDINGS:
                cursor = database.query(RecordingsContract.RecordingsEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case RECORDINGS_ID:

        }

    }
}
