package com.hui.automatictouchb;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 开机自启，app保活
 * 登录检查
 * Created by HUI on 2017/7/19.
 */

public class DaemonService extends Service {
    private static final String TAG = "DaemonService";

    @Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
        Log.i(TAG,"->kkkk onCreate");
        DaemonNotifiService.startDaemonNotifiService(this);
	}

	private Handler handler;
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG,"->onStartCommand");
		if (handler == null) {
			handler = new Handler(getMainLooper()){
				@Override
				public void handleMessage(Message msg) {
					removeCallbacksAndMessages(null);
					sendEmptyMessageDelayed(0,5000);
					//互相保活
					try {
						Intent startIntent = new Intent();
						startIntent.setComponent(new ComponentName("com.hui.automatictouch","com.hui.service.DaemonService"));
						startIntent.setAction("com.hui.intent.action.DAEMON_SERVICE");
						startService(startIntent);
					}catch (Exception e){
						e.printStackTrace();
					}
				}
			};
		}
		handler.sendEmptyMessageDelayed(0,3000);
        return START_STICKY;
	}

	@Override
	public void onDestroy() {
        Log.i(TAG,"->kkkk onDestroy");
        stopForeground(true);
        startService(new Intent(this,DaemonService.class));//销毁时重新启动
        super.onDestroy();

	}

}
