package com.jh.litepaltest;

import android.app.Application;

import com.jh.litepaltest.bean.UserBean;
import com.jh.litepaltest.utils.SharedPreferenceUtil;
import com.jh.litepaltest.utils.Utils;

import org.litepal.LitePal;


public class App extends Application {

    public static UserBean accountInfo = null;
    private static App instance;

    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);

        SharedPreferenceUtil.getInstance().init(this);

    }
}
