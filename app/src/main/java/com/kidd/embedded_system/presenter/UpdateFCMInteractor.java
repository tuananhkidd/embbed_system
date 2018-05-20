package com.kidd.embedded_system.presenter;

public interface UpdateFCMInteractor extends BaseInteractor {
    void updateFCMToken(String token,OnRequestCompleteListener listener);
}
