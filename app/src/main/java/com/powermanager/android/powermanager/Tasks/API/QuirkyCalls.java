package com.powermanager.android.powermanager.Tasks.API;
import com.powermanager.android.powermanager.Tasks.LoginResponse;

import java.util.Map;

import co.touchlab.android.threading.errorcontrol.NetworkException;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Query;
/**
 * @author zafrani (david@touchlab.co).
 */
public interface QuirkyCalls{
    @FormUrlEncoded
    @POST("/oauth2/token")
    LoginResponse login(@Field("client_id")String cId, @Field("client_secret")String cSecret, @Field("username")String username,@Field("password")String password,@Field("grant_type")String grantType) throws NetworkException;

    @GET("/users/me/wink_devices")
    Map wink()throws NetworkException;

    @PUT ("/powerstrips/{power_id}")
    void changePower(@Query("power_id")String powerId)throws NetworkException;
}
