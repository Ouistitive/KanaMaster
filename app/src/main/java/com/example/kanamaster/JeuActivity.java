package com.example.kanamaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import jeu.BuilderKana;
import jeu.Kana;
import jeu.TypeKana;

public class JeuActivity extends AppCompatActivity {

    private TypeKana type; // Type de kana du jeu
    private int indice; // indice du kana a trouver
    private List<Kana> kanas; // Liste des kanas
    private List<Button> choix; // Liste des boutons de choix
    private Random random; // La graine aleatoire du jeu
    private String strBouton; // Chaine de caracteres parametree pour le texte dans les boutons

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        // On initialise la graine et la chaine
        random = new Random();
        strBouton = "%s (%s)";

        // Stocke les boutons dans une liste
        choix = new ArrayList<>();
        choix.add((Button) findViewById(R.id.bouton_1));
        choix.add((Button) findViewById(R.id.bouton_2));
        choix.add((Button) findViewById(R.id.bouton_3));
        choix.add((Button) findViewById(R.id.bouton_4));

        for(Button b : choix) {
            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    modifierBoutons();
                }
            });
        }

        // On recupere le type de kanas que l'utilisateur devra deviner a partir de l'activite precedente
        Bundle extras = getIntent().getExtras();
        type = (TypeKana) extras.get("type");
        kanas = BuilderKana.prepareKana(type);

        // On melange la liste des kanas
        Collections.shuffle(kanas);

        modifierBoutons();
    }

    /**
     * @brief Modifie les boutons et change le kana a trouver
     */
    private void modifierBoutons() {
        Collections.shuffle(choix);

        // String.format permet de changer les %s par ce qu'il y a en parametres (comme les printf en C)
        choix.get(0).setText(String.format(strBouton, kanas.get(indice).getPrononciation(), kanas.get(indice).getType()));
        for(int i = 1; i < choix.size(); i++) {
            int nb = randomAvecExclusion(random, 0, kanas.size(), indice);
            choix.get(i).setText(String.format(strBouton, kanas.get(nb).getPrononciation(), kanas.get(nb).getType()));
        }

        indice++;
    }

    /**
     * @brief Tire un nombre aleatoire a partir d'un debut et d'une fin avec un numero exclu
     * @param r : La graine aleatoire
     * @param deb : la valeur minimale
     * @param fin : la valeur maximale
     * @param exclu : la valeur interdite
     * @return un entier aleatoire different de exclu
     */
    public int randomAvecExclusion(Random r, int deb, int fin, int exclu) {
        int random = deb + r.nextInt(fin - deb + 1 - 1);
        if (random >= exclu)
            random++;
        return random;
    }
}