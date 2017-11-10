package com.example.kondratkov.bookingofmeetingrooms.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {


    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            long endTime = System.currentTimeMillis() + 10*1000;
            while (true) {
                synchronized (this) {
                    try {

                        wait(10000, 2000);
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

}
