package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import kanamaster.kana.TypeKana;

public class UtilisateurBD {
    private static Statement st; // Le statement pour faire les requetes a la base de donnees

    /**
     * @brief Verifie a partir d'un nom utilisateur et un mot de passe si il est dans la base de donnees
     * @param nomU : Le nom utilisateur
     * @param mdp : Le mot de passe
     */
    public static boolean identification(String nomU, String mdp) {
        boolean valide = false;
        st = ConnexionBD.connexionBD();

        try{
            ResultSet res = st.executeQuery("SELECT motDePasse FROM Utilisateur WHERE nomUtilisateur = '" + nomU + "';");
            if(res.next()) {
                String motDePasse = res.getString(1);
                valide = motDePasse.equals(ConnexionBD.hash(mdp));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        ConnexionBD.fermer();
        return valide;
    }

    /**
     * @brief Retourne si le nom d'utilisateur est valide ou non, il est valide si le nom n'a pas encore ete utilise
     * @param nomU : le nom utilisateur
     * @return true : le nom utilisateur n'existe pas
     * @return false : le nom utilisateur existe deja
     */
    public static boolean nomUtilisateurValide(String nomU) {
        boolean valide = false;
        st = ConnexionBD.connexionBD();

        try{
            ResultSet res = st.executeQuery("SELECT * FROM Utilisateur WHERE nomUtilisateur = '" + nomU + "';");
            if(!res.next())
                valide = true;
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        ConnexionBD.fermer();
        return valide;
    }

    /**
     * @brief Recupere le meilleur score d'un type donne d'un utilisateur
     * @param nomU : le nom utilisateur
     * @param type : la categorie du record
     */
    public static int recupererMeilleurScore(String nomU, TypeKana type) {
        int score = 0;
        st = ConnexionBD.connexionBD();

        try {
            ResultSet rs = st.executeQuery("SELECT MeilleurScore" + type.toString() + " FROM Utilisateur WHERE nomUtilisateur = '" + nomU + "'");
             if(rs.next()) {
                 score = rs.getInt("MeilleurScore" + type.toString());
             }

        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        ConnexionBD.fermer();
        return score;
    }

    /**
     * @brief Insert le nouveau record pour un joueur dans une categorie
     * @param nomU : Le nom du joueur qui a fait le record
     * @param score : Le score que le joueur a eu
     * @param type : La categorie dans laquelle le score sera enregistre
     */
    public static void insererNouveauRecord(String nomU, int score, TypeKana type) {
        st = ConnexionBD.connexionBD();

        try {
            st.executeUpdate("UPDATE Utilisateur SET MeilleurScore" + type.toString() + " = " + score + " WHERE nomUtilisateur = '" + nomU + "';");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        ConnexionBD.fermer();
    }

    /**
     * @brief Ajoute un nouvel utilisateur dans la base de donnees
     * @param nomU : Le nom utilisateur
     * @param mdp : le mot de passe
     */
    public static void insererUtilisateur(String nomU, String mdp) throws UtilisateurExistantException {
        if(!nomUtilisateurValide(nomU))
            throw new UtilisateurExistantException();

        st = ConnexionBD.connexionBD();

        try {
            st.executeUpdate("INSERT INTO Utilisateur VALUES ('" + nomU + "', '" + ConnexionBD.hash(mdp) + "', 0, 0, 0);");

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        ConnexionBD.fermer();
    }

    /**
     * @brief Selectionne les 10 meilleurs scores dans une categorie donnee
     * @param type : la categorie de kana
     * @return Map<String, Integer> : le dictionnaire avec le pseudo et le score des 10 meilleurs joueurs
     */
    public static Map<String, Integer> selectionnerMeilleurScore(TypeKana type) {
        st = ConnexionBD.connexionBD();
        Map<String, Integer> map = new LinkedHashMap<>();

        try {
            ResultSet rs = st.executeQuery("SELECT nomUtilisateur, MeilleurScore" + type.toString() + " FROM Utilisateur ORDER BY MeilleurScore" + type.toString() + " DESC LIMIT 10");
            while(rs.next()) {
                String s = rs.getString("nomUtilisateur");
                map.put(s, rs.getInt("MeilleurScore" + type.toString()));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        ConnexionBD.fermer();
        return map;
    }
}
