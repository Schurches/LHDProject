package com.company.dsii.lhdproject.Views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.company.dsii.lhdproject.R;

public class HelpActivity extends AppCompatActivity {

    private Button volverButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        this.volverButton = findViewById(R.id.volverB);
    }

    public void onVolverClick(View view){iniVolver();}

    public void iniVolver(){
        Intent volver = new Intent(this,MainActivity.class);
        startActivity(volver);
    }
}
