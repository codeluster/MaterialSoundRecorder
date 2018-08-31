package com.example.tanmay.soundrecorder.Data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

public class RecordingsProvider extends ContentProvider {

    public static final String LOG_TAG = RecordingsProvider.class.getSimpleName();

    private static final int RECORDINGS = 1029;
    private static final int RECORDINGS_ID = 3048;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(RecordingsContract.RecordingsEntry.RECORDINGS_CONTENT_AUTHORITY, RecordingsContract.PATH_RECORDINGS, RECORDINGS);
        sUriMatcher.addURI(RecordingsContract.RecordingsEntry.RECORDINGS_CONTENT_AUTHORITY, RecordingsContract.PATH_RECORDINGS + "/#", RECORDINGS_ID);
    }

    private RecordingsDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new RecordingsDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        Cursor cursor;

        switch (sUriMatcher.match(uri)) {
            case RECORDINGS:
                cursor = database.query(RecordingsContract.RecordingsEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case RECORDINGS_ID:
                selection = RecordingsContract.RecordingsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                cursor = database.query(RecordingsContract.RecordingsEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            default:
                throw new IllegalArgumentException("The provided Uri " + uri.toString() + " is not valid.");

        }

        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {


        if (sUriMatcher.match(uri) != RECORDINGS)
            throw new IllegalArgumentException("Insertion at provided Uri " + uri.toString() + " is not possible");


        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        long foo = database.insert(RecordingsContract.RecordingsEntry.TABLE_NAME, null, contentValues);

        if (foo == -1) Log.e(LOG_TAG, "Failed to insert row for " + uri.toString());

        return ContentUris.withAppendedId(uri, foo);


    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        switch (sUriMatcher.match(uri)) {

            case RECORDINGS:
                int foo = deleteItem(selection, selectionArgs);
                if (foo == 0) {
                    Log.e(LOG_TAG, "Deletion failed for " + uri.toString());
                } else return foo;
                break;


            case RECORDINGS_ID:
                selection = RecordingsContract.RecordingsEntry._ID + "=?";
                selectionArgs = new String[]{String.valueOf(ContentUris.parseId(uri))};
                int goo = deleteItem(selection, selectionArgs);
                if (goo == 0) {
                    Log.e(LOG_TAG, "Deletion failed for " + uri.toString());
                } else return goo;

            default:
                throw new IllegalArgumentException("Deletion not supported for " + uri.toString());

        }

        // Unreachable statement
        return 0;

    }

    private int deleteItem(String selection, String[] selectionArgs) {

        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        return database.delete(RecordingsContract.RecordingsEntry.TABLE_NAME, selection, selectionArgs);

    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {

        if (contentValues.size() < 1) return -1;

        switch (sUriMatcher.match(uri)) {

            case RECORDINGS:
                int foo = updateItem(contentValues, s, strings);
                if (foo == 0) {
                    Log.e(LOG_TAG, "Update failed for " + uri.toString());
                } else return foo;
                break;

            case RECORDINGS_ID:
                s = RecordingsContract.RecordingsEntry._ID + "=?";
                strings = new String[]{String.valueOf(ContentUris.parseId(uri))};
                int goo = updateItem(contentValues, s, strings);
                if (goo == 0) {
                    Log.e(LOG_TAG, "Update failed for " + uri.toString());
                } else return goo;
                break;

            default:
                throw new IllegalArgumentException("Update is not supported for " + uri.toString());
        }

        // Unreachable statement
        return -1;

    }

    private int updateItem(ContentValues values, String selection, String[] selectionArgs) {

        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        return database.update(RecordingsContract.RecordingsEntry.TABLE_NAME, values, selection, selectionArgs);

    }
}
