package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseManager {
    private static final String url = "jdbc:mysql://localhost:3306/GSJava";
    private static final String username = "root";
    private static final String password = "";
    private static Connection cn = null;
    public static Statement st = null;

    public static void connnectDB() {
        try {
            if (st != null) {
                System.out.println("Already connected to the database");
            } else {
                Class.forName("com.mysql.cj.jdbc.Driver");
                cn = DriverManager.getConnection(url, username, password);
                st = cn.createStatement();
                System.out.println("Connected to the database");
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