package com.multicamera.ui;

import com.multicamera.R;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private static final int RECORD_TEXT_X = 10;
    private static final int RECORD_TEXT_Y_MARGIN = 20;

    private Paint paint;

    public CameraSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setTextSize(25);

        setWillNotDraw(false);
    }

    @Override
    public void draw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.draw(canvas);

        canvas.drawText(this.getResources().getString(R.string.text_recording), RECORD_TEXT_X,
                this.getHeight() - RECORD_TEXT_Y_MARGIN, paint);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO Auto-generated method stub
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    }
}
