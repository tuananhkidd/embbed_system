package com.kidd.embedded_system.presenter;

public class FCMEvent {
    String fcmToken;

    public String getFcmToken() {
        return fcmToken;
    }

    public void setFcmToken(String fcmToken) {
        this.fcmToken = fcmToken;
    }

    public FCMEvent() {

    }

    public FCMEvent(String fcmToken) {

        this.fcmToken = fcmToken;
    }
}
