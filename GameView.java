package com.example.henry.flappybird;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;
    public static int gapHeight = 200;
    private pipeSprite pipe1;
    private pipeSprite pipe2;
    private pipeSprite pipe3;
    private groundSprite ground;
    private backgroundSprite background;
    private CharacterSprite characterSprite;
    private int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    private int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;

    public static double startTime;
    public static int Update_speed = 25;
    public static double timeDiff;
    public static double touchDiff;

    private boolean updateCharacter;

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        makeLevel();

        thread.setRunning(true);
        thread.start();
        startTime = System.currentTimeMillis();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;

        while(retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        updateCharacter = true;
        touchDiff = System.currentTimeMillis();
        return super.onTouchEvent(event);
    }

    public void makeLevel() {
        background = new backgroundSprite(getResizedBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.background),screenWidth,screenHeight));
        characterSprite = new CharacterSprite(getResizedBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.flappybird), 75, 60));
        ground = new groundSprite(getResizedBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.ground),screenWidth,screenHeight *5/16));

        Bitmap bmp;
        Bitmap bmp2;
        int y = Resources.getSystem().getDisplayMetrics().heightPixels;
        int x = 150;
        bmp = getResizedBitmap(BitmapFactory.decodeResource (getResources(), R.drawable.pipe_down), x, y);
        bmp2 = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pipe_up), x, y);

        Random rand = new Random();
        pipe1 = new pipeSprite(bmp, bmp2, 1000, rand.nextInt(-600 + 1 +800)-800);
        pipe2 = new pipeSprite(bmp, bmp2, 1500, -800);
        pipe3 = new pipeSprite(bmp, bmp2, 2000, -700);
    }

    public void update() {
        timeDiff = System.currentTimeMillis() - startTime;
        if (timeDiff >= Update_speed ) {
            logic();
            pipe1.update();
            pipe2.update();
            pipe3.update();
            characterSprite.update(updateCharacter);
            updateCharacter = false;

            startTime = System.currentTimeMillis();
        }
    }

    @Override
    public void draw(Canvas canvas) {

        super.draw(canvas);
        if(canvas!=null) {
            background.draw(canvas);
            pipe1.draw(canvas);
            pipe2.draw(canvas);
            pipe3.draw(canvas);
            ground.draw(canvas);
            characterSprite.draw(canvas);

        }
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    public void logic() {
        ArrayList<pipeSprite> pipes = new ArrayList<>();
        pipes.add(pipe1);
        pipes.add(pipe2);
        pipes.add(pipe3);
        int spacing = 500;

        for (int i = 0; i < pipes.size(); i++) {
            if (pipes.get(i).xX + 200 < 0) {
                Random r = new Random();
                int value2 = r.nextInt(-500 + 1 +900 ) - 900;
                pipes.get(i).xX = spacing * 3;
                pipes.get(i).yY = value2;
            }
        }
    }

    public void gameOver() {
        Bitmap game_overBmp;
        int y = Resources.getSystem().getDisplayMetrics().heightPixels / 8;
        int x = Resources.getSystem().getDisplayMetrics().widthPixels *2/3;

        game_overBmp = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.game_over), x, y);
    }

    public void resetLevel() {
        characterSprite.y = screenHeight / 2;
        pipe1.xX = 0;
        pipe2.xX = 450;
        pipe3.xX = 900;
        pipe1.yY = -100;
        pipe2.yY = 0;
        pipe3.yY = 200;
    }
}
