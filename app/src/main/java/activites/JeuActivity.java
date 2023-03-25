package activites;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kanamaster.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import animations.ClignotementAnimation;
import animations.TremblementAnimation;
import kanamaster.jeu.CompteurJeu;
import kanamaster.jeu.KanaMasterJeu;
import kanamaster.kana.TypeKana;

public class JeuActivity extends AppCompatActivity {
    private TypeKana type; // Type de kana du jeu
    private List<Button> choix; // Liste des boutons de choix
    private String strBouton, strScore; // Chaine de caracteres parametree pour le texte dans les boutons et pour le score
    private TextView texteChrono, texteScore; // TextView pour le chronometre du jeu
    private ImageView imageKana; // Image du kana qui est a deviner
    private KanaMasterJeu kanaMaster; // Le jeu
    private ForegroundColorSpan fcsRed; // Pour marquer le texte du score en rouge

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        // On recupere le type de kanas que l'utilisateur devra deviner a partir de l'activite precedente
        Bundle extras = getIntent().getExtras();
        type = (TypeKana) extras.get("type");

        imageKana = findViewById(R.id.imageKana);

        kanaMaster = new KanaMasterJeu(type);

        // On initialise les chaines
        strBouton = "%s (%s)";
        strScore = "Score : %d";
        fcsRed = new ForegroundColorSpan(getColor(R.color.couleur_principale_rouge));

        texteScore = findViewById(R.id.score);

        mettreAJourScoreJoueur();

        // Stocke les boutons dans une liste
        choix = new ArrayList<>();
        choix.add(findViewById(R.id.bouton_1));
        choix.add(findViewById(R.id.bouton_2));
        choix.add(findViewById(R.id.bouton_3));
        choix.add(findViewById(R.id.bouton_4));

        for(Button b : choix) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    boolean correct = kanaMaster.verification(b.getText().toString(), strBouton);
                    if(correct) {
                        kanaMaster.incrementerScore();
                        mettreAJourScoreJoueur();
                    }

                    if(correct) {
                        kanaMaster.incrementerIndice();
                        modifierBoutons();
                    }
                    else {
                        mettreBoutonsCliquables(false);
                        new TremblementAnimation(view) {
                            public void onFinish() {
                                kanaMaster.incrementerIndice();
                                modifierBoutons();
                                mettreBoutonsCliquables(true);
                            }
                        }.start();
                    }
                }
            });
        }

        texteChrono = findViewById(R.id.chronometre);
        lancerChronometre();
        modifierBoutons();
    }

    /**
     * @brief Met a jour le texte du score du joueur
     */
    private void mettreAJourScoreJoueur() {
        String text = String.format(strScore, kanaMaster.getScoreJoueur());
        SpannableString ss = new SpannableString(text);
        SpannableStringBuilder ssb = new SpannableStringBuilder(text);
        ssb.setSpan(fcsRed, 7, text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        texteScore.setText(ssb);
    }

    /**
     * @brief Modifie l'etat des boutons en les rendant cliquable ou non
     * @param estCliquable : l'etat que vont avoir les boutons
     */
    private void mettreBoutonsCliquables(boolean estCliquable) {
        for(Button b : choix) {
            b.setEnabled(estCliquable);
        }
    }

    /**
     * @brief Modifie les boutons et change le kana a trouver
     */
    private void modifierBoutons() {
        Collections.shuffle(choix);
        kanaMaster.modifierBouton(choix, strBouton);
        modifierImage();
    }

    /**
     * @brief Modifie l'image du kana affiche a l ecran pour
     * remplacer avec celui qui est a deviner
     */
    private void modifierImage() {
        Resources res = getResources();
        String mDrawableName = kanaMaster.getNomKana();
        int resID = res.getIdentifier(mDrawableName, "drawable", getPackageName());
        Drawable drawable = res.getDrawable(resID);
        imageKana.setImageDrawable(drawable);
    }

    /**
     * @brief Lance le chronometre et l'affiche dans le TextView
     */
    private void lancerChronometre() {
        new CompteurJeu(texteChrono, getResources().getColor(R.color.couleur_principale_rouge), getResources().getColor(R.color.couleur_principale_noir)) {
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

    /*private void test() {
        Resources res = getResources();
        String str = getPackageName();
        kanaMaster.test(res, str);
    }*/

    /**
     * @brief Envoie à la prochaine activite pour afficher le score du joueur
     */
    public void changerActivite() {
        Bundle extras = getIntent().getExtras();
        String nomUtilisateur = extras.getString("nomU");

        Intent intent = new Intent(JeuActivity.this, ScoreActivity.class);
        intent.putExtra("score", kanaMaster.getScoreJoueur());
        intent.putExtra("nomU", nomUtilisateur);
        intent.putExtra("type", type);
        startActivity(intent);
        finish();
    }
}