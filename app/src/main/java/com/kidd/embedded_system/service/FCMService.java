package com.kidd.embedded_system.service;

import com.kidd.embedded_system.presenter.ResponseBody;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface FCMService {
    @POST("/api/gas/fcmtoken")
    Observable<Response<ResponseBody<String>>> updateFCMToken(@Body String fcmtoken);
}
