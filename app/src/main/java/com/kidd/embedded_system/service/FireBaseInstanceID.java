package com.kidd.embedded_system.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.kidd.embedded_system.presenter.FCMEvent;

import org.greenrobot.eventbus.EventBus;


public class FireBaseInstanceID extends FirebaseInstanceIdService {
    private static final String TAG = FireBaseInstanceID.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        EventBus.getDefault().post(new FCMEvent(refreshedToken));
        Log.i(TAG, refreshedToken);
//        FirebaseMessaging.getInstance().subscribeToTopic(Constants.FIREBASE_NOTIFICATION_TOPIC_GLOBAL);

    }
}

