package com.company.dsii.lhdproject.Handling;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;

import com.company.dsii.lhdproject.R;

import java.util.ArrayList;
import java.util.Random;


public class GameplayBehavior extends View {

    Bitmap background;
    private int dispWidth, dispHeight;
    private boolean hasGameFinished;
    private ArrayList<FallingObject> fallingObjects = new ArrayList<>();
    private PlayerObject playerObject;
    private int puntaje = 0;

    public GameplayBehavior(Context context, int objectSize, int playerSize){
        super(context);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display displayInfo = windowManager.getDefaultDisplay();
        Point point=new Point();
        displayInfo.getSize(point);
        this.dispWidth = point.x;
        this.dispHeight = point.y;
        BitmapDrawable backgroundImg = (BitmapDrawable) ContextCompat.getDrawable(context, R.drawable.fondo2);
        this.background=backgroundImg.getBitmap();
        this.background = Bitmap.createScaledBitmap(background,dispWidth,dispHeight,false);
        iniSnowBalls(context,objectSize);
        iniPlayer(context,playerSize);
    }


    final Handler handler = new Handler();
    Runnable mLongPressed = new Runnable() {
        public void run() {

        }
    };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int actionID = event.getAction();
        float x;
        switch(actionID){
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                if(x >= dispWidth/2){
                    onRightClick();
                }else{
                    onLeftClick();
                }
                handler.postDelayed(mLongPressed, ViewConfiguration.getLongPressTimeout());
                break;
        }

        return super.onTouchEvent(event);
    }

    public void iniSnowBalls(Context context, int objectSize){
        Random R = new Random();
        for (int i = 0; i < 15; i++){
            int posX = R.nextInt((dispWidth-objectSize-25)-50)+50;
            int posY = 0-objectSize;
            if(i!=0){
                posY += -500*i;
            }
            Log.d("i="+i+":    ",""+posY);
            fallingObjects.add(new FallingObject(context,objectSize,dispWidth,dispHeight,posX,posY,i));
        }
    }

    public int getPoints(){
        return this.puntaje;
    }

    public void iniPlayer(Context context, int playerSize){
        int playerX = this.dispWidth/2;
        int playerY = this.dispHeight-playerSize-50;
        this.playerObject = new PlayerObject(context,playerSize,playerX,playerY, this.dispWidth, this.dispHeight);
    }

    public void onRightClick(){
        this.playerObject.moveLeft();
    }

    public void onLeftClick(){
        this.playerObject.moveRight();
    }

    public void letItRain(){
        for(int i = 0; i < 15; i++){
            FallingObject currentObject = this.fallingObjects.get(i);
            currentObject.objectMovement(playerObject.getPlayerX(),playerObject.getPlayerY(),playerObject.getPlayerY());
            puntaje+=currentObject.getPoints();
        }

    }

    protected void onDraw(Canvas layer){
        layer.drawBitmap(background, 0,0, null);
        for(int i = 0; i < 15; i++){
            FallingObject objectI = fallingObjects.get(i);
            layer.drawBitmap(objectI.getBitmapObject(),objectI.getObjX(),objectI.getObjY(),null);
        }
        layer.drawBitmap(playerObject.getBitmapObject(),playerObject.getPlayerX(),playerObject.getPlayerY(),null);
    }

}
