package com.company.dsii.lhdproject.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.company.dsii.lhdproject.R;

public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button helpButton;
    private Button scoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.startButton = findViewById(R.id.startB);
        this.helpButton = findViewById(R.id.helpB);
        this.scoreButton = findViewById(R.id.scoreB);
    }

    public void onStartClick(View view){
        iniPlayActivity();
    }

    public void onHelpClick(View view) { iniHelpActivity();}

    public void onScoreClick(View view) { iniScoreActivity();}

    public void iniPlayActivity(){
        Intent startGameIntent = new Intent(this,GameActivity.class);
        startActivity(startGameIntent);
    }

    public void iniHelpActivity(){
        Intent helpIntent = new Intent(this, HelpActivity.class);
        startActivity(helpIntent);
    }

    public void iniScoreActivity(){
        Intent scoreIntent = new Intent(this, ScoreActivity.class);
        startActivity(scoreIntent);
    }
}

