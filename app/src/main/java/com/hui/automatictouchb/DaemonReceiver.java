package com.hui.automatictouchb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by HUI on 2017/7/19.
 */

public class DaemonReceiver extends BroadcastReceiver {
    private static final String TAG = "DaemonReceiver";

    @Override
	public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"->kkkk onReceive");
        context.startService(new Intent(context,DaemonService.class));//销毁时重新启动

    }
}
