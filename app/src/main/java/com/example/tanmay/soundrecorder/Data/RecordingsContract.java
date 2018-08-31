package com.example.tanmay.soundrecorder.Data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class RecordingsContract {

    private static final String LOG_TAG = RecordingsContract.class.getSimpleName();

    private RecordingsContract() {
    }

    public static final String CONTENT_AUTHORITY = "com.example.tanmay.soundrecorder";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_RECORDINGS = RecordingsEntry.TABLE_NAME;

    public static class RecordingsEntry implements BaseColumns {

        // Refers to this particualar database
        public static final String RECORDINGS_CONTENT_AUTHORITY = RecordingsContract.CONTENT_AUTHORITY + ".Data.RecordingsProvider";
        public static final Uri RECORDINGS_CONTENT_URI = Uri.parse("content://" + RECORDINGS_CONTENT_AUTHORITY);

        // Uri pointing to this particular table
        public static final Uri CONTENT_URI = Uri.withAppendedPath(RECORDINGS_CONTENT_URI, PATH_RECORDINGS);

        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RECORDINGS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RECORDINGS;

        public static final String TABLE_NAME = "metadata";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_RECORDING_NAME = "name";
        public static final String COLUMN_FILE_PATH = "path";
        public static final String COLUMN_RECORDING_LENGTH = "length";
        public static final String COLUMN_RECORDING_TIME = "time";


    }

}
