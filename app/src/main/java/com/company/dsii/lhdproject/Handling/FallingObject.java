package com.company.dsii.lhdproject.Handling;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;

import com.company.dsii.lhdproject.R;

import java.util.Random;

/**
 * Created by Juan on 23/02/2017.
 */

public class FallingObject extends android.support.v7.widget.AppCompatImageView {

    private int fallAccel;
    private Bitmap fallingObject;
    private int objSize, objX, objY, fallSpdX, fallSpdY, initialY, initialX;
    private int heightMultiplier;
    private int dispHeight, dispWidth;
    boolean hasTouchedTheGround, hasReachedPlayer, mustMove=true;
    private Random Rand = new Random();
    private int collected = 0;

    public FallingObject(Context context, int objectSize, int width, int height, int ballX, int ballY, int ballID){
        super(context);
        BitmapDrawable fallingObject = (BitmapDrawable)ContextCompat.getDrawable(context, R.drawable.copo1);
        this.fallingObject = fallingObject.getBitmap();
        this.objSize = objectSize;
        this.fallingObject =Bitmap.createScaledBitmap(this.fallingObject, objSize, objSize, false);
        this.dispWidth = width;
        this.dispHeight = height;
        this.initialX = ballX;
        this.initialY = ballY;
        this.objY = initialY;
        this.objX = initialX;
        this.fallSpdY = 10;
        this.fallAccel = 1;//nivel_dificultad*(maneja_coord.y/400);
        this.heightMultiplier = ballID;
    }

    public boolean hasCollidedWithSomething(int ballX, int ballY, int playerX,int playerY, int playerSize){
        int horizontalD = ballX-playerX;
        if(horizontalD < 0){
            horizontalD=horizontalD*-1;
        }
        int verticalD = ballY-playerY;
        if(verticalD < 0){
            verticalD=verticalD*-1;
        }
        if(ballY >= dispHeight){
            onGroundTouch();
            return true;
        }else if((verticalD<50 && verticalD >= 0) && (horizontalD < 100 & horizontalD >= 0)){
            onPlayerTouch();
            return true;
        }
        return false;
    }

    public void onGroundTouch(){
        collected--;
        this.objY = initialY-Rand.nextInt(800-400)-400;
        Log.d("Fail","Ball #"+heightMultiplier+" has height "+objY);
        this.objX = Rand.nextInt((dispWidth-objSize-25)-50)+50;
    }

    public void onPlayerTouch(){
        collected++;
        this.objY = initialY-Rand.nextInt(800-400)-400;
        Log.d("Win","Ball #"+heightMultiplier+" has height "+objY);
        this.objX = Rand.nextInt((dispWidth-objSize-25)-50)+50;
    }

    public boolean mustBeMoved(){
        return this.mustMove;
    }

    public void changeMoveState(){
        this.mustMove = !this.mustMove;
    }

    public void objectMovement(int playerX, int playerY, int playerSize){
        objY += fallSpdY;
        hasCollidedWithSomething(objX,objY,playerX,playerY,playerSize);
    }

    public int getPoints(){
        return this.collected;
    }

    public int getObjX(){
        return this.objX;
    }

    public int getObjY(){
        return this.objY;
    }

    public Bitmap getBitmapObject(){
        return this.fallingObject;
    }

    protected void onDraw(Canvas lienzo){
        lienzo.drawBitmap(fallingObject, objX, objY, null);
    }
}




