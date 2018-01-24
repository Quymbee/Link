package com.gimbal.hello_gimbal_android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;

public class MainActivity extends Activity {
    public GimbalEventReceiver gimbalEventReceiver;
    public GimbalEventListAdapter adapter;

    public TextView beaconTextView;
    public TextView addressTextView;
    public String address;
    public String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, AppService.class));

        adapter = new GimbalEventListAdapter(this);
        /*
        Intent intent = new Intent(this, AppService.class);
        */

        /*
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        */

        beaconTextView = findViewById(R.id.beaconText);
        addressTextView = findViewById(R.id.addressText);

        address = "10th Ave and W 30th St";
        temp = AppService.placee;



        beaconTextView.setText(temp);
        addressTextView.setText(address);
        //txtview.setText(AppService.placee);




    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.setEvents(GimbalDAO.getEvents(getApplicationContext()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        gimbalEventReceiver = new GimbalEventReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(GimbalDAO.GIMBAL_NEW_EVENT_ACTION);
        intentFilter.addAction(AppService.APPSERVICE_STARTED_ACTION);
        registerReceiver(gimbalEventReceiver, intentFilter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(gimbalEventReceiver);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    class GimbalEventReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null) {
                if (intent.getAction().compareTo(GimbalDAO.GIMBAL_NEW_EVENT_ACTION) == 0) {
                    adapter.setEvents(GimbalDAO.getEvents(getApplicationContext()));
                }
            }
        }
    }


}
