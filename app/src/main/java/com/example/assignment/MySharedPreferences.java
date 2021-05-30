package com.example.assignment;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreferences {
    public static Context mContext;
    public static SharedPreferences prefs;
    public static SharedPreferences.Editor editor;

    public static void setPreference(Context context, String key, String value) {
        mContext = context;
        editor = mContext.getSharedPreferences("AndroidTask_Fin_Sreelatha", Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getPreferences(Context context, String key) {
        mContext = context;
        prefs = mContext.getSharedPreferences("AndroidTask_Fin_Sreelatha", Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }

    public static void clearPreferences(Activity context) {
        mContext = context;
        SharedPreferences settings = mContext.getSharedPreferences("AndroidTask_Fin_Sreelatha", Context.MODE_PRIVATE);
        settings.edit().clear().apply();
    }
}