package tables;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import database.DatabaseManager;

public class Product extends Table {
    private int id;
    private String titre;
    private float quantite;
    private String unite;
    private float seuil;
    private float prixAchat;
    private float prixUnitaire;

    public Product(int id, String titre, float quantite, String unite, float seuil, float prixAchat,
            float prixUnitaire) {
        super("products");
        this.id = id;
        this.titre = titre;
        this.quantite = quantite;
        this.unite = unite;
        this.seuil = seuil;
        this.prixAchat = prixAchat;
        this.prixUnitaire = prixUnitaire;
    }

    public Product(String titre, float quantite, String unite, float seuil, float prixAchat, float prixUnitaire) {
        super("products");
        this.titre = titre;
        this.quantite = quantite;
        this.unite = unite;
        this.seuil = seuil;
        this.prixAchat = prixAchat;
        this.prixUnitaire = prixUnitaire;

        try {
            String query = "INSERT INTO " + tableName +
                    " (titre, quantite, unite, seuil, prixAchat, prixUnitaire) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = DatabaseManager.getConnection()
                    .prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, titre);
            pst.setFloat(2, quantite);
            pst.setString(3, unite);
            pst.setFloat(4, seuil);
            pst.setFloat(5, prixAchat);
            pst.setFloat(6, prixUnitaire);

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

    public static String[] getColumnsNames() {
        return new String[] {
                "id", "Titre", "Quantité", "Unité de mesure", "Seuil d'alerte", "Prix d'achat", "Prix unitaire"
        };
    }

    @Override
    public String[] getRowsDataAsString() {
        String uniteClean = (unite == null || unite.isEmpty()) ? "unitaire" : unite;
        return new String[] {
                Integer.toString(id), titre, Float.toString(quantite),
                uniteClean, Float.toString(seuil), Float.toString(prixAchat), Float.toString(prixUnitaire)
        };
    }

    public static ArrayList<Product> getAllProducts(int pageNum, int limit) {
        try {
            ArrayList<Product> products = new ArrayList<>();
            ResultSet rs = fetchTable("products", pageNum, limit);
            while (rs.next()) {
                Product p = new Product(
                        rs.getInt("idProduit"), rs.getString("titre"), rs.getFloat("quantite"),
                        rs.getString("unite"), rs.getFloat("seuil"),
                        rs.getFloat("prixAchat"), rs.getFloat("prixUnitaire"));
                products.add(p);
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // New methods required by SetQuantitePage
    public static Product getProductById(int id) {
        try {
            String query = "SELECT * FROM products WHERE idProduit = ?";
            PreparedStatement pst = DatabaseManager.getConnection().prepareStatement(query);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("idProduit"),
                        rs.getString("titre"),
                        rs.getFloat("quantite"),
                        rs.getString("unite"),
                        rs.getFloat("seuil"),
                        rs.getFloat("prixAchat"),
                        rs.getFloat("prixUnitaire"));
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Product searchProductByTitle(String searchTerm) {
        try {
            String query = "SELECT * FROM products WHERE titre LIKE ? LIMIT 1";
            PreparedStatement pst = DatabaseManager.getConnection().prepareStatement(query);
            pst.setString(1, "%" + searchTerm + "%");

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return new Product(
                        rs.getInt("idProduit"),
                        rs.getString("titre"),
                        rs.getFloat("quantite"),
                        rs.getString("unite"),
                        rs.getFloat("seuil"),
                        rs.getFloat("prixAchat"),
                        rs.getFloat("prixUnitaire"));
            }
            rs.close();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update() {
        try {
            String query = "UPDATE products SET titre = ?, quantite = ?, unite = ?, seuil = ?, prixAchat = ?, prixUnitaire = ? WHERE idProduit = ?";
            PreparedStatement pst = DatabaseManager.getConnection().prepareStatement(query);
            pst.setString(1, titre);
            pst.setFloat(2, quantite);
            pst.setString(3, unite);
            pst.setFloat(4, seuil);
            pst.setFloat(5, prixAchat);
            pst.setFloat(6, prixUnitaire);
            pst.setInt(7, id);

            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }

    public float getSeuil() {
        return seuil;
    }

    public void setSeuil(float seuil) {
        this.seuil = seuil;
    }

    public float getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(float prixAchat) {
        this.prixAchat = prixAchat;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
}