package com.kidd.embedded_system.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.kidd.embedded_system.MainActivity;
import com.kidd.embedded_system.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;

public class FireBaseMessaging extends FirebaseMessagingService {

    private static final String TAG = FireBaseMessaging.class.getSimpleName();
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();
        JSONObject objectData = new JSONObject(data);
        Log.i(TAG, "onMessageReceived: "+objectData.toString());
        try {
            Log.i(TAG, "onMessageReceived: gas  "+objectData.getString("gas"));
            Log.i(TAG, "onMessageReceived: tempurature  "+objectData.getString("tempurature"));
            String gasstr = objectData.getString("gas");
            String temp = objectData.getString("tempurature");
            double gas =Double.parseDouble(gasstr);
            double tempurature = Double.parseDouble(temp);
            buildNoti(tempurature,gas);
        }catch (Exception e){
            Log.i(TAG, "error: "+e.getCause());
        }

    }

    public void buildNoti(double temp,double gas) {
        NumberFormat formatter = new DecimalFormat("#0.0");

        String contentText = ("Nồng độ khí gas vượt quá mức cho phép ( Nồng độ : "
                +formatter.format(gas)+" và nhiệt độ : "+formatter.format(temp))+" )";

        String contentTitle = "Cảnh báo nguy hiểm";
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                1,
                new Intent(this, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle(getString(R.string.app_name)+" - "+contentTitle)
                        .setContentText(contentText)
                        .setContentIntent(pendingIntent)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(contentText))
                        .setDefaults(Notification.DEFAULT_SOUND)
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setAutoCancel(true);



        final Notification notification = mBuilder.build();
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, notification);
    }

}
