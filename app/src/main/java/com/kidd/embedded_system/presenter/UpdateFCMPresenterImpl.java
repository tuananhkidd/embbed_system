package com.kidd.embedded_system.presenter;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.storage.FirebaseStorage;

public class UpdateFCMPresenterImpl implements UpdateFCMPresenter {
    private Context context;
    private UpdateFCMInteractor updateFCMInteractor;

    public UpdateFCMPresenterImpl(Context context) {
        this.context = context;
        this.updateFCMInteractor = new UpdateFCMInteractorImpl(context);
    }

    @Override
    public void updateFCM(String token) {
//        String token = FirebaseInstanceId.getInstance().getToken();
        updateFCMInteractor.updateFCMToken(token, new OnRequestCompleteListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServerError(String message) {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
