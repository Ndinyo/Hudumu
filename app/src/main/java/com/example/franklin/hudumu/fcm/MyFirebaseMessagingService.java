package com.example.franklin.hudumu.fcm;

import android.app.Notification;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.franklin.hudumu.MainActivity;
import com.example.franklin.hudumu.R;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.widget.Toast;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FCM Service";

    //FCM Upstream
    @Override
    public void onMessageSent(String s) {
        //super.onMessageSent(s);
        Log.d(TAG, "OnMessageSent: " + s);

    }

    @Override
    public void onSendError(String s, Exception e) {
        //super.onSendError(s, e);
        Log.d(TAG, "OnSendError: " + s);
        Log.d(TAG, "Exception: " + e);
    }

    //FCM from Firebase
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        //super.onMessageReceived(remoteMessage);

        //Check if message contains a notification payload
        if (remoteMessage.getNotification() != null) {
            sendErrandNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
        }

        //Handle FCM Messages here
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        //Get FCM token for a specific device and target it
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "App's token: " + token);
    }


    //Handle FCM Message
    private void sendErrandNotification(String title, String body) {

        try {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);
            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle(title)
                    .setContentText(body)
                    .setAutoCancel(true)
                    .setSound(soundUri)
                    .setContentIntent(pendingIntent);

            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(0, builder.build());
        } catch (Exception e) {
            Toast.makeText(MyFirebaseMessagingService.this, "Failed to send confirmation message!" + e.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onNewToken(String s) {
        Log.d(TAG, "Refreshed Token: " + s);
        super.onNewToken(s);
    }
}
