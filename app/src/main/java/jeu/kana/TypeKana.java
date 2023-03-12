package jeu.kana;

public enum TypeKana {
    HIRAGANA("Hiragana"), KATAKANA("Katakana"), KANA("Kana");
    private String nom;

    private TypeKana(String n) {
        nom = n;
    }

    public TypeKana get(String n) {
        for (TypeKana t : TypeKana.values())
            if (t.nom == n)
                return t;
        return null;
    }

    public String toString() {
        return ""+nom;
    }
}
