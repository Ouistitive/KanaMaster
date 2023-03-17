package kanamaster.kana;

public enum TypeKana {
    HIRAGANA("Hiragana"), KATAKANA("Katakana"), KANA("Kana");
    private String nom;

    private TypeKana(String n) {
        nom = n;
    }

    public String toString() {
        return ""+nom;
    }
}
