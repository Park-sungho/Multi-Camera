package com.multicamera.settings;

import com.multicamera.etc.Constants;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {
    public final static int PREFERENCE_DATA_DEFAULT = 0; 

    public static int getPreferenceResolution(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(Constants.KEY_RESOLUTION, PREFERENCE_DATA_DEFAULT);
    }

    public static int getPreferenceFramerate(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(Constants.KEY_FRAMERATE, PREFERENCE_DATA_DEFAULT);
    }

    public static int getPreferenceBitrate(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(Constants.KEY_BITRATE, PREFERENCE_DATA_DEFAULT);
    }

    public static void setPreferenceResolution(Context context, int value)
    {
        SharedPreferences prefs = context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(Constants.KEY_RESOLUTION, value);
        editor.commit();
    }

    public static void setPreferenceFramerate(Context context, int value)
    {
        SharedPreferences prefs = context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(Constants.KEY_FRAMERATE, value);
        editor.commit();
    }

    public static void setPreferenceBitrate(Context context, int value)
    {
        SharedPreferences prefs = context.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(Constants.KEY_BITRATE, value);
        editor.commit();
    }
}
