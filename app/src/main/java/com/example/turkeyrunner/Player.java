package com.example.turkeyrunner;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;

public class Player extends Sprite
{
    public Player(Bitmap image, Context context, Rect hitbox, Rect screen) {
        super(image, context, hitbox, screen);
        this.affectedByGrav = true;
    }

    public void update(long elapsed) {
        // Check for player hitting bottom
        // TODO: Standardize how floor is calculated
        if(this.getHitbox().bottom >= screen.height() - screen.width() / 10) {
            this.setY(screen.height() - screen.width() / 10 - this.getHeight());
            // Don't let player fall through
            this.vy = 0;
        }
        super.update(elapsed);

        this.ax = this.ay = 0;
    }

    public void jump() {
        //Log.d("PLAYER", "Jump");
        if(Math.abs(this.getBottom() - screen.height() + screen.width() / 10) < 5) this.applyForce(0, -60);
    }

    public void applyForce(double fax, double fay) {
        this.ax = fax;
        this.ay = fay;
    }
}
