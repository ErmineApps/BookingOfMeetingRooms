package kondratkov.bookingofmeetingrooms.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

    PushNotification mPushNotification;

    public MyIntentService() {
        super("MyIntentService");
        mPushNotification = new PushNotification(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            long endTime = System.currentTimeMillis() + 10*1000;
            while (true) {
                synchronized (this) {
                    try {
                        mPushNotification.checkPush();
                        wait(10000);
                    } catch (Exception e) {
                    }
                }
            }
        }
    }

}
