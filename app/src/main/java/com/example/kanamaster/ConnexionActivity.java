package com.example.kanamaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import jdbc.UtilisateurBD;

public class ConnexionActivity extends AppCompatActivity {

    private EditText nomUtilisateur, motDePasse;
    private Button seConnecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        nomUtilisateur = (EditText) findViewById(R.id.nomUtilisateur);
        motDePasse = (EditText) findViewById(R.id.motDePasse);
        seConnecter = (Button) findViewById(R.id.connecter);

        seConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(UtilisateurBD.identification(nomUtilisateur.getText().toString(), motDePasse.getText().toString())) {

                }
                else {

                }
            }
        });
    }

}