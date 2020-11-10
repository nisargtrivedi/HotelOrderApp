package app.nisargapp.hotelorderapp.FCM;

import android.content.DialogInterface;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import app.nisargapp.hotelorderapp.Utility.AppPreferences;

public class FCMMessage extends FirebaseMessagingService {

    public static String TAG="HOTEL APP------------->";
    AppPreferences appPreferences;
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());


//            {moredata=dd, message={"sound":"mySound","icon":"myIcon","title":"title","body":"INPROCESS"}}

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                //scheduleJob();
            } else {
                // Handle message within 10 seconds
                //handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

        }
    }

    @Override
    public void onNewToken(@NonNull String token) {
        Log.d(TAG, "Refreshed token: " + token);
    appPreferences=new AppPreferences(this);
    appPreferences.set("TOKEN",token);
        //super.onNewToken(token);
    }

}
