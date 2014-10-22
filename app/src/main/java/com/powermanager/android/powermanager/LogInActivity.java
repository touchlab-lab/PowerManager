package com.powermanager.android.powermanager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.powermanager.android.powermanager.Tasks.LogInTask;

import co.touchlab.android.threading.eventbus.EventBusExt;
import co.touchlab.android.threading.tasks.TaskQueue;
/**
 * @author zafrani (david@touchlab.co).
 */
public class LogInActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EventBusExt.getDefault().register(this);
        setContentView(R.layout.activity_login);
        final EditText username=(EditText)findViewById(R.id.email);
        final EditText password=(EditText)findViewById(R.id.password);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TaskQueue.execute(getApplicationContext(),new LogInTask(username.getText().toString(),password.getText().toString()));
            }
        });
    }

    @Override
    protected void onDestroy(){
        EventBusExt.getDefault().unregister(this);
        super.onDestroy();
    }

    public void onEventMainThread(LogInTask task){
        AppPrefs.getInstance(getApplicationContext()).saveUserData(task.res);
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}
