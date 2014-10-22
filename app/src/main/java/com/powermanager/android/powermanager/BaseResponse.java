package com.powermanager.android.powermanager;

/**
 * Created by kgalligan on 8/7/14.
 */
public class BaseResponse
{
    public Meta meta;

    public static class Meta
    {
        public boolean success;
        public String[] message;
    }
}
