package com.example.henry.flappybird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.MotionEvent;

public class CharacterSprite {
    private Bitmap image;
    private int xVelocity = 10;
    public int yVelocity = 0;
    private int Update_speed = 20;
    private int jump_speed = 10;
    private int x_movement_diff = 10; // pixels
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    public int x = screenWidth * 3/10, y = screenHeight *5/12;


    public CharacterSprite (Bitmap bmp) {
        image = bmp;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x - image.getWidth()/2, y - image.getHeight() , null);
    }

    public void update() {
        y += yVelocity * Update_speed;
        yVelocity += 9.81 * Update_speed;
    }


}