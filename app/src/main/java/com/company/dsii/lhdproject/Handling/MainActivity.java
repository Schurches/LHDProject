package com.company.dsii.lhdproject.Handling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.company.dsii.lhdproject.R;

public class MainActivity extends AppCompatActivity {

    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.startButton = findViewById(R.id.startB);
    }

    public void onStartClick(View view){
        iniPlayActivity();
    }

    public void iniPlayActivity(){
        Intent startGameIntent = new Intent(this,PlayActivity.class);
        startActivity(startGameIntent);
    }
}
