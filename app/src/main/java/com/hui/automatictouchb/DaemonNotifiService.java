package com.hui.automatictouchb;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by HUI on 2017/8/27.
 */

public class DaemonNotifiService extends Service{
	public static final int daemonNotificationId = 15522;
    private static final String TAG = "DaemonNotifiService";

    @Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
        Log.i(TAG,"->kkkk onCreate");
		showDaemonNotification(this);
		stopSelf();
	}

	private static void showDaemonNotification(Service context) {
		Notification notification = new Notification.Builder(context).build();
		context.startForeground(daemonNotificationId, notification);
	}

	@Override
	public void onDestroy() {
        Log.i(TAG,"->kkkk onDestroy");
        stopForeground(true);
        super.onDestroy();
	}

	public static void startDaemonNotifiService(Service service){
		showDaemonNotification(service);
		service.startService(new Intent(service, DaemonNotifiService.class));

	}
}
