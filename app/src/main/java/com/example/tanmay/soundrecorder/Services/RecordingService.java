package com.example.tanmay.soundrecorder.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class RecordingService extends Service {


    private static final String LOG_TAG = RecordingService.class.getSimpleName();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
