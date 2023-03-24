package jdbc;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnexionBD {

    private static String ip = "192.168.1.12"; // 192.168.1.12 | sql7.freemysqlhosting.net

    private static String port = "3306";

    private static String nomBD = "KanaMaster"; // KanaMaster | sql7608023

    private static String url = "jdbc:mysql://"+ ip + ":" + port + "/"+ nomBD + "?useSSL=false&allowPublicKeyRetrieval=true";

    private static final String user = "android"; // android | sql7608023

    private static final String pass = "root"; // root | qwfyL3VPu3

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
     * @brief Ferme la connexion a la base de donnees
     */
    public static void fermer() {
        try {
            connexion.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Hash le texte entre pour le securiser dans la base de donnees
     * @param txt : Le texte a hasher
     * @return String : Le texte hashe
     */
    public static String hash(String txt) {
        String mdpGenere = null;

        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(txt.getBytes());

            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            mdpGenere = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return mdpGenere;
    }
}
