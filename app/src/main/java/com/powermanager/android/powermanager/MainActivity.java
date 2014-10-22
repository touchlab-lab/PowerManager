package com.powermanager.android.powermanager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        int user = AppPrefs.getInstance(getApplicationContext()).getUserId();
        if (user==-1){
            startActivity(new Intent(getApplicationContext(),LogInActivity.class));
            return;
        }
        setContentView(R.layout.activity_main);
        IntentFilter ifilter=new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        ifilter.addAction(Intent.ACTION_POWER_CONNECTED);
        ifilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(new PowerConnectionReceiver(),ifilter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id=item.getItemId();

        //noinspection SimplifiableIfStatement
        if(id==R.id.action_settings){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
