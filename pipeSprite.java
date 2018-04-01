package com.example.henry.flappybird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class pipeSprite {

    private Bitmap image;
    private Bitmap image2;
    public int xX, yY;
    private int xVelocity = 10;
    private int x_movement_diff = 10;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public pipeSprite (Bitmap bmp, Bitmap bmp2, int x, int y) {
        image = bmp;
        image2 = bmp2;
        yY = y;
        xX = x;
    }

    public void draw(Canvas canvas) {
        int ypos = -(GameView.gapHeight / 2) + yY;
        canvas.drawBitmap(image, xX, ypos, null);
        canvas.drawBitmap(image2,xX, yY + screenHeight + GameView.gapHeight/2, null);
    }

    public void update() {

        xX -= x_movement_diff;
    }
}
