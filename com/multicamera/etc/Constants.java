/**
 * This program is free software: 
 * Added by park Sung-ho
 */
package com.multicamera.etc;

public class Constants {
    public static final boolean DEBUG = true;

    // Preference Constants
    public static final String PREFERENCE_RESOULTION_KEY = "resolution_key";
    public static final String PREFERENCE_FRAMERATE_KEY = "framerate_key";
    public static final String PREFERENCE_BITRATE_KEY = "bitrate_key";

    public static final String SHARED_PREF_NAME = "Settings";
    public static final String KEY_RESOLUTION = "resolution";
    public static final String KEY_FRAMERATE = "framerate";
    public static final String KEY_BITRATE = "bitrate";

    public static final String RECORDING_PATH = "/MultiCamera/";

    // Recording Service Constants
    public final static String RECORD_MEDIA_3GP_MIME_TYPE = "video/3gpp";
    public final static String RECORD_MEDIA_MP4_MIME_TYPE = "video/mp4";

    public final static String RECORD_MEDIA_TYPE = "mp4";

    public static final String RECORD_SERVICE_NAME = "com.multicamera.service.FacingBackRecorder";
    public static final String RECORD_FRONT_SERVICE_NAME = "com.multicamera.service.FacingFrontRecorder";
}
