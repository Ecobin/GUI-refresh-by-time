package io.github.ecobin.gui_refresh_by_time;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends Activity {

    public static final String[] WORD_BANK = {"Hello World!", "Ice cream", "Soda", "Cake", "Apple"};
    public static final int TIMER = 10; // 10 seconds timer.
    public TextView textView;

    IntentFilter fil;
    BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.hello_world);

        fil = new IntentFilter("mybroadcast");
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i(">>>>>>>>>>>", "Updating UI!");
                updateGUI();
            }
        };

        AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent in = new Intent(this, UpdateService.class);
        PendingIntent pending = PendingIntent.getService(this, 1234, in, 0);

        manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 1000 * TIMER, 1000 * TIMER, pending);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(receiver, fil);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void updateGUI() {
        int randomNumber = (int) (Math.random() * WORD_BANK.length);
        textView.setText(WORD_BANK[randomNumber]);
    }
}
