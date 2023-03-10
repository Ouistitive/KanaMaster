package com.example.kanamaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import jeu.kana.TypeKana;

public class MainActivity extends AppCompatActivity {

    private Button boutonHiragana, boutonKatakana, boutonDeux, boutonClassement; // Les Buttons pour le choix du type de kanas a deviner
    private String nomUtilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras();
        nomUtilisateur = extras.getString("nomU");

        boutonHiragana = (Button) findViewById(R.id.boutonHiragana);
        boutonKatakana = (Button) findViewById(R.id.boutonKatakana);
        boutonDeux = (Button) findViewById(R.id.boutonDeux);
        boutonClassement = (Button) findViewById(R.id.boutonClassement);

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

        boutonClassement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changerActiviteClassement();
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
        intent.putExtra("nomU", nomUtilisateur);
        startActivity(intent);
        finish();
    }

    /**
     * @brief Change d'activite pour afficher le classement
     */
    private void changerActiviteClassement() {
        Intent intent = new Intent(MainActivity.this, ClassementActivity.class);
        intent.putExtra("nomU", nomUtilisateur);
        startActivity(intent);
    }
}