package com.example.oschina.application;

import android.app.Application;

import com.example.oschina.utils.SpUtils;
import com.itheima.retrofitutils.ItheimaHttp;

public class OsChina extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ItheimaHttp.init(this, "https://www.oschina.net/");
        SpUtils.loadReadState(this);
    }

}
