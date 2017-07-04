/**
 * This program is free software: 
 * Added by park Sung-ho
 */
package com.multicamera.service;

import java.io.File;
import java.io.IOException;

import com.multicamera.settings.Constants;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.provider.MediaStore.Video;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

public class FacingBackCamService extends Service implements SurfaceHolder.Callback {
    private static final String TAG = "FacingBackCamService";

    private static final int PREVIEW_LAYOUT_WIDTH = 200;
    private static final int PREVIWE_LAYOUT_HEIGHT = 200;
    private static final int RECORDING_WIDTH = 1920;
    private static final int RECORDING_HEIGHT = 1080;

    private static final String RECORDING_PATH = Environment.getExternalStorageDirectory().getPath()
            + Constants.RECORDING_PATH;

    private int mCamera_device = 0;
    private Camera mCamera = null;
    private WindowManager mWindowManager;
    private MediaRecorder mMediaRecorder = null;
    private SurfaceView mSurfaceView;

    private File mFile;
    private String mMediaFullPath;

    private ContentValues mDBContentValue = null;

    public boolean mIsRecording;

    @Override
    public void onCreate() {
        setCameraSurfaceView();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mCamera_device = Camera.CameraInfo.CAMERA_FACING_BACK;
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void surfaceCreated(final SurfaceHolder surfaceHolder) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                openCamera(Camera.CameraInfo.CAMERA_FACING_BACK);
                startRecording(surfaceHolder);
            }
        }).start();

    }

    private void startRecording(SurfaceHolder surfaceHolder) {
        // Sanity Check
        if (mCamera == null) {
            Log.e(TAG, "Camera is not working...");
            return;
        }

        try {
            mMediaRecorder = new MediaRecorder();
            mMediaRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {
                @Override
                public void onError(MediaRecorder mr, int what, int extra) {
                    stopRecording();
                    stopSelf();
                    return;
                }
            });

            mMediaRecorder.setCamera(mCamera);
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
            mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);

            File directory = new File(RECORDING_PATH);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            long currentTime = System.currentTimeMillis();
            if (currentTime < 1) {
                currentTime = (long) Math.random()*10000 + 1; 
            };

            String filename = currentTime + "."+Constants.RECORD_MEDIA_TYPE;
            String fullPath = RECORDING_PATH + filename;
            mFile = new File(directory, fullPath);
            if (mFile.exists()) {
                mFile.delete();
            }
            mMediaRecorder.setOutputFile(fullPath);
            mMediaRecorder.setVideoFrameRate(30);
            mMediaRecorder.setVideoSize(RECORDING_WIDTH, RECORDING_HEIGHT);
            mMediaRecorder.setPreviewDisplay(surfaceHolder.getSurface());

            mMediaRecorder.prepare();
            mMediaRecorder.start();
            mIsRecording = true;

            // Save media info into the database
            mMediaFullPath = fullPath;

            ContentValues values = new ContentValues(5);
            values.put(Video.Media.TITLE, currentTime);
            values.put(Video.Media.DISPLAY_NAME, filename);
            values.put(Video.Media.DATE_TAKEN, System.currentTimeMillis());
            values.put(Video.Media.MIME_TYPE, Constants.RECORD_MEDIA_MP4_MIME_TYPE);
            values.put(Video.Media.DATA, mMediaFullPath);
            mDBContentValue = values;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (mFile != null && mFile.exists()) {
                mFile.delete();
            }
        }
    }

    @Override
    public void onDestroy() {
        stopRecording();
        mWindowManager.removeView(mSurfaceView);

        saveCameraDB();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void setCameraSurfaceView() {
        try {
            mWindowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
            mSurfaceView = new SurfaceView(this);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(
                    PREVIEW_LAYOUT_WIDTH, 
                    PREVIWE_LAYOUT_HEIGHT,
                    WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY,
                    WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                    PixelFormat.TRANSLUCENT);

            layoutParams.gravity = Gravity.TOP;
            layoutParams.gravity |= Gravity.LEFT;
            layoutParams.verticalMargin = 0.1f;
            layoutParams.horizontalMargin = 0.1f;

            mWindowManager.addView(mSurfaceView, layoutParams);
            mSurfaceView.getHolder().addCallback(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopRecording() {
        try {
            mIsRecording = false;
            if (mMediaRecorder != null) {
                mMediaRecorder.stop();
                mMediaRecorder.reset();
                mMediaRecorder.release();
            }

            if (mCamera != null) {
                mCamera.lock();
                mCamera.release();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mMediaRecorder = null;
            mCamera = null;
        }
    }

    private void saveCameraDB() {
        try {
            mDBContentValue.put(Video.Media.SIZE, new File(mMediaFullPath).length());
            getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, mDBContentValue);
            mDBContentValue = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openCamera(int camera_device) {
        if (mCamera != null) {
            Log.e(TAG, "Error, Camera is already working...");
            return;
        }

        try {
            int i;
            boolean isAvailableDevice = false;
            for (i = 0; i < Camera.getNumberOfCameras(); i++) {
                Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
                Camera.getCameraInfo(i, cameraInfo);
                if (cameraInfo.facing == mCamera_device) {
                    isAvailableDevice = true;
                    break;
                }
            }
            if (isAvailableDevice) {
                mCamera = Camera.open(mCamera_device);
            } else {
                Log.e(TAG, "Error, Camera is Not Found...");
                return;
            }

            if (mCamera != null) {
                Camera.Parameters params = mCamera.getParameters();

                Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
                if (display.getRotation() == Surface.ROTATION_0) {
                    mCamera.setDisplayOrientation(90);
                } else if (display.getRotation() == Surface.ROTATION_270) {
                    mCamera.setDisplayOrientation(0);
                }

                // Setting here for camera preview.
                mCamera.setParameters(params);
                mCamera.unlock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
