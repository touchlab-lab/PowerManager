package com.powermanager.android.powermanager;
import android.content.Context;
import android.content.SharedPreferences;
/**
 * @author zafrani (david@touchlab.co).
 */

public class AppPrefs{
    private static AppPrefs instance;
    private SharedPreferences prefs;
    public static final String USER_ID="USER_ID";

    public static synchronized AppPrefs getInstance(Context context){
        if(instance==null){
            instance=new AppPrefs();
            instance.prefs=context.getSharedPreferences("APP_PREFS",Context.MODE_PRIVATE);
        }
        return instance;
    }

    public Integer getUserId(){
        if(prefs.contains(USER_ID)){
            return prefs.getInt(USER_ID,-1);
        }else{
            return -1;
        }
    }

    public void setUserId(int userId){
        prefs.edit().putInt(USER_ID,userId).apply();
    }

    public String getUserToken(){
        return "";
    }
}