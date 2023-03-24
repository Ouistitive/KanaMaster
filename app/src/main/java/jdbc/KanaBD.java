package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kanamaster.kana.Kana;
import kanamaster.kana.TypeKana;

public class KanaBD {
    private static Statement st;

    /**
     * @brief Recupere tous les hiragana dans la base de donnees
     * @return List<Kana> : La liste des hiragana
     */
    public static List<Kana> selectionHiragana() {
        ResultSet rs = null;
        List<Kana> hiraganas = new ArrayList<>();

        st = ConnexionBD.connexionBD();

        try {
            rs = st.executeQuery("SELECT sonorite FROM Alphabet WHERE type ='Hiragana';");
            while (rs.next()) {
                hiraganas.add(new Kana(rs.getString("sonorite"), TypeKana.HIRAGANA));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ConnexionBD.fermer();
        return hiraganas;
    }

    /**
     * @brief Recupere tous les katakana dans la base de donnees
     * @return List<Kana> : La liste des katakana
     */
    public static List<Kana> selectionKatakana() {
        ResultSet rs = null;
        List<Kana> katakanas = new ArrayList<>();

        st = ConnexionBD.connexionBD();

        try {
            rs = st.executeQuery("SELECT sonorite FROM Alphabet WHERE type ='Katakana';");
            while (rs.next()) {
                katakanas.add(new Kana(rs.getString("sonorite"), TypeKana.KATAKANA));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ConnexionBD.fermer();
        return katakanas;
    }
}
