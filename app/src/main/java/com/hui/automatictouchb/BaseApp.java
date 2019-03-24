package com.hui.automatictouchb;

import android.app.Application;
import android.content.Intent;

/**
 * @author Created by waterHYH on 2019/3/22.
 */
public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        startService(new Intent(this, DaemonService.class));
    }
}
