package kanamaster.kana;

public class Kana {
    private String prononciation; // La prononciation en romaji
    private TypeKana type; // Le type de Kana, hiragana ou katakana

    public Kana(String prononciation, TypeKana type) {
        this.prononciation = prononciation;
        this.type = type;
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
