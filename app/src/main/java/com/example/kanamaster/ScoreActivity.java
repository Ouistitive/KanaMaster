package com.example.kanamaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import jdbc.UtilisateurBD;
import kanamaster.kana.TypeKana;

public class ScoreActivity extends AppCompatActivity {

    private TextView texteScore, texteAncienScore, texteCommentaire;
    private Button rejouer;
    private String nomU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Bundle extras = getIntent().getExtras();
        int score = extras.getInt("score");
        nomU = extras.getString("nomU");
        TypeKana type = (TypeKana) extras.get("type");
        int ancienScore = UtilisateurBD.recupererMeilleurScore(nomU, type);

        texteScore = (TextView) findViewById(R.id.texteScore);
        texteAncienScore = (TextView) findViewById(R.id.texteMeilleurScore);
        texteCommentaire = (TextView) findViewById(R.id.texteCommentaire);
        rejouer = (Button) findViewById(R.id.boutonRejouer);

        changerCouleurTexte("Votre score : " + score, texteScore, 14);
        changerCouleurTexte("Votre ancien score : " + ancienScore, texteAncienScore, 21);

        if(ancienScore < score) {
            texteCommentaire.setText(R.string.commentaire_meilleur_score);
            UtilisateurBD.insererNouveauRecord(nomU, score, type);
        }
        else {
            texteCommentaire.setText(R.string.commentaire_pas_meilleure_score);
        }

        rejouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
                intent.putExtra("nomU", nomU);
                startActivity(intent);
                finish();
            }
        });
    }

    private void changerCouleurTexte(String texte, TextView tv, int deb) {
        SpannableString ss = new SpannableString(texte);
        SpannableStringBuilder ssb = new SpannableStringBuilder(texte);
        ForegroundColorSpan fcsRed = new ForegroundColorSpan(getColor(R.color.couleur_principale_rouge));
        ssb.setSpan(fcsRed, deb, texte.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(ssb);
    }
}