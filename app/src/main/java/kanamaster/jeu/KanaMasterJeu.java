package kanamaster.jeu;

import android.widget.Button;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import kanamaster.BuilderKana;
import kanamaster.kana.Kana;
import kanamaster.kana.TypeKana;

public class KanaMasterJeu {
    private TypeKana type; // Type de kana qui sera devine
    private int indice; // indice du kana a trouver
    private List<Kana> kanas; // Liste des kanas
    private Random random; // La graine aleatoire du jeu
    private int scoreJoueur;


    public KanaMasterJeu(TypeKana type) {
        this.type = type;
        this.random = new Random();
        this.indice = 0;
        this.scoreJoueur = 0;

        kanas = BuilderKana.prepareKana(this.type);
        Collections.shuffle(kanas);
    }

    public void incrementerScore() {
        this.scoreJoueur++;
    }

    public void modifierBouton(List<Button> boutons, String strFormatBouton) {
        if(indice >= kanas.size()) {
            reinitListe();
        }

        // String.format permet de changer les %s par ce qu'il y a en parametres (comme les printf en C)
        boutons.get(0).setText(String.format(strFormatBouton, kanas.get(indice).getPrononciation(), kanas.get(indice).getType()));

        System.out.println(String.format(strFormatBouton, kanas.get(indice).getPrononciation(), kanas.get(indice).getType()));
        for(int i = 1; i < boutons.size(); i++) {
            int nb = randomAvecExclusion(random, 0, kanas.size()-1, indice);
            boutons.get(i).setText(String.format(strFormatBouton, kanas.get(nb).getPrononciation(), kanas.get(nb).getType()));
        }
    }

    public void incrementerIndice() {
        indice++;
    }

    public int getScoreJoueur() {
        return scoreJoueur;
    }

    /**
     * @brief Verifie si le kana est egal au bouton clique
     * @param texte : le texte dans le bouton
     * @return true : le bouton clique est la bonne reponse
     * @return false : le bouton clique est faux
     */
    public boolean verification(String texte, String strFormatBouton) {
        return String.format(strFormatBouton, kanas.get(indice).getPrononciation(), kanas.get(indice).getType()).equals(texte);
    }

    /**
     * @brief Reinitialise la liste et melange les kanas
     */
    private void reinitListe() {
        indice = 0;
        Collections.shuffle(kanas);
    }

    /**
     * @brief Tire un nombre aleatoire a partir d'un debut et d'une fin avec un numero exclu
     * @param r : La graine aleatoire
     * @param deb : la valeur minimale
     * @param fin : la valeur maximale
     * @param exclu : la valeur interdite
     * @return un entier aleatoire different de exclu
     */
    private int randomAvecExclusion(Random r, int deb, int fin, int exclu) {
        int random = deb + r.nextInt(fin - deb + 1 - 1);
        if (random >= exclu)
            random++;
        return random;
    }
}
