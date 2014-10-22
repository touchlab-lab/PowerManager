package com.powermanager.android.powermanager;
import android.content.Context;
import android.content.SharedPreferences;

import com.powermanager.android.powermanager.Tasks.LoginResponse;
/**
 * @author zafrani (david@touchlab.co).
 */

public class AppPrefs{
    private static AppPrefs instance;
    private SharedPreferences prefs;
    public static final String USER_TOKEN="USER_TOKEN";
    public static final String USER_REFRESH="USER_REFRESH";
    public static final String USER_TYPE="USER_TYPE";

    public static synchronized AppPrefs getInstance(Context context){
        if(instance==null){
            instance=new AppPrefs();
            instance.prefs=context.getSharedPreferences("APP_PREFS",Context.MODE_PRIVATE);
        }
        return instance;
    }

    public String getUserToken(){
        if(prefs.contains(USER_TOKEN))
            return prefs.getString(USER_TOKEN,null);
        else
            return null;
    }

    public String getType(){
        if(prefs.contains(USER_TYPE))
            return prefs.getString(USER_TYPE,null);
        else
            return null;
    }

    public void saveUserData(LoginResponse user){
        prefs.edit().putString(USER_TOKEN,user.data.access_token).apply();
        prefs.edit().putString(USER_REFRESH,user.data.refresh_token).apply();
        prefs.edit().putString(USER_TYPE,user.data.token_type).apply();
    }
}