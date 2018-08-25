package com.example.tanmay.soundrecorder.Data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class RecordingsContract {

    private static final String LOG_TAG = RecordingsContract.class.getSimpleName();

    private RecordingsContract() {
    }

    public static final String CONTENT_AUTHORITY = "com.example.tanmay.recorder";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_RECORDINGS = RecordingsEntry.TABLE_NAME;

    public static class RecordingsEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_RECORDINGS);
        public static final String CONTENT_LIST_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RECORDINGS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_RECORDINGS;

        public static final String TABLE_NAME = "recordings";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_RECORDING_NAME = "name";
        public static final String COLUMN_FILE_PATH = "path";
        public static final String COLUMN_RECORDING_LENGTH = "length";
        public static final String COLUMN_RECORDING_TIME = "time";




    }

}
