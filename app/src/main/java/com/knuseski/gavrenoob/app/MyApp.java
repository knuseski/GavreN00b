package com.knuseski.gavrenoob.app;

import android.app.Application;


public class MyApp extends Application {

    public static final String TAG = "GavreN00b";
    public static final String GET_USER = "https://postman-echo.com/get?name=Gavre&nickname=n00b&img=http://www.sundaynews.co.zw/wp-content/uploads/2017/09/laughing_donkey.jpg";
    public static final String GET_USERS = "https://postman-echo.com/get";

    private static MyApp instance;

    public static MyApp getAppContext() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}