package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {
    private static Connection cn;

    public DatabaseManager(String url, String username, String password) {
        try {
            if (cn == null || cn.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                cn = DriverManager.getConnection(url, username, password);
                System.out.println("✅ Connected to DB");
            } else {
                System.out.println("⚠️ Already connected");
            }
        } catch (Exception e) {
            System.out.println("❌ Error connecting: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        return cn;
    }

    public static void closeConnection() {
        try {
            if (cn != null && !cn.isClosed()) {
                cn.close();
                System.out.println("🔒 Connection closed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
