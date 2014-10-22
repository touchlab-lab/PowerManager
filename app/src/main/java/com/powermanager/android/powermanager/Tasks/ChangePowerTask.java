package com.powermanager.android.powermanager.Tasks;
import android.content.Context;

import com.powermanager.android.powermanager.DataHelper;
import com.powermanager.android.powermanager.Tasks.API.QuirkyCalls;

import co.touchlab.android.threading.tasks.TaskQueue;
/**
 * @author zafrani (david@touchlab.co).
 */
public class ChangePowerTask extends TaskQueue.Task{
    private String powerId;
    private boolean turnOn;

    public ChangePowerTask(String powerId,boolean turnOn){
        this.powerId=powerId;
        this.turnOn=turnOn;
    }

    @Override
    protected void run(Context context) throws Exception{
        DataHelper.makeRequestAdapter(context).create(QuirkyCalls.class).changePower(powerId);
    }

    @Override
    protected boolean handleError(Throwable throwable){
        return false;
    }
}
