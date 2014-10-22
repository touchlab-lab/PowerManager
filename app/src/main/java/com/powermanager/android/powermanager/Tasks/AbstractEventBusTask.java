package com.powermanager.android.powermanager.Tasks;

import android.content.Context;

import com.powermanager.android.powermanager.BaseResponse;

import co.touchlab.android.threading.errorcontrol.NetworkException;
import co.touchlab.android.threading.eventbus.EventBusExt;
import co.touchlab.android.threading.tasks.TaskQueue;

/**
 * Created by kgalligan on 8/8/14.
 */
public abstract class AbstractEventBusTask extends TaskQueue.Task{
    public BaseResponse response;

    @Override
    protected boolean handleError(Throwable e){
        if(e instanceof NetworkException){
            //TODO: All usages need to be reviewed.  May be problematic if a response is expected.
            if(response==null){
                response=new BaseResponse();
                response.meta=new BaseResponse.Meta();
            }

            response.meta.success=false;
            response.meta.message=new String[]{"Errah"};
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void onComplete(Context context){
        if(response!=null&&!response.meta.success){
            postErrorMessage();
        }else{
            EventBusExt.getDefault().post(this);
        }
    }

    protected void postErrorMessage(){
    }

    public boolean isSuccess(){
        if(response!=null&&response.meta!=null)
            return response.meta.success;
        else
            return false;
    }
}
