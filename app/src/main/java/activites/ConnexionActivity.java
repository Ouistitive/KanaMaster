package activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.os.StrictMode;
import android.os.Build;
import android.widget.TextView;

import com.example.kanamaster.R;

import jdbc.UtilisateurBD;

public class ConnexionActivity extends AppCompatActivity {

    private EditText nomUtilisateur, motDePasse; // Les EditText pour saisir le nom utilisateur et mot de passe
    private TextView texteErreur, texteInscrire; // Les TextView pour le message d'erreur et le texte cliquable pour s'inscrire
    private Button seConnecter; // Button qui permet de s'identifier

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
            /**
             * @brief Lors du clique, verifie la validite des EditText, verifie si l'utilisateur
             * existe dans la BD et si son mot de passe est correcte
             */
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

                String s1 = nomUtilisateur.getText().toString().substring(0, 1).toUpperCase();
                String s2 = nomUtilisateur.getText().toString().substring(1).toLowerCase();
                String nomUtilisateurStr = s1 + s2;
                if(UtilisateurBD.identification(nomUtilisateurStr, motDePasse.getText().toString())) {
                    passerMainActivity(nomUtilisateurStr);
                }
                else {
                    texteErreur.setText(R.string.erreur_connexion);
                }
            }
        });

        texteInscrire.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                passerInscriptionActivity();
            }
        });

        if(Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    /**
     * @brief Verifie que le nom d'utilisateur et le mot de passe sont differents de vide
     */
    private boolean verification() {
        return !(nomUtilisateur.getText().toString().equals("") || motDePasse.getText().toString().equals(""));
    }

    /**
     * @brief Passe a l'activite inscription et supprime l'ancienne
     */
    private void passerInscriptionActivity() {
        Intent intent = new Intent(ConnexionActivity.this, InscriptionActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    /**
     * @brief Passe a l'activite principale et supprime l'ancienne
     */
    private void passerMainActivity(String nomU) {
        Intent intent = new Intent(ConnexionActivity.this, MainActivity.class);
        intent.putExtra("nomU", nomU);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.glissement_gauche, R.anim.fondu_disparition);
    }
}