package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseManager {
    private String url;
    private String username;
    private String password;
    private static Connection cn = null;
    public static Statement st = null;

    public DatabaseManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        connectDB();
    }

    private void connectDB() {
        try {
            if (st != null) {
                System.out.println("Already connected to the database 😎");
            } else {
                Class.forName("com.mysql.cj.jdbc.Driver");
                cn = DriverManager.getConnection(url, username, password);
                st = cn.createStatement();
                System.out.println("Connected to the database 🚀");
            }
        } catch (Exception e) {
            System.out.println("Error connecting to the database: " + e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            if (st != null) {
                st.close();
            }
            if (cn != null) {
                cn.close();
            }
            System.out.println("Connection closed");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
