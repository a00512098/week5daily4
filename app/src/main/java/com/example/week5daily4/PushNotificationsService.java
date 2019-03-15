package com.example.week5daily4;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class PushNotificationsService extends FirebaseMessagingService {

    private static final int REQUEST_CODE = 512;
    private static final int NOTIFICATION_ID = 98;
    public static final String CHANNEL_ID = "PUSH_NOTIFICATION_SERVICE_CHANNEL";
    public static final String CHANNEL_NAME = "FIREBASE_PUSH_NOTIFICATION_SERVICE_CHANNEL";

    public PushNotificationsService() {
    }

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage);
    }

    public void showNotification(RemoteMessage remoteMessage){
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent
                .getActivity(this, REQUEST_CODE, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        String title;
        if (remoteMessage.getNotification().getTitle() != null) {
            title = remoteMessage.getNotification().getTitle();
        } else {
            title = "FireBase Notification";
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentIntent(pendingIntent)
                .setContentTitle(title)
                .setContentText(remoteMessage.getNotification().getBody())
                .setSmallIcon(R.drawable.ic_backup_black_24dp);
        builder.setChannelId(CHANNEL_ID);
        notificationManager.notify(NOTIFICATION_ID, builder.build());

    }
}
