package com.kidd.embedded_system.presenter;

import android.content.Context;

import com.kidd.embedded_system.service.ApiClient;
import com.kidd.embedded_system.service.FCMService;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class UpdateFCMInteractorImpl implements UpdateFCMInteractor {
    private Context context;
    private CompositeDisposable compositeDisposable;

    public UpdateFCMInteractorImpl(Context context) {
        this.context = context;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void updateFCMToken(String token, OnRequestCompleteListener listener) {
        Observable<Response<ResponseBody<String>>> observable = ApiClient.getClient().create(FCMService.class)
                .updateFCMToken(token);
        Disposable disposable = observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            switch (response.code()){
                                case 200:{
                                    listener.onSuccess();
                                    break;
                                }
                                default:{
                                    listener.onServerError(response.message());
                                    break;
                                }
                            }
                        },
                        error -> {
                            listener.onServerError(error.getMessage());
                        }
                );

        compositeDisposable.add(disposable);
    }

    @Override
    public void onViewDestroy() {
        compositeDisposable.clear();
    }
}
