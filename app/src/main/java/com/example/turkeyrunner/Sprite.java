package com.example.turkeyrunner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Sprite
{
    public enum SpriteState {
        IDLE, JUMP, FLY, LAND
    }
    public Bitmap image;
    public Context context;
    private Rect hitbox;
    public Rect screen;
    private SpriteState spriteState;

    private int width;
    private int height;
    private double x;
    private double y;

    Paint noAliasPaint = new Paint();
    Paint borderPaint = new Paint();
    Paint vectorPaint = new Paint();

    public Sprite(Bitmap image, Context context, Rect hitbox, Rect screen) {
        this.image = image;
        this.context = context;
        this.hitbox = hitbox;
        this.screen = screen;
        spriteState = SpriteState.IDLE;

        this.width = hitbox.width();
        this.height = hitbox.height();
        this.x = hitbox.left;
        this.y = hitbox.top;

    }

    public void draw(Canvas canvas, long elevation) {
        //Log.d("SPRITE", "Drawing sprite at (" + hitbox.left + ", " + hitbox.top + ")");
        if(image != null) {
            // Draw image
            this.setY(this.getY());
            //canvas.drawBitmap(image, null, getHitbox(), null);
        } else {
            drawHitbox(canvas, elevation, Color.MAGENTA);
        }
        //drawVecs(canvas, elevation, 15);
    }

    public void drawHitbox(Canvas canvas, long elevation, int color) {
        borderPaint.setColor(color);
        this.setY(this.getY());// + elevation);
        canvas.drawRect(hitbox, borderPaint);
    }

    public double getX() {return this.x;}

    public double getY() {return this.y;}

    public void setY(double y) {
        this.y = y;
        //this.hitbox.offsetTo((int) this.x, (int) this.y);
    }
}
