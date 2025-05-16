package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {
    private static Connection cn = null;

    public DatabaseManager(String url, String username, String password) {
        try {
            if (cn == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                cn = DriverManager.getConnection(url, username, password);
                System.out.println("CoConnecté à db");
            } else {
                System.out.println("Déjà connecté");
            }
        } catch (Exception e) {
            System.out.println("ErConnexion d'erreur" + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return cn;
    }

    public static void closeConnection() {
        try {
            if (cn != null && !cn.isClosed()) {
                cn.close();
                System.out.println("Connexion fermée");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
