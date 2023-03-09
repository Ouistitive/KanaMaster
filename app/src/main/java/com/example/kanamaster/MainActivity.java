package com.example.kanamaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import jdbc.Connexion;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new Connexion().connexionBD();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}