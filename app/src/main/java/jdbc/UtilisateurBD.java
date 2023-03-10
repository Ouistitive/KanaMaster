package jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UtilisateurBD {
    private Statement st;

    public UtilisateurBD() {
        st = ConnexionBD.connexionBD();
    }
    public boolean identification(String nomU, String mdp) {
        try{
            ResultSet res = st.executeQuery("SELECT motDePasse FROM Utilisateur WHERE nomUtilisateur = '" + nomU + "';");
            if(res.next()){
                String motDePasse = res.getString("motDePasse");
                return motDePasse.equals(mdp);
            }
            else {
                return false;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
