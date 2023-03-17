package kanamaster.jeu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import kanamaster.kana.TypeKana;

public class KanaMasterJeuTest {
    private KanaMasterJeu kanamaster;

    @Before
    public void initKanaMaster() {
        kanamaster = new KanaMasterJeu(TypeKana.HIRAGANA, new Random());
    }

    @Test
    public void testScore() {
        assertEquals(0, kanamaster.getScoreJoueur());
        kanamaster.incrementerScore();
        assertEquals(1, kanamaster.getScoreJoueur());
    }

    @Test
    public void testRandomAvecValeurExclue() {
        for(int i = 0; i < 20; i++) {
            assertNotEquals(3, kanamaster.randomAvecExclusion(new Random(), 1, 5, 3));
        }
    }
}
