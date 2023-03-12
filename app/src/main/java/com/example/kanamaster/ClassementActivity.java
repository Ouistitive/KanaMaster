package com.example.kanamaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Map;

import jdbc.UtilisateurBD;
import jeu.kana.TypeKana;

public class ClassementActivity extends AppCompatActivity {
    TableLayout tableauHiragana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);

        tableauHiragana = (TableLayout) findViewById(R.id.tableauHiragana);

        Map<String, Integer> map = UtilisateurBD.selectionnerMeilleurScore(TypeKana.HIRAGANA);

        tableauHiragana.setStretchAllColumns(true);
        tableauHiragana.bringToFront();
        for (Map.Entry m : map.entrySet()) {
            TableRow tr =  new TableRow(this);
            TextView c1 = new TextView(this);
            c1.setText(m.getKey().toString());
            TextView c2 = new TextView(this);
            c2.setText(String.valueOf(m.getValue()));
            tr.addView(c1);
            tr.addView(c2);
            tableauHiragana.addView(tr);
        }
    }

    private void initialiserTableau(TypeKana type) {
        Map<String, Integer> map = UtilisateurBD.selectionnerMeilleurScore(type);

        tableauHiragana.bringToFront();
        for (Map.Entry m : map.entrySet()) {
            TableRow tr =  new TableRow(this);
            TextView c1 = new TextView(this);
            c1.setText(m.getKey().toString());
            TextView c2 = new TextView(this);
            c2.setText(String.valueOf(m.getValue()));
            tr.addView(c1);
            tr.addView(c2);
            tableauHiragana.addView(tr);
        }
    }
}