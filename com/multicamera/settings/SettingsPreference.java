/**
 * This program is free software: 
 * Added by park Sung-ho
 */
package com.multicamera.settings;

import com.multicamera.R;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;

public class SettingsPreference extends PreferenceActivity implements OnPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setCameraPreferencesFromResource();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        loadPreferenceData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onPreferenceChange(Preference pref, Object value) {
        String key = pref.getKey();

        if (Constants.PREFERENCE_RESOULTION_KEY.equals(key)) {
            setResolution(value);
        } else if (Constants.PREFERENCE_FRAMERATE_KEY.equals(key)) {
            setFramerate(value);
        } else if (Constants.PREFERENCE_BITRATE_KEY.equals(key)) {
            setBitrate(value);
        }

        return false;
    }

    @SuppressWarnings("deprecation")
    protected void setCameraPreferencesFromResource() {
        addPreferencesFromResource(R.xml.multicamera_settings);

        ListPreference mResolution = (ListPreference) findPreference(Constants.PREFERENCE_RESOULTION_KEY);
        mResolution.setOnPreferenceChangeListener(this);

        ListPreference mFramerate = (ListPreference) findPreference(Constants.PREFERENCE_FRAMERATE_KEY);
        mFramerate.setOnPreferenceChangeListener(this);

        ListPreference mBitrate = (ListPreference) findPreference(Constants.PREFERENCE_BITRATE_KEY);
        mBitrate.setOnPreferenceChangeListener(this);
    }

    private void setResolution(Object value) {
        ListPreference mResolution = (ListPreference) findPreference(Constants.PREFERENCE_RESOULTION_KEY);
        mResolution.setTitle(mResolution.getEntries()[Integer.parseInt((String) value)]);
        mResolution.setValueIndex(Integer.parseInt((String) value));
        PreferenceUtils.setPreferenceResolution(this, Integer.parseInt((String) value));
    }

    private void setFramerate(Object value) {
        ListPreference mFramerate = (ListPreference) findPreference(Constants.PREFERENCE_FRAMERATE_KEY);
        mFramerate.setTitle(mFramerate.getEntries()[Integer.parseInt((String) value)]);
        mFramerate.setValueIndex(Integer.parseInt((String) value));
        PreferenceUtils.setPreferenceFramerate(this, Integer.parseInt((String) value));
    }

    private void setBitrate(Object value) {
        ListPreference mBitrate = (ListPreference) findPreference(Constants.PREFERENCE_BITRATE_KEY);
        mBitrate.setTitle(mBitrate.getEntries()[Integer.parseInt((String) value)]);
        mBitrate.setValueIndex(Integer.parseInt((String) value));
        PreferenceUtils.setPreferenceBitrate(this, Integer.parseInt((String) value));
    }

    private void loadPreferenceData() {
        int resolution, framerate, bitrate;

        ListPreference mResolution = (ListPreference) findPreference(Constants.PREFERENCE_RESOULTION_KEY);
        resolution = PreferenceUtils.getPreferenceResolution(this);
        mResolution.setValueIndex(resolution);
        mResolution.setTitle(mResolution.getEntries()[resolution]);

        ListPreference mFramerate = (ListPreference) findPreference(Constants.PREFERENCE_FRAMERATE_KEY);
        framerate = PreferenceUtils.getPreferenceFramerate(this);
        mFramerate.setValueIndex(framerate);
        mFramerate.setTitle(mFramerate.getEntries()[framerate]);

        ListPreference mBitrate = (ListPreference) findPreference(Constants.PREFERENCE_BITRATE_KEY);
        bitrate = PreferenceUtils.getPreferenceBitrate(this);
        mBitrate.setValueIndex(bitrate);
        mBitrate.setTitle(mBitrate.getEntries()[bitrate]);
    }
}
