package kanamaster.kana;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class KanaTest {
    @Test
    public void test() {
        Kana kana = new Kana("KO", TypeKana.HIRAGANA);
        assertEquals(kana.getPrononciation(), "KO");
        assertEquals(kana.getType(), TypeKana.HIRAGANA);
    }
}
