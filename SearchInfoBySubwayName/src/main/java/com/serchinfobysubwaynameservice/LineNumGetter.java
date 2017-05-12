package com.serchinfobysubwaynameservice;

import android.content.Context;
import android.util.Log;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LineNumGetter {

    Retrofit retrofit;
    ApiService apiService;
    String test = null;

    public void getLineNum(String stationName) {
        retrofit = new Retrofit.Builder().baseUrl(apiService.DEFAULT_URL + stationName + "/").build();
        apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> comment = apiService.getComment("");

        comment.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    test = response.body().string();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}