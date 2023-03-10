package com.example.kanamaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import jdbc.ConnexionBD;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new ConnexionBD().connexionBD();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}