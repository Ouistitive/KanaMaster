package kanamaster.kana;

import static org.junit.Assert.*;

import org.junit.Test;

import kanamaster.kana.TypeKana;

public class TypeKanaTest {
    @Test
    public void testHiraganaToString() {
        TypeKana type = TypeKana.HIRAGANA;
        assertEquals(type.toString(), "Hiragana");
    }

    @Test
    public void testKatakanaToString() {
        TypeKana type = TypeKana.KATAKANA;
        assertEquals(type.toString(), "Katakana");
    }

    @Test
    public void testKanaToString() {
        TypeKana type = TypeKana.KANA;
        assertEquals(type.toString(), "Kana");
    }
}
