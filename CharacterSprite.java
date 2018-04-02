package com.example.henry.flappybird;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import java.lang.Math;

public class CharacterSprite {
    private Bitmap image;
    public double yVelocity = 0;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    public  int x = screenWidth * 3/10, y = screenHeight *5/12;
    private double jump_height = screenHeight/12.0;
    private double prevVelocity = 0;
    private float yPos;


    public CharacterSprite (Bitmap bmp) {
        image = bmp;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x - (image.getWidth() / 2), y - image.getHeight(), null);
    }

    public void update(boolean updateCharacter) {

        if (updateCharacter) {
            prevVelocity = -1 * Math.sqrt(2*7.63 * jump_height/15);
        }
        yVelocity = prevVelocity + 19.6 * GameView.Update_speed / 1000;
        System.out.println("Velocity: " + yVelocity);
        //System.out.println("Previous Velocity: " + prevVelocity);
        y += (float) yVelocity;
        System.out.println("y pos: " + y);

        prevVelocity = yVelocity;

    }


}