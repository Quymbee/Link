package com.gimbal.hello_gimbal_android;

import java.util.Locale;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;
import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;

import org.w3c.dom.Text;


public class MainActivity extends Activity {
    public GimbalEventReceiver gimbalEventReceiver;
    public GimbalEventListAdapter adapter;

   // public TextToSpeech tts;

    public TextView beaconTextView;
    public TextView addressTextView;
    public String address;
    public String temp;
 //   public String temp1 = " ";
   // public String fullTemp;
    //public String beaconTxt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, AppService.class));
        adapter = new GimbalEventListAdapter(this);


        beaconTextView = findViewById(R.id.beaconText);
        addressTextView = findViewById(R.id.addressText);

        temp = AppService.placee;
        address = "10th Ave and W 30th St is the location of the nearest Link";

        addressTextView.setText(address);
        beaconTextView.setText(temp);

        //  speakOut();



    }
/*
    public void speakOut(){
       // CharSequence txt = beaconTextView.getText();
        CharSequence txt = "I hope this works!";
        tts.speak(txt, TextToSpeech.QUEUE_ADD, null, "id1");
    }
*/


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
        /*
        if (tts != null){
            tts.stop();
            tts.shutdown();
        }
        */

        super.onDestroy();

    }

    class GimbalEventReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction() != null) {
                if (intent.getAction().compareTo(GimbalDAO.GIMBAL_NEW_EVENT_ACTION) == 0) {

                   // temp1 = temp;

                 //   fullTemp = "Temp: " + temp + " and Temp1: " + temp1;


                    /*
                    beaconTxt = beaconTextView.getText().toString();

                    if(beaconTxt.equals("There are no beacons found!"))
                    {
                        address = " ";
                    } else {
                        address = "10th Ave and W 30th St is the location of the nearest Link";
                    }
                    */

                    /*
                    if (temp1.equalsIgnoreCase(temp)) {
                        Log.v("equals", fullTemp);
                    } else{
                        Log.v("not equals", fullTemp);
                    }
*/
                    adapter.setEvents(GimbalDAO.getEvents(getApplicationContext()));

                }

            }
        }
    }


}
