package activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kanamaster.R;

import jdbc.UtilisateurBD;
import jdbc.UtilisateurExistantException;

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
                passerConnexionActivity();
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
                try {
                    String s1 = nomUtilisateur.getText().toString().substring(0, 1).toUpperCase();
                    String s2 = nomUtilisateur.getText().toString().substring(1).toLowerCase();
                    String nomUtilisateurStr = s1 + s2;

                    UtilisateurBD.insererUtilisateur(nomUtilisateurStr, motDePasse.getText().toString());
                    passerMainActivity(nomUtilisateurStr);
                }
                catch (UtilisateurExistantException e) {
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
     * @brief Passe a l'activite principale et supprime l'ancienne
     */
    private void passerMainActivity(String nomU) {
        Intent intent = new Intent(InscriptionActivity.this, MainActivity.class);
        intent.putExtra("nomU", nomU);
        startActivity(intent);
        finish();
    }

    /**
     * @brief Passe a l'activite connexion et supprime l'ancienne
     */
    private void passerConnexionActivity() {
        Intent intent = new Intent(InscriptionActivity.this, ConnexionActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}