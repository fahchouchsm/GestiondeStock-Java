package tables;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseManager;

public abstract class Table {
    protected String tableName;

    // abstract methods
    public abstract String[] getColumnsNames();

    public Table(String tableName) {
        this.tableName = tableName;
    }

    protected static ResultSet fetchTable(String tableName, int pageNum, int limite) {
        String query = "SELECT * FROM " + tableName + " LIMIT " + limite + " OFFSET " + (pageNum - 1) * limite;
        try {
            ResultSet rs = DatabaseManager.st.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
