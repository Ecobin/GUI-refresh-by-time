package io.github.ecobin.gui_refresh_by_time;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class UpdateService extends Service {
    public UpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Intent myIntent = new Intent("mybroadcast");
        sendBroadcast(myIntent);
        return START_NOT_STICKY;
    }
}
