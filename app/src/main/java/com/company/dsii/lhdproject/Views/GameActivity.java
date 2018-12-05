package com.company.dsii.lhdproject.Views;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;

import com.company.dsii.lhdproject.Handling.GameplayBehavior;
import com.company.dsii.lhdproject.R;

public class GameActivity extends Activity {

    private GameplayBehavior gameBackground;
    private final int FPS = 30;
    private Handler timer;
    private MediaPlayer player;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.gameBackground = new GameplayBehavior(getApplicationContext(),150, 250);
        timer = new Handler();
        timer.postDelayed(gameThread,2000);
        setContentView(gameBackground);
        player = MediaPlayer.create(this, R.raw.magicnes);
        player.setLooping(true);
        player.start();
    }

    private Runnable gameThread = new Runnable() {
        @Override
        public void run() {
            gameBackground.letItRain();
            gameBackground.invalidate();
            timer.postDelayed(gameThread,1000/FPS);
            Log.d("punt:",""+gameBackground.getPoints());
        }
    };

}
