package kanamaster.jeu;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import activites.JeuActivity;
import kanamaster.BuilderKana;
import kanamaster.kana.Kana;
import kanamaster.kana.TypeKana;

public class KanaMasterJeu {
    private TypeKana type; // Type de kana qui sera devine
    private ImageView imageKana; // Image du kana a deviner
    private int indice; // indice du kana a trouver
    private List<Kana> kanas; // Liste des kanas
    private Random random; // La graine aleatoire du jeu
    private int scoreJoueur; // Le score du joueur

    public KanaMasterJeu(TypeKana type) {
        this.type = type;
        this.random = new Random();
        this.indice = 0;
        this.scoreJoueur = 0;

        kanas = BuilderKana.prepareKana(this.type);
        Collections.shuffle(kanas);
    }

    public KanaMasterJeu(TypeKana type, Random r) {
        this(type);
        this.random = r;
    }

    /**
     * @brief Increment le score du joueur
     */
    public void incrementerScore() {
        this.scoreJoueur++;
    }

    /**
     * @brief Modifie le texte des 4 boutons pour correspondre a 4 propositions dont une vraie et trois fausses
     * @param boutons : La liste des boutons
     * @param strFormatBouton : La chaine parametree pour le format du texte dans les boutons
     */
    public void modifierBouton(List<Button> boutons, String strFormatBouton) {
        if(indice >= kanas.size()) {
            reinitListe();
        }

        // String.format permet de changer les %s par ce qu'il y a en parametres (comme les printf en C)
        boutons.get(0).setText(String.format(strFormatBouton, kanas.get(indice).getPrononciation(), kanas.get(indice).getType()));
        //imageKana.setBackgroundResource("@drawable/");

        System.out.println(String.format(strFormatBouton, kanas.get(indice).getPrononciation(), kanas.get(indice).getType()));
        for(int i = 1; i < boutons.size(); i++) {
            int nb = randomAvecExclusion(random, 0, kanas.size()-1, indice);
            boutons.get(i).setText(String.format(strFormatBouton, kanas.get(nb).getPrononciation(), kanas.get(nb).getType()));
        }
    }

    /**
     * @brief Incremente l'indice du jeu
     */
    public void incrementerIndice() {
        indice++;
    }

    /**
     * @brief Retourne le score du joueur
     * @return int : Le score du joueur
     */
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
     * @brief Retourne le nom du kana sous forme du nom de l'image
     * @return String : le nom du kana
     */
    public String getNomKana() {
        return kanas.get(indice).getType().toString().toLowerCase() + "_" + kanas.get(indice).getPrononciation().toLowerCase();
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
    public int randomAvecExclusion(Random r, int deb, int fin, int exclu) {
        int random = deb + r.nextInt(fin - deb + 1 - 1);
        if (random >= exclu)
            random++;
        return random;
    }

    /*public void test(Resources r) {
        for(int i = 0; i < kanas.size(); i++) {
            System.out.println(getNomKana());
            String mDrawableName = getNomKana();
            int resID = r.getIdentifier(mDrawableName, "drawable", JeuActivity.class.getPackageName());
            Drawable drawable = r.getDrawable(resID);
            imageKana.setImageDrawable(drawable);

            indice++;
        }
    }*/
}
