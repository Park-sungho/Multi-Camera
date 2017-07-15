/**
 * Added by park Sung-ho
 */
package com.multicamera.main;

import com.multicamera.R;
import com.multicamera.etc.CameraUtils;
import com.multicamera.etc.Constants;
import com.multicamera.service.FacingBackCamService;
import com.multicamera.service.FacingFrontCamService;
import com.multicamera.settings.SettingsPreference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MultiCameraMain extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        setContentView(R.layout.multi_camera_main_layout);

        initCameralayout();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.camera_menu, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id) {
        case R.id.settings:
            Intent intent = new Intent(this, SettingsPreference.class);
            startActivity(intent);
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
        case R.id.button_facing_back_record_start:
            if (CameraUtils.isServiceRunning(this, Constants.RECORD_SERVICE_NAME) == false) {
                Intent intent = new Intent(this, FacingBackCamService.class);
                startService(intent);
            }
            break;

        case R.id.button_facing_front_record_start:
            if (CameraUtils.isServiceRunning(this, Constants.RECORD_FRONT_SERVICE_NAME) == false) {
                Intent intent = new Intent(this, FacingFrontCamService.class);
                startService(intent);
            }
            break;

        case R.id.button_facing_back_record_stop:
            stopService(new Intent(this, FacingBackCamService.class));
            break;

        case R.id.button_facing_front_record_stop:
            stopService(new Intent(this, FacingFrontCamService.class));
            break;

        default:
            break;

        }
    }

    private void initCameralayout() {
        Button mButtonBGRearRecord = (Button) findViewById(R.id.button_facing_back_record_start);
        mButtonBGRearRecord.setOnClickListener(this);
        Button mButtonBGFrontRecord = (Button) findViewById(R.id.button_facing_front_record_start);
        mButtonBGFrontRecord.setOnClickListener(this);

        Button mButtonBGRearStop = (Button) findViewById(R.id.button_facing_back_record_stop);
        mButtonBGRearStop.setOnClickListener(this);
        Button mButtonBGFrontStop = (Button) findViewById(R.id.button_facing_front_record_stop);
        mButtonBGFrontStop.setOnClickListener(this);
    }
}
