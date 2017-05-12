package com.serchinfobysubwaynameservice;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

//https://brunch.co.kr/@henen/25

public interface ApiService {
    String DEFAULT_URL = "http://openAPI.seoul.go.kr:8088/4e786b4d716d6e6d37306b76554859/json/SearchInfoBySubwayNameService/1/5/강남/";

    @GET
    Call<ResponseBody> getComment(@Url String anEmptyString);
}