package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexionBD {

    private static String ip = "192.168.121.199";

    private static String port = "3306";

    private static String nomBD = "KanaMaster";

    private static String url = "jdbc:mysql://"+ ip + ":" + port + "/"+ nomBD;

    private static final String user = "android2";

    private static final String pass = "root";

    private static Connection connexion;

    public static Statement connexionBD() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connexion = DriverManager.getConnection(url, user, pass);
            return connexion.createStatement();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void fermer() {
        try {
            connexion.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
