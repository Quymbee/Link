package com.gimbal.hello_gimbal_android;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.gimbal.android.Communication;
import com.gimbal.android.CommunicationListener;
import com.gimbal.android.CommunicationManager;
import com.gimbal.android.Gimbal;
import com.gimbal.android.PlaceEventListener;
import com.gimbal.android.PlaceManager;
import com.gimbal.android.Push;
import com.gimbal.android.Visit;

import org.slf4j.impl.LogLevel;

import java.util.LinkedList;
import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import android.util.Log;
import android.content.SharedPreferences;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class AppService extends Service {

    public static final String APPSERVICE_STARTED_ACTION = "appservice_started";
    private static final int MAX_NUM_EVENTS = 100;

    private PlaceEventListener placeEventListener;
    private CommunicationListener communicationListener;
    private LinkedList<String> events;


    public static final String PLACE_NAME = "Visit().Place().getPlace().getName()";



    @Override
    public void onCreate(){
        events = new LinkedList<>(GimbalDAO.getEvents(getApplicationContext()));

        Gimbal.setApiKey(this.getApplication(), "d9f80fb2-70c1-4947-bcd1-0f81ae9ccffc");
        setupGimbalPlaceManager();
        setupGimbalCommunicationManager();
        Gimbal.start();


        SharedPreferences placeName = this.getSharedPreferences(PLACE_NAME,);

        Log.v("TEST", "START2");
    }



    private void setupGimbalCommunicationManager() {
        communicationListener = new CommunicationListener() {
            @Override
            public Notification.Builder prepareCommunicationForDisplay(Communication communication, Visit visit, int notificationId) {
                addEvent(String.format( "Communication Delivered : %s", communication.getTitle()));
                // If you want a custom notification create and return it here
                return null;
            }

            @Override
            public Notification.Builder prepareCommunicationForDisplay(Communication communication, Push push, int notificationId) {
                addEvent(String.format( "Push Communication Delivered : %s", communication.getTitle()));
                // If you want a custom notification create and return it here
                return null;
            }

            @Override
            public void onNotificationClicked(List<Communication> communications) {
                for (Communication communication : communications) {
                    if(communication != null) {
                        addEvent("Communication Clicked");
                    }
                }
            }
        };
        CommunicationManager.getInstance().addListener(communicationListener);
    }

    private void setupGimbalPlaceManager() {
        placeEventListener = new PlaceEventListener() {

            @Override
            public void onVisitStart(Visit visit) {


                Log.v("PLACE NAME", "VISIT START");
                Log.v("PLACE NAME STATE", visit.getPlace().getName());



                /*
                Logger.getGlobal().log(Level.INFO, "VISIT START");
                Logger.getGlobal().log(Level.INFO, visit.getPlace().getName());
                addEvent(String.format("Start Visit for %s", visit.getPlace().getName()));
                */
            }

            @Override
            public void onVisitEnd(Visit visit) {
                addEvent(String.format("End Visit for %s", visit.getPlace().getName()));
            }
        };
        PlaceManager.getInstance().addListener(placeEventListener);
    }

    private void addEvent(String event) {
        while (events.size() >= MAX_NUM_EVENTS) {
            events.removeLast();
        }
        events.add(0, event);
        GimbalDAO.setEvents(getApplicationContext(), events);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        notifyServiceStarted();

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        PlaceManager.getInstance().removeListener(placeEventListener);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void notifyServiceStarted() {
        Intent intent = new Intent(APPSERVICE_STARTED_ACTION);
        sendBroadcast(intent);
    }
}
