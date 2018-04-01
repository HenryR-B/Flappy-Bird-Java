package com.example.henry.flappybird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class groundSprite {
    private Bitmap image;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;

    public groundSprite (Bitmap bmp) {
        image = bmp;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image,0, screenHeight *3/4, null);
    }
}
