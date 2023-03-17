package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexionBD {

    private static String ip = "192.168.1.12";

    private static String port = "3306";

    private static String nomBD = "KanaMaster";

    private static String url = "jdbc:mysql://"+ ip + ":" + port + "/"+ nomBD + "?useSSL=false&allowPublicKeyRetrieval=true";

    private static final String user = "android";

    private static final String pass = "root";

    private static Connection connexion;

    /**
     * @brief Se connecte a la base de donnees, cree un statement
     * @return Statement : le statement pour faire des requetes SQL
     */
    public static Statement connexionBD() {
        Statement st = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connexion = DriverManager.getConnection(url, user, pass);
            st = connexion.createStatement();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return st;
    }

    /**
     * Ferme la connexion a la base de donnees
     */
    public static void fermer() {
        try {
            connexion.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
