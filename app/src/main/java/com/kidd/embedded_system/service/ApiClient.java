package com.kidd.embedded_system.service;



import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by TuanAnhKid on 3/12/2018.
 */
//SingleTon Pattern
public class ApiClient {
    private static Retrofit retrofit = getClient();

    public static Retrofit getClient() {
        if (retrofit == null) {
            String url = "http://192.168.0.104:6789";
//            String url = "http://35.185.179.159:1991";
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
