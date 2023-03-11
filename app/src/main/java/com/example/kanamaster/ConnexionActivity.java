package com.example.kanamaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.StrictMode;
import android.os.Build;
import android.widget.TextView;

import jdbc.UtilisateurBD;

public class ConnexionActivity extends AppCompatActivity {

    private EditText nomUtilisateur, motDePasse;
    private TextView texteErreur, texteInscrire;
    private Button seConnecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        nomUtilisateur = (EditText) findViewById(R.id.nomUtilisateur);
        motDePasse = (EditText) findViewById(R.id.motDePasse);
        seConnecter = (Button) findViewById(R.id.connecter);
        texteErreur = (TextView) findViewById(R.id.texteErreur);
        texteInscrire = (TextView) findViewById(R.id.inscrire);

        seConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!verification()) {
                    if(nomUtilisateur.getText().toString().equals(""))
                        nomUtilisateur.setHintTextColor(getColor(R.color.couleur_principale_rouge));
                    if(motDePasse.getText().toString().equals(""))
                        motDePasse.setHintTextColor(getColor(R.color.couleur_principale_rouge));
                    texteErreur.setText(R.string.erreur_champ_non_rempli);
                    return;
                }

                if(UtilisateurBD.identification(nomUtilisateur.getText().toString(), motDePasse.getText().toString())) {
                    changerActivite(MainActivity.class);
                }
                else {
                    texteErreur.setText(R.string.erreur_connexion);
                }
            }
        });

        texteInscrire.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                changerActivite(InscriptionActivity.class);
            }
        });

        if(Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    /**
     * On verifie que les champs ont bien ete remplis
     */
    private boolean verification() {
        return !(nomUtilisateur.getText().toString().equals("") || motDePasse.getText().toString().equals(""));
    }

    private void changerActivite(Class<? extends AppCompatActivity> activite) {
        Intent intent = new Intent(ConnexionActivity.this, activite);
        startActivity(intent);
        finish();
    }
}