package com.example.braintech.postretrofitdemo;


import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface LoginInterface {
    @Headers("appKey:b7de1026-0408-4983-ad2e-2deac08245b6")
    @FormUrlEncoded
    @POST("API/")
    Call<LoginModel> getAccess(@Field("username") String username
            , @Field("password") String password);


    @Headers("appKey:b7de1026-0408-4983-ad2e-2deac08245b6")
    @POST("Account/AuthenticateUser")
    Call<LoginModel> getAccess(@Header("Content-Type")
                                       String contentType,
                               @Header("Cache-Control")
                                       String cache,
                               @Body RequestBody params);



}
