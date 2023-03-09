package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private final static String url = "jdbc:mysql://192.168.1.12:3306/KanaMaster";
    private final static String utilisateur = "android";
    private final static String motDePasse = "root";
    private static Connection connexion;

    public static void connexionBD() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            connexion.createStatement();
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
