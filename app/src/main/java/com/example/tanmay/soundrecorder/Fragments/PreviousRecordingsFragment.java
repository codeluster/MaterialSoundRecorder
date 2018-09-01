package com.example.tanmay.soundrecorder.Fragments;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tanmay.soundrecorder.Data.RecordingsContract;
import com.example.tanmay.soundrecorder.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class PreviousRecordingsFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    // Identifier for data loader
    private static final int REC_LOADER = 214;

    ListView listView;
    Context context;

    RecordingsAdapter adapter;

    public PreviousRecordingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_previous_recordings, container, false);

        listView = v.findViewById(R.id.previous_recordings_list);
        listView.setEmptyView(v.findViewById(R.id.no_recordings_view));

        context = getContext();

        adapter = new RecordingsAdapter(context, null);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });

        getLoaderManager().initLoader(REC_LOADER, null, this);

        return v;
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {

        String[] projection = new String[]{
                RecordingsContract.RecordingsEntry._ID,
                RecordingsContract.RecordingsEntry.COLUMN_RECORDING_NAME,
                RecordingsContract.RecordingsEntry.COLUMN_RECORDING_LENGTH,
                RecordingsContract.RecordingsEntry.COLUMN_RECORDING_TIME
        };

        return new CursorLoader(context,
                RecordingsContract.RecordingsEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);

    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {

        adapter.swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

        adapter.swapCursor(null);

    }

    private class RecordingsAdapter extends CursorAdapter {


        public RecordingsAdapter(Context context, Cursor c) {
            super(context, c);
        }

        private String millisToHMS(int lengthInMillis) {

            Integer secs = lengthInMillis / 1000;
            Integer mins = secs / 60;
            secs %= 60;
            Integer hours = mins / 60;
            mins %= 60;

            String time = secs.toString() + " s";

            if (mins > 0) time = mins.toString() + " m " + time;
            if (hours > 0) time = hours.toString() + " h " + time;

            return time;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.recordings_list_item, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

            if (cursor.getCount() > 0) {

                TextView title = view.findViewById(R.id.recordings_list_item_title);
                TextView length = view.findViewById(R.id.recordings_list_item_length);
                TextView date = view.findViewById(R.id.recordings_list_item_date);
                TextView time = view.findViewById(R.id.recordings_list_item_time);

                title.setText(cursor.getString(cursor.getColumnIndexOrThrow(RecordingsContract.RecordingsEntry.COLUMN_RECORDING_NAME)));
                length.setText(millisToHMS(cursor.getInt(cursor.getColumnIndexOrThrow(RecordingsContract.RecordingsEntry.COLUMN_RECORDING_LENGTH))));

                Date fDate = new Date(cursor.getInt(cursor.getColumnIndexOrThrow(RecordingsContract.RecordingsEntry.COLUMN_RECORDING_TIME)));
                DateFormat format = DateFormat.getDateTimeInstance();
                format.setTimeZone(TimeZone.getTimeZone("UTC"));
                String formattedDate = format.format(fDate);

                time.setText(formattedDate);


            }

        }
    }


}
