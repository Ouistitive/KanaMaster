package activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.kanamaster.R;

import java.util.HashMap;
import java.util.Map;

import jdbc.UtilisateurBD;
import kanamaster.kana.TypeKana;

public class ClassementActivity extends AppCompatActivity {
    private TableLayout tableauHiragana, tableauKatakana, tableauKana; // Les tableaux pour afficher les classements
    private Button boutonRetour; // Le bouton de retour pour revenir sur la MainActivity
    private TableRow.LayoutParams paramTextView; // Parametres pour les TextView dans les tableaux
    private String nomUtilisateur; // Le nom utilisateur de la personne connectee
    private Map<TypeKana, Integer> dico; // Le dictionnaire des types avec leur id dans le XML

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);

        tableauHiragana = (TableLayout) findViewById(R.id.tableauHiragana);
        tableauKatakana = (TableLayout) findViewById(R.id.tableauKatakana);
        tableauKana = (TableLayout) findViewById(R.id.tableauKana);
        boutonRetour = (Button) findViewById(R.id.boutonRetour);

        paramTextView = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 1);

        Bundle extras = getIntent().getExtras();
        nomUtilisateur = (String) extras.get("nomU");

        dico = new HashMap<>();
        dico.put(TypeKana.HIRAGANA, R.id.texteScoreHiragana);
        dico.put(TypeKana.KATAKANA, R.id.texteScoreKatakana);
        dico.put(TypeKana.KANA, R.id.texteScoreKana);

        initialiserTableau(TypeKana.HIRAGANA, tableauHiragana);
        initialiserTableau(TypeKana.KATAKANA, tableauKatakana);
        initialiserTableau(TypeKana.KANA, tableauKana);

        boutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                retour();
            }
        });
    }

    /**
     * @brief Insert les lignes dans le tableau en fonction de la position du joueur
     * @param type : La categorie du tableau
     * @param tl : Le tableau qui sera modifie
     */
    private void initialiserTableau(TypeKana type, TableLayout tl) {
        Map<String, Integer> map = UtilisateurBD.selectionnerMeilleurScore(type);
        boolean estFonce = false;
        boolean nonClasse = true;
        TableRow.LayoutParams paramTableRow = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        TableLayout.LayoutParams paramTableLayout = new TableLayout.LayoutParams(TableLayout.LayoutParams.FILL_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);

        for (Map.Entry m : map.entrySet()) {
            TableRow row =  new TableRow(this);
            TextView c1 = new TextView(this);
            TextView c2 = new TextView(this);

            if(estFonce)
                row.setBackground(ContextCompat.getDrawable(this, R.drawable.bordurefoncee));
            else
                row.setBackground(ContextCompat.getDrawable(this, R.drawable.bordureclaire));

            if(m.getKey().toString().equals(nomUtilisateur)) {
                row.setBackground(ContextCompat.getDrawable(this, R.drawable.borduremoi));
                nonClasse = false;
            }

            estFonce = !estFonce;

            row.setLayoutParams(paramTableRow);
            row.setPadding(5, 5, 5, 5);

            changerStyleTexte(c1, m.getKey().toString());
            changerStyleTexte(c2, String.valueOf(m.getValue()));

            row.addView(c1);
            row.addView(c2);
            tl.addView(row, paramTableLayout);
        }

        if(nonClasse) {
            TextView tv = (TextView) findViewById(dico.get(type));
            tv.setText("Votre meilleur record : " + UtilisateurBD.recupererMeilleurScore(nomUtilisateur, type));
        }
    }

    /**
     * @brief Change l'apparance du texte avec des parametres predefinies
     * @param tv : La TextView qui sera modifiee
     * @param txt : Le texte dans la TextView
     */
    private void changerStyleTexte(TextView tv, String txt) {
        tv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv.setLayoutParams(paramTextView);
        tv.setTextColor(getColor(R.color.couleur_principale_blanc));
        tv.setText(txt);
    }

    /**
     * @brief Quitte l'activite
     */
    private void retour() {
        finish();
        overridePendingTransition(R.anim.fondu_apparition, R.anim.glissement_droit);
    }
}