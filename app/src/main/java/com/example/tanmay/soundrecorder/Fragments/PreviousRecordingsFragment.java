package com.example.tanmay.soundrecorder.Fragments;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tanmay.soundrecorder.Data.RecordingsContract;
import com.example.tanmay.soundrecorder.R;

public class PreviousRecordingsFragment extends Fragment {

    ListView listView;
    Context context;


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

        Cursor allRecordings = context.getContentResolver().query(RecordingsContract.RecordingsEntry.CONTENT_URI, null, null, null, null);

        listView.setAdapter(new RecordingsAdapter(context, allRecordings));

        return v;
    }

    private class RecordingsAdapter extends CursorAdapter {


        public RecordingsAdapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            return LayoutInflater.from(context).inflate(R.layout.recordings_list_item, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {

            while (cursor.moveToNext()) {

                TextView title = view.findViewById(R.id.recordings_list_item_title);
                TextView length = view.findViewById(R.id.recordings_list_item_length);
                TextView date = view.findViewById(R.id.recordings_list_item_date);
                TextView time = view.findViewById(R.id.recordings_list_item_time);

                title.setText(cursor.getString(cursor.getColumnIndexOrThrow(RecordingsContract.RecordingsEntry.COLUMN_RECORDING_NAME)));

            }

        }
    }


}
