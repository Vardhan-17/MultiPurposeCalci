package com.example.multipurposecalci;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void calci(View view){
        Intent scalci=new Intent(this,ScientificCalci.class);
        startActivity(scalci);
    }

    public void agecalci(View view){
        Intent acalci=new Intent(this,AgeCalci.class);
        startActivity(acalci);
    }

    public void bmicalci(View view){
        Intent acalci=new Intent(this,BMICalci.class);
        startActivity(acalci);
    }
}