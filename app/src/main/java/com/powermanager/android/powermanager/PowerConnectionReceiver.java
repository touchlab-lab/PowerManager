package com.powermanager.android.powermanager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.util.Log;
/**
 * @author zafrani (david@touchlab.co).
 */
public class PowerConnectionReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context,Intent intent){
        int status=intent.getIntExtra(BatteryManager.EXTRA_STATUS,-1);
        boolean isCharging=status==BatteryManager.BATTERY_STATUS_CHARGING||status==BatteryManager.BATTERY_STATUS_FULL;
        int chargePlug=intent.getIntExtra(BatteryManager.EXTRA_PLUGGED,-1);
        boolean usbCharge=chargePlug==BatteryManager.BATTERY_PLUGGED_USB;
        boolean acCharge=chargePlug==BatteryManager.BATTERY_PLUGGED_AC;
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);
        if (batteryStatus==null)
            return;
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL,-1);
        Log.e("BC Received Level",""+level);
    }
}
