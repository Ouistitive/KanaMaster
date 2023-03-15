package com.example.kanamaster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Map;

import jdbc.UtilisateurBD;
import kanamaster.kana.TypeKana;

public class ClassementActivity extends AppCompatActivity {
    TableLayout tableauHiragana, tableauKatakana, tableauKana; // Les tableaux pour afficher les classements

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);

        tableauHiragana = (TableLayout) findViewById(R.id.tableauHiragana);
        tableauKatakana = (TableLayout) findViewById(R.id.tableauKatakana);
        tableauKana = (TableLayout) findViewById(R.id.tableauKana);

        initialiserTableau(TypeKana.HIRAGANA, tableauHiragana);
        initialiserTableau(TypeKana.KATAKANA, tableauKatakana);
        initialiserTableau(TypeKana.KANA, tableauKana);
    }

    /**
     * @brief Insert les lignes dans le tableau en fonction de la position du joueur
     * @param type : La categorie du tableau
     * @param tl : Le tableau qui sera modifie
     */
    private void initialiserTableau(TypeKana type, TableLayout tl) {
        Map<String, Integer> map = UtilisateurBD.selectionnerMeilleurScore(type);

        for (Map.Entry m : map.entrySet()) {
            System.out.println(m.getKey().toString() + " " + m.getValue());
            TableRow row =  new TableRow(this);
            TextView c1 = new TextView(this);
            TextView c2 = new TextView(this);

            row.setBackground(ContextCompat.getDrawable(this, R.drawable.border));
            c1.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            c2.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            c1.setText(m.getKey().toString());
            c2.setText(String.valueOf(m.getValue()));

            tl.addView(row);
            row.addView(c1);
            row.addView(c2);
        }
    }
}