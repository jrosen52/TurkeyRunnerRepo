package com.example.turkeyrunner;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

public class Game
{
    private enum GameState {
        START, PAUSED, RUNNING, LOST
    }

    private Context context;
    private SurfaceHolder holder;
    private Rect screen;
    private Resources resources;
    private GameState state = GameState.START;

    Paint borderPaint = new Paint();
    BitmapFactory.Options options;

    public Game(Context context, Rect screen, SurfaceHolder holder, Resources resources) {
        this.context = context;
        this.screen = screen;
        this.holder = holder;
        this.resources = resources;
        options = new BitmapFactory.Options();
        options.inScaled = false;
    }

    public void onTouchEvent(MotionEvent event) {
        if (state == GameState.RUNNING) {
            //player.jump();
        } else if(state == GameState.LOST){
            //restartGame();
        } else if(state == GameState.START) {
            state = GameState.RUNNING;
        } else if(state == GameState.PAUSED) {
            state = GameState.RUNNING;
        }
    }

    public void draw() {
        Canvas canvas = holder.lockCanvas();
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            switch (state) {
                case RUNNING:
                    //drawGame(canvas);
                    break;
                case LOST:
                    //drawGame(canvas);
                    break;
                case START:
                    //drawGame(canvas);
                    break;
            }
            holder.unlockCanvasAndPost(canvas);
        }
    }
}
