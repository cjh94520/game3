package com.smartman.game3;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by jiahui.chen on 2015/10/15.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //关闭调试模式
       // JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
