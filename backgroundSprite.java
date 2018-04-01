package com.example.henry.flappybird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class backgroundSprite {
    private Bitmap image;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public backgroundSprite (Bitmap bmp) {
        image = bmp;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image,0, -screenHeight /8, null);
    }
}
