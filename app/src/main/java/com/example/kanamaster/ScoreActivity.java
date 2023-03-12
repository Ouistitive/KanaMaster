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
import jeu.kana.TypeKana;

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
        int ancienScore = UtilisateurBD.recupererMeilleurScore(nomU);

        texteScore = (TextView) findViewById(R.id.texteScore);
        texteAncienScore = (TextView) findViewById(R.id.texteMeilleurScore);
        texteCommentaire = (TextView) findViewById(R.id.texteCommentaire);
        rejouer = (Button) findViewById(R.id.boutonRejouer);

        String strScore = "Votre score : " + score;
        SpannableString ss = new SpannableString(strScore);
        SpannableStringBuilder ssb = new SpannableStringBuilder(strScore);
        ForegroundColorSpan fcsRed = new ForegroundColorSpan(getColor(R.color.couleur_principale_rouge));
        ssb.setSpan(fcsRed, 14, strScore.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        texteScore.setText(ssb);

        String strAncienScore = "Votre ancien score : " + ancienScore;
        ss = new SpannableString(strAncienScore);
        ssb = new SpannableStringBuilder(strAncienScore);
        fcsRed = new ForegroundColorSpan(getColor(R.color.couleur_principale_rouge));
        ssb.setSpan(fcsRed, 21, strAncienScore.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        texteAncienScore.setText(ssb);

        if(ancienScore < score) {
            texteCommentaire.setText(R.string.commentaire_meilleur_score);
            UtilisateurBD.insererNouveauRecord(nomU, score);
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
}