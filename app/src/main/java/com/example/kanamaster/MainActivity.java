package com.example.kanamaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import jdbc.ConnexionBD;

public class MainActivity extends AppCompatActivity {

    private Button boutonHiragana, boutonKatakana, boutonDeux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boutonHiragana = (Button) findViewById(R.id.boutonHiragana);
        boutonKatakana = (Button) findViewById(R.id.boutonKatakana);
        boutonDeux = (Button) findViewById(R.id.boutonDeux);

        boutonHiragana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lancerJeu("Hiragana");
            }
        });

        boutonKatakana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lancerJeu("Katakana");
            }
        });

        boutonDeux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lancerJeu("Kana");
            }
        });
    }

    private void lancerJeu(String type) {
        Intent intent = new Intent(MainActivity.this, JeuActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
        finish();
    }
}