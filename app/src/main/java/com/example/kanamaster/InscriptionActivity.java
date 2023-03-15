package com.example.kanamaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import jdbc.UtilisateurBD;

public class InscriptionActivity extends AppCompatActivity {

    private EditText nomUtilisateur, motDePasse, confirmation; // Les EditText pour saisir le nom utilisateur, le mot de passe et sa confirmation
    private TextView texteErreur, connecter; // TextView pour les messages d'erreur et texte cliquable pour changer d'activite pour se connecter
    private Button sInscrire; // Button pour inserer l'utilisateur dans la BD

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        nomUtilisateur = (EditText) findViewById(R.id.nomUtilisateur);
        motDePasse = (EditText) findViewById(R.id.motDePasse);
        confirmation = (EditText) findViewById(R.id.motDePasseConfirmation);
        sInscrire = (Button) findViewById(R.id.sinscrire);
        texteErreur = (TextView) findViewById(R.id.texteErreur);
        connecter = (TextView) findViewById(R.id.connecter);

        connecter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                changerActivite(ConnexionActivity.class);
            }
        });

        sInscrire.setOnClickListener(new View.OnClickListener() {
            /**
             * @brief Verifie que l'utilisateur a des informations valides, sinon affiche un message d'erreur
             */
            @Override
            public void onClick(View view) {
                // On regarde si les champs sont remplis
                if(!verification()) {
                    if(nomUtilisateur.getText().toString().equals(""))
                        nomUtilisateur.setHintTextColor(getColor(R.color.couleur_principale_rouge));
                    if(motDePasse.getText().toString().equals(""))
                        motDePasse.setHintTextColor(getColor(R.color.couleur_principale_rouge));
                    if(confirmation.getText().toString().equals(""))
                        confirmation.setHintTextColor(getColor(R.color.couleur_principale_rouge));
                    texteErreur.setText(R.string.erreur_champ_non_rempli);
                    return;
                }
                // On verifie que le mot de passe et sa confirmation sont egaux
                if(!verificationMotDePasse()) {
                    texteErreur.setText(R.string.erreur_mot_de_passe_different);
                    return;
                }
                // On verifie que l'utilisateur n'existe pas deja
                if(UtilisateurBD.nomUtilisateurValide(nomUtilisateur.getText().toString())) {
                    UtilisateurBD.insererUtilisateur(nomUtilisateur.getText().toString(), motDePasse.getText().toString());
                    changerActivite(MainActivity.class);
                }
                else {
                    texteErreur.setText(R.string.erreur_nom_utilisateur_existe);
                }
            }
        });
    }

    /**
     * @brief Verifie que le nom, le mot de passe et la confirmation ont ete saisis
     */
    private boolean verification() {
        return !(nomUtilisateur.getText().toString().equals("") || motDePasse.getText().toString().equals("") ||
                confirmation.getText().toString().equals(""));
    }

    /**
     * @brief Verifie que le mot de passe et sa confirmation sont egaux
     */
    private boolean verificationMotDePasse() {
        return motDePasse.getText().toString().equals(confirmation.getText().toString());
    }

    /**
     * @brief Change d'activite et supprime l'ancienne
     * @param activite : la nouvelle activite
     */
    private void changerActivite(Class<? extends AppCompatActivity> activite) {
        Intent intent = new Intent(InscriptionActivity.this, activite);

        if(activite == MainActivity.class)
            intent.putExtra("nomU", nomUtilisateur.getText().toString());

        startActivity(intent);
        finish();
    }
}