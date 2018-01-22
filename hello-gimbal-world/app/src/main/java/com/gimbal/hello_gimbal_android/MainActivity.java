package com.gimbal.hello_gimbal_android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.widget.TextView;
import com.gimbal.android.PlaceEventListener;
import com.gimbal.android.Place;
import com.gimbal.android.PlaceManager;
import com.gimbal.android.Visit;

import android.app.Fragment;
import android.app.Activity;
import android.content.SharedPreferences;

public class MainActivity extends ActionBarActivity {
    private GimbalEventReceiver gimbalEventReceiver;
    private GimbalEventListAdapter adapter;
    public TextView txtview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, AppService.class);

        startService(new Intent(this, AppService.class));


        adapter = new GimbalEventListAdapter(this);

        /*
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        */
        txtview = (TextView) findViewById(R.id.textView02);

        SharedPreferences msettings = this.getSharedPreferences("settings", Context.MODE_PRIVATE);

        txtview.setText(msettings);

        //txtview.setText(intent.getAction(AppService.setupGimbalPlaceManager().onVisitStart().getPlace().getName());



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
