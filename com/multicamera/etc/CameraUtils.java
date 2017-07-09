/**
 * This program is free software: 
 * Added by park Sung-ho
 */
package com.multicamera.etc;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

public class CameraUtils {
    private static final String TAG = "MultiCameraUtils";

    public static String getModelName() {
        return android.os.Build.MODEL;
    }

    public static String getVendorName() {
        return android.os.Build.MANUFACTURER;
    }

    public static String getProductName() {
        return android.os.Build.PRODUCT;
    }

    public static boolean isServiceRunning(Context context, String service_name) {
        boolean isRunning = false;

        if (context == null) {
            return false;
        }

        ActivityManager manager = (ActivityManager) context.getSystemService(Activity.ACTIVITY_SERVICE);

        for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (service_name.equals(service.service.getClassName())) {
                isRunning = true;
                break;
            }
        }

        if (isRunning) {
            Log.d(TAG, "The " + service_name + " service is ServiceRunning()...");
        }

        return isRunning;
    }

    public static int getDp2Pixel(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int pixel = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        if (Constants.DEBUG) Log.i(TAG, "getDp2Px pixel = "+pixel);
        return pixel;
    }

    public static int getPixel2Dp(Context context, int pixel) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(pixel / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        if (Constants.DEBUG) Log.i(TAG, "getPx2Dp dp = "+dp);
        return dp;
    }

}
