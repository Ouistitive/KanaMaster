package com.example.kanamaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import jdbc.UtilisateurBD;

public class InscriptionActivity extends AppCompatActivity {

    private EditText nomUtilisateur, motDePasse, confirmation;
    private TextView texteErreur, connecter;
    private Button sInscrire;

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
            @Override
            public void onClick(View view) {
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
                if(!verificationMotDePasse()) {
                    texteErreur.setText(R.string.erreur_mot_de_passe_different);
                    return;
                }

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

    private boolean verification() {
        return !(nomUtilisateur.getText().toString().equals("") || motDePasse.getText().toString().equals("") ||
                confirmation.getText().toString().equals(""));
    }

    private boolean verificationMotDePasse() {
        return motDePasse.getText().toString().equals(confirmation.getText().toString());
    }

    private void changerActivite(Class<? extends AppCompatActivity> activite) {
        Intent intent = new Intent(InscriptionActivity.this, activite);
        startActivity(intent);
        finish();
    }
}