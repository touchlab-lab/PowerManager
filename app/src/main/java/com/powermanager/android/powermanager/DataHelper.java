package com.powermanager.android.powermanager;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.ErrorHandler;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.android.AndroidLog;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by kgalligan on 8/1/14.
 */
public class DataHelper{
    public static final int CONNECT_TIMEOUT=45;
    public static final int READ_TIMEOUT=30;
    public static final String JSON_DATE_FORMAT="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String JSON_DATE_FORMAT_SHORT="yyyy-MM-dd'T'HH:mm:ss'Z'";

    public static RestAdapter.Builder makeSuperbusRequestAdapterBuilder(Context context,String dateTimeFormat){
        return makeRequestAdapterBuilder(context,new ErrorHandler(){
            @Override
            public Throwable handleError(RetrofitError cause){
                return null;
            }
        },dateTimeFormat);
    }

    public static RestAdapter makeRequestAdapter(final Context context){
        return makeRequestAdapterBuilder(context,JSON_DATE_FORMAT_SHORT).build();
    }

    public static RestAdapter.Builder makeRequestAdapterBuilder(final Context context,String dateTimeFormat){
        return makeRequestAdapterBuilder(context,new RetrofitErrorHandler(),dateTimeFormat);
    }

    private static RestAdapter.Builder makeRequestAdapterBuilder(final Context context,ErrorHandler errorHandler,String dateTimeFormat){
        RequestInterceptor requestInterceptor=new RequestInterceptor(){
            @Override
            public void intercept(RequestFacade request){
                request.addHeader("Accept","application/json");
                AppPrefs instance=AppPrefs.getInstance(context);

                if(instance.getUserToken()!=null)
                    request.addHeader("X-Auth-Token",instance.getUserToken());
            }
        };

        Gson gson=new GsonBuilder().setDateFormat(dateTimeFormat).create();

        GsonConverter gsonConverter=new GsonConverter(gson);
        String baseURL=context==null?"https://winkapi.quirky.com/":context.getString((R.string.baseurl));

        RestAdapter.Builder builder=new RestAdapter.Builder().setRequestInterceptor(requestInterceptor).setConverter(gsonConverter).setLogLevel(RestAdapter.LogLevel.FULL).setLog(new AndroidLog("PowerManager")).setEndpoint(baseURL);

        if(errorHandler!=null)
            builder.setErrorHandler(errorHandler);

        builder.setClient(new OkClient(makeTimeoutClient(READ_TIMEOUT,CONNECT_TIMEOUT)));

        return builder;
    }

    public static class RetrofitErrorHandler implements ErrorHandler{
        @Override
        public Throwable handleError(RetrofitError cause){
            if(cause.isNetworkError()){
                return new Exception(cause.getCause());
            }

            return cause;
        }
    }

    private static OkHttpClient makeTimeoutClient(int readTimeout,int connectTimeout){
        final OkHttpClient okHttpClient=new OkHttpClient();
        okHttpClient.setReadTimeout(readTimeout,TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(connectTimeout,TimeUnit.SECONDS);

        return okHttpClient;
    }
}
