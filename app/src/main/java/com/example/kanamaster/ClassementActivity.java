package com.example.kanamaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Map;

import jdbc.UtilisateurBD;
import jeu.kana.TypeKana;

public class ClassementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classement);

        Map<String, Integer> map = UtilisateurBD.selectionnerMeilleurScore(TypeKana.HIRAGANA);
        
    }
}