package com.example.turkeyrunner;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback
{
    SurfaceHolder holder;


    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        holder = getHolder();
        holder.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("GAMEVIEW", "created");

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.d("GAMEVIEW", "changed");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d("GAMEVIEW", "destroyed");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
