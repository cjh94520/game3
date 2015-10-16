package com.smartman.game3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by jiahui.chen on 2015/10/15.
 */
public class GameReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(action != null && !action.isEmpty() && action.equals(JPushInterface.ACTION_NOTIFICATION_RECEIVED)) {
            Intent goIntent = new Intent();
            goIntent.setClass(context, MainActivity.class);
            goIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(goIntent);
            Log.i("GameReceiver", "GameReceiver is running");
        }
    }
}
