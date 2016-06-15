package com.osvr.serverlauncher;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Jeremy on 6/12/2016.
 */
public class BootHandler extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("OSVR", "[OSVR] Got an onReceive message.");
        //if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Log.i("OSVR", "[OSVR] Got an ACTION_BOOT_COMPLETED intent.");
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        //}
    }
}
