package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UtilisateurBD {
    private static Statement st;

    public static boolean identification(String nomU, String mdp) {
        boolean valide = false;
        st = ConnexionBD.connexionBD();

        try{
            ResultSet res = st.executeQuery("SELECT motDePasse FROM Utilisateur WHERE nomUtilisateur = '" + nomU + "';");
            if(res.next()) {
                String motDePasse = res.getString(1);
                valide = motDePasse.equals(mdp);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        ConnexionBD.fermer();
        return valide;
    }

    public static String t() {
        String s = null;
        st = ConnexionBD.connexionBD();

        try{
            ResultSet res = st.executeQuery("SELECT * FROM alphabet;");
            if(res.next()) {
                String motDePasse = res.getString(1);
                s = motDePasse;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        ConnexionBD.fermer();
        return s;
    }

    /**
     * Retourne si le nom d'utilisateur est valide ou non
     * Il est valide si le nom n'a pas encore ete utilise
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

    public static void insererUtilisateur(String nomU, String mdp) {
        st = ConnexionBD.connexionBD();

        try {
            st.executeUpdate("INSERT INTO Utilisateur VALUES ('" + nomU + "', '" + mdp + "');");

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
