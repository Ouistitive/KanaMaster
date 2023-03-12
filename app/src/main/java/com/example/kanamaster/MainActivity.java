package com.example.kanamaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import jdbc.ConnexionBD;
import jeu.TypeKana;

public class MainActivity extends AppCompatActivity {

    private Button boutonHiragana, boutonKatakana, boutonDeux; // Les Buttons pour le choix du type de kanas a deviner

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
                lancerJeu(TypeKana.HIRAGANA);
            }
        });

        boutonKatakana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lancerJeu(TypeKana.KATAKANA);
            }
        });

        boutonDeux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lancerJeu(TypeKana.KANA);
            }
        });
    }

    /**
     * @brief Lance le jeu et envoie le type de kana
     * @param type : Le type de kana a deviner
     */
    private void lancerJeu(TypeKana type) {
        Intent intent = new Intent(MainActivity.this, JeuActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
        finish();
    }
}