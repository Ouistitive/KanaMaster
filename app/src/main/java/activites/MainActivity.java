package activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kanamaster.R;

import kanamaster.kana.TypeKana;

public class MainActivity extends AppCompatActivity {

    private Button boutonHiragana, boutonKatakana, boutonDeux, boutonClassement; // Les Buttons pour le choix du type de kanas a deviner et le classement
    private String nomUtilisateur; // Le nom du joueur
    private ImageView boutonDeconnexion; // Image cliquable pour se deconnecter
    private TextView texteBienvenue; // TextView du bienvenue pour afficher le nom utilisateur

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // On recupere le nom utilisateur de l activite precedente
        Bundle extras = getIntent().getExtras();
        nomUtilisateur = extras.getString("nomU");

        boutonHiragana = (Button) findViewById(R.id.boutonHiragana);
        boutonKatakana = (Button) findViewById(R.id.boutonKatakana);
        boutonDeux = (Button) findViewById(R.id.boutonDeux);
        boutonClassement = (Button) findViewById(R.id.boutonClassement);
        boutonDeconnexion = (ImageView) findViewById(R.id.boutonDeconnexion);
        texteBienvenue = (TextView) findViewById(R.id.texteBienvenue);

        texteBienvenue.setText("Bonjour " + nomUtilisateur + " !");

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

        boutonDeconnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changerActiviteDeconnexion();
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

    /**
     * @brief Change d'activite pour remettre sur la connexion et inscription
     */
    private void changerActiviteDeconnexion() {
        Intent intent = new Intent(MainActivity.this, ConnexionActivity.class);
        startActivity(intent);
        finish();
    }
}