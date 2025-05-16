package tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
            if (rs.next()) {
                return new Categorie(rs.getInt("idCategorie"),
                        rs.getString("nom"),
                        rs.getString("description"));
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Product> getCategorieProducts(int catId, int limite) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String query = "SELECT p.idProduit, p.titre, p.quantite, p.unite, p.seuil, p.prixAchat, p.prixUnitaire  FROM categorie_products cp INNER JOIN products p on cp.idProduit = p.idProduit WHERE cp.idCategorie = ? LIMIT ?";
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
            ps.setInt(1, catId);
            ps.setInt(2, limite);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getInt("idProduit"), rs.getString("titre"), rs.getFloat(
                        "quantite"), rs.getString("unite"), rs.getFloat("seuil"), rs.getFloat("prixAchat"),
                        rs.getFloat("prixUnitaire")));
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return products;
        }
    }

    public static boolean isProductInCategory(int categoryId, int productId) {
        try {
            String query = "SELECT * FROM categorie_products WHERE idCategorie = ? AND idProduit = ?";
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
            ps.setInt(1, categoryId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();
            boolean exists = rs.next();
            rs.close();
            ps.close();
            return exists;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void addProductToCategory(int categoryId, int productId) {
        try {
            String query = "INSERT INTO categorie_products (idCategorie, idProduit) VALUES (?, ?)";
            PreparedStatement ps = DatabaseManager.getConnection().prepareStatement(query);
            ps.setInt(1, categoryId);
            ps.setInt(2, productId);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String[] getRowsDataAsString() {
        return new String[] { Integer.toString(id), nom, description };
    }

    public static String[] getColumnsNamesForProducts() {
        return new String[] {
                "ProduitID", "Titre", "Quantité", "Unité de mesure", "Prix unitaire"
        };
    }

    public static String[] getColumsNames() {
        return new String[] { "Id", "Nom du Categorie", "Description" };
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }
}