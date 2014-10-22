package com.powermanager.android.powermanager.Tasks;
import android.content.Context;

import com.powermanager.android.powermanager.DataHelper;
import com.powermanager.android.powermanager.R;
import com.powermanager.android.powermanager.Tasks.API.QuirkyCalls;

import co.touchlab.android.threading.tasks.TaskQueue;
/**
 * @author zafrani (david@touchlab.co).
 */
public class LogInTask extends  TaskQueue.Task{
    private String username, password;
public LoginResponse res;
    public LogInTask(String username,String password){
        this.username=username;
        this.password=password;
    }

    @Override
    protected void run(Context context) throws Exception{
         res=DataHelper.makeRequestAdapter(context).create(QuirkyCalls.class).login(context.getResources().getString(R.string.client_id),context.getResources().getString(R.string.client_secret),username,password,"password");
    }

    @Override
    protected boolean handleError(Throwable throwable){
        return false;
    }
}
