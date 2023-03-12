package com.example.kanamaster;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import jeu.BuilderKana;
import jeu.kana.Kana;
import jeu.kana.TypeKana;

public class JeuActivity extends AppCompatActivity {

    private TypeKana type; // Type de kana du jeu
    private int indice; // indice du kana a trouver
    private List<Kana> kanas; // Liste des kanas
    private List<Button> choix; // Liste des boutons de choix
    private Random random; // La graine aleatoire du jeu
    private String strBouton; // Chaine de caracteres parametree pour le texte dans les boutons


    private int compteur; // Le compteur pour le chronometre
    private TextView texteChrono, texteScore; // TextView pour le chronometre du jeu
    private final static int TEMPS_CHRONO = 10; // 180 secondes = 3 minutes
    private final static int MILLISECONDE = 1000;
    private final static int MINUTE = 60;

    private int scoreJoueur;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        // On initialise la graine et la chaine
        random = new Random();
        strBouton = "%s (%s)";

        texteScore = (TextView) findViewById(R.id.score);
        scoreJoueur = 0;
        String text = "Score : " + scoreJoueur;
        SpannableString ss = new SpannableString(text);
        SpannableStringBuilder ssb = new SpannableStringBuilder(text);
        ForegroundColorSpan fcsRed = new ForegroundColorSpan(getColor(R.color.couleur_principale_rouge));
        ssb.setSpan(fcsRed, 7, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        texteScore.setText(ssb);

        // Stocke les boutons dans une liste
        choix = new ArrayList<>();
        choix.add((Button) findViewById(R.id.bouton_1));
        choix.add((Button) findViewById(R.id.bouton_2));
        choix.add((Button) findViewById(R.id.bouton_3));
        choix.add((Button) findViewById(R.id.bouton_4));

        for(Button b : choix) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(verification(b.getText().toString())) {
                        scoreJoueur++;

                        // Ajoute un au score du joueur sur le TextView et affiche le score en rouge
                        String text = "Score : " + scoreJoueur;

                        SpannableString ss = new SpannableString(text);
                        SpannableStringBuilder ssb = new SpannableStringBuilder(text);
                        ForegroundColorSpan fcsRed = new ForegroundColorSpan(getColor(R.color.couleur_principale_rouge));
                        ssb.setSpan(fcsRed, 7, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        texteScore.setText(ssb);
                    }
                    else
                        System.out.println("C PAS GOOD");
                    indice++;

                    modifierBoutons();

                }
            });
        }

        // On recupere le type de kanas que l'utilisateur devra deviner a partir de l'activite precedente
        Bundle extras = getIntent().getExtras();
        type = (TypeKana) extras.get("type");
        kanas = BuilderKana.prepareKana(type);
        Collections.shuffle(kanas);

        texteChrono = (TextView) findViewById(R.id.chronometre);
        compteur = TEMPS_CHRONO;

        lancerChronometre();

        modifierBoutons();
    }

    /**
     * @brief Modifie les boutons et change le kana a trouver
     */
    private void modifierBoutons() {
        Collections.shuffle(choix);

        if(indice >= kanas.size()) {
            reinitListe();
        }

        // String.format permet de changer les %s par ce qu'il y a en parametres (comme les printf en C)
        choix.get(0).setText(String.format(strBouton, kanas.get(indice).getPrononciation(), kanas.get(indice).getType()));

        System.out.println(String.format(strBouton, kanas.get(indice).getPrononciation(), kanas.get(indice).getType()));
        for(int i = 1; i < choix.size(); i++) {
            int nb = randomAvecExclusion(random, 0, kanas.size()-1, indice);
            choix.get(i).setText(String.format(strBouton, kanas.get(nb).getPrononciation(), kanas.get(nb).getType()));
        }
    }

    private void reinitListe() {
        indice = 0;
        Collections.shuffle(kanas);
    }

    /**
     * @brief Verifie si le kana est egal au bouton clique
     * @param texte : le texte dans le bouton
     * @return true : le bouton clique est la bonne reponse
     * @return false : le bouton clique est faux
     */
    private boolean verification(String texte) {
        return String.format(strBouton, kanas.get(indice).getPrononciation(), kanas.get(indice).getType()).equals(texte);
    }

    /**
     * @brief Tire un nombre aleatoire a partir d'un debut et d'une fin avec un numero exclu
     * @param r : La graine aleatoire
     * @param deb : la valeur minimale
     * @param fin : la valeur maximale
     * @param exclu : la valeur interdite
     * @return un entier aleatoire different de exclu
     */
    private int randomAvecExclusion(Random r, int deb, int fin, int exclu) {
        int random = deb + r.nextInt(fin - deb + 1 - 1);
        if (random >= exclu)
            random++;
        return random;
    }

    /**
     * @brief Lance le chronometre et l'affiche dans le TextView
     */
    private void lancerChronometre() {
        new CountDownTimer(TEMPS_CHRONO * MILLISECONDE, MILLISECONDE) {
            public void onTick(long millisUntilFinished) {
                int tmp = String.valueOf(compteur % MINUTE).length();
                // Condition ternaire pour concatener un 0 si il y a un chiffre
                texteChrono.setText((compteur / MINUTE) + ":" + (tmp == 1 ? "0" : "") + (compteur % MINUTE));
                compteur--;
            }
            public void onFinish() {
                AlertDialog.Builder builder = new AlertDialog.Builder(JeuActivity.this);

                builder.setMessage("Quitter le jeu");
                builder.setTitle("Temps écoulé !");
                builder.setCancelable(false);

                builder.setPositiveButton("Ok", (DialogInterface.OnClickListener) (dialog, which) -> {
                    changerActivite();
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }

        }.start();
    }

    /**
     * @brief Envoie à la prochaine activite pour afficher le score du joueur
     */
    private void changerActivite() {
        Bundle extras = getIntent().getExtras();
        String nomUtilisateur = extras.getString("nomU");

        Intent intent = new Intent(JeuActivity.this, ScoreActivity.class);
        intent.putExtra("score", scoreJoueur);
        intent.putExtra("nomU", nomUtilisateur);
        intent.putExtra("type", type);
        startActivity(intent);
        finish();
    }
}