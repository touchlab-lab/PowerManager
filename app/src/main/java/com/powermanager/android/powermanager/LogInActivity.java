package com.powermanager.android.powermanager;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
/**
 * @author zafrani (david@touchlab.co).
 */
public class LogInActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewById(R.id.login).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
    }
}
