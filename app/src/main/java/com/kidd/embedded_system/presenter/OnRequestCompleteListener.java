package com.kidd.embedded_system.presenter;



public interface OnRequestCompleteListener {
    void onSuccess();
    void onServerError(String message);
}
