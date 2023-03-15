package kanamaster;

import java.util.ArrayList;
import java.util.List;

import jdbc.KanaBD;
import kanamaster.kana.Kana;
import kanamaster.kana.TypeKana;

public class BuilderKana {

    /**
     * @brief Construit la liste des kanas selon le type de kana choisi
     * @param type : le type de kana
     * @return List<Kana> : La liste de kana remplie*/
    public static List<Kana> prepareKana(TypeKana type) {
        List<Kana> kanas = new ArrayList<>();

        if(type == TypeKana.KANA) {
            kanas = KanaBD.selectionHiragana();
            kanas.addAll(KanaBD.selectionKatakana());
        }
        else if(type == TypeKana.HIRAGANA) {
            kanas = KanaBD.selectionHiragana();
        }
        else {
            kanas = KanaBD.selectionKatakana();
        }
        return kanas;
    }
}
