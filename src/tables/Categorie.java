package tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import database.DatabaseManager;

public class Categorie extends Table {
    private int id;
    private String nom;
    private String description;

    public Categorie(int id, String nom, String description) {
        super("categories");
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public Categorie(String nom, String description) {
        super("categories");
        this.nom = nom;
        this.description = description;

        try {
            String query = "INSERT INTO categories (nom, description) VALUES (?, ?)";
            PreparedStatement pst = DatabaseManager.getConnection()
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, nom);
            pst.setString(2, description);

            int rows = pst.executeUpdate();
            if (rows > 0) {
                ResultSet rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    this.id = rs.getInt(1);
                }
                rs.close();
            }
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Categorie getCategorie(int orderNum) {
        try {
            String query = "SELECT * FROM categories c ORDER BY c.idCategorie LIMIT 1 OFFSET ?";
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
            ps.setInt(1, orderNum - 1);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new Categorie(rs.getInt("idCategorie"),
                    rs.getString("nom"),
                    rs.getString("description"));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // TODO
    // public int getCategorieSearch(String input) {}

    @Override
    public String[] getRowsDataAsString() {
        return new String[] { Integer.toString(id), nom, description };
    }
}
