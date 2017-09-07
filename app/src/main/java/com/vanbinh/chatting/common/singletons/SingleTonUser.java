package com.vanbinh.chatting.common.singletons;

import android.content.Context;

import com.vanbinh.chatting.common.sharedpreference.MySharedPreference;
import com.vanbinh.chatting.common.sqlite.UserSQLiteManager;
import com.vanbinh.chatting.models.User;

/**
 * Created by vanbinh on 8/18/2017.
 */

public class SingleTonUser {
    private static User instance = null;

    private SingleTonUser(Context context) {

    }

    public static synchronized User getInstance(Context context) {
        if (instance == null) {
            UserSQLiteManager sqLiteManager = UserSQLiteManager.getInstance(context);
            instance = sqLiteManager.getUser();
        }
        return instance;
    }
    public static void setInstance(Context context){
        UserSQLiteManager sqLiteManager =UserSQLiteManager.getInstance(context);
        instance = sqLiteManager.getUser(getDeviceToken(context));

    }
    public static void deleteInstance() {
        instance = null;
    }

    private static String getDeviceToken(Context context) {
        return new MySharedPreference.BuildShare()
                .init(context, MySharedPreference.SR_MAIN)
                .get()
                .getString(MySharedPreference.SR_TOKEN, null);
    }
}

