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

    private Player player;
    private Background highway;
    private Background skyline_close;
    private Background skyline_mid;
    private Background skyline_far;
    private Enemy van;

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

    public void update(Long elapsed) {
        if(state == GameState.RUNNING){
            // Do stuff
            player.update(elapsed);
            highway.update(elapsed);
            skyline_close.update(elapsed);
            skyline_mid.update(elapsed);
            skyline_far.update(elapsed);
            van.update(elapsed);

            if(van.isOffScreen()) van = new Enemy(BitmapFactory.decodeResource(resources, R.drawable.van, options),
                    context, Enemy.generate(screen), screen, screen.height() - screen.width() / 10);

            if(Rect.intersects(van.getHitbox(), player.getHitbox())) loseGame();
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

    private void drawGame(Canvas canvas) {
        //Log.d("GAME_DRAWGAME", "Trying to draw everything in the game!");
        //canvas.drawRect(screen, borderPaint);
        skyline_far.draw(canvas);
        skyline_mid.draw(canvas);
        skyline_close.draw(canvas);
        highway.draw(canvas);
        van.draw(canvas, 0);
        player.draw(canvas, 0);
    }

    private void loseGame() {
        state = GameState.LOST;
    }

    private void restartGame() {
        player = new Player(null, context, new Rect(
                400,
                screen.height()/2,
                520,
                screen.height()/2 + 220),
                screen);

        highway = new Background( BitmapFactory.decodeResource(resources, R.drawable.highway, options),
                context, new Rect( 0, screen.height() - screen.width() / 10, screen.width(), screen.height()), screen, 12);

        skyline_close = new Background(BitmapFactory.decodeResource(resources, R.drawable.skyline_close, options),
                context, new Rect( 0, screen.height() / 2, screen.height() * 3, screen.height()), screen, 8);

        skyline_mid = new Background(BitmapFactory.decodeResource(resources, R.drawable.skyline_mid, options),
                context, new Rect( 0, screen.height() / 4, screen.height() * 3, screen.height()), screen, 4);

        skyline_far = new Background(BitmapFactory.decodeResource(resources, R.drawable.skyline_far, options),
                context, new Rect( 0, screen.height() / 4, screen.height() * 3, screen.height()), screen, 2);

        van = new Enemy(BitmapFactory.decodeResource(resources, R.drawable.van, options),
                context, Enemy.generate(screen), screen, screen.height() - screen.width() / 10);

        borderPaint.setStrokeWidth(24);
        borderPaint.setColor(Color.GREEN);
        borderPaint.setStyle(Paint.Style.STROKE);
        state = GameState.RUNNING;
    }
}
