package jeu.kana;

import jeu.kana.TypeKana;

public class Kana {
    private String symbole; // Le symbole japonais du Kana
    private String prononciation; // La prononciation en romaji
    private TypeKana type; // Le type de Kana, hiragana ou katakana

    public Kana(String symbole, String prononciation, TypeKana type) {
        this.symbole = symbole;
        this.prononciation = prononciation;
        this.type = type;
    }

    /**
     * @brief Retourne le symbole japonais du kana
     * @return String : le symbole
     */
    public String getSymbole() {
        return this.symbole;
    }

    /**
     * @brief Retourne la prononciation en romaji du kana
     * @return String : la prononciation
     */
    public String getPrononciation() {
        return this.prononciation;
    }

    /**
     * @brief Retourne le type de kana
     * @return TypeKana : le type
     */
    public TypeKana getType() {
        return type;
    }
}
