package com.dabin.www.yuekaob;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by Dabin on 2017/11/23.
 */

public class MyApp extends Application{
    private static MyApp mcontext;

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        if(mcontext == null){
            mcontext = this;
        }
    }
}
