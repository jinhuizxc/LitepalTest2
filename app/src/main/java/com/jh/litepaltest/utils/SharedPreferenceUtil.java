package com.jh.litepaltest.utils;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtil {

    private static SharedPreferenceUtil instance;

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";


    private SharedPreferenceUtil() {
    }

    public static SharedPreferenceUtil getInstance() {
        if (instance == null) {
            instance = new SharedPreferenceUtil();
        }
        return instance;
    }


    public void init(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

    }
}
