package tables;

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
            String query = "INSERT INTO " + tableName
                    + " (titre, quantite, unite, seuil, prixAchat, prixUnitaire) VALUES ('"
                    + titre + "', " + quantite + ", '" + unite + "', " + seuil + ", " + prixAchat + ", " + prixUnitaire
                    + ")";
            DatabaseManager.st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = DatabaseManager.st.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // getters
    public static String[] getColumnsNames() {
        return new String[] { "id", "Titre", "Quantitie", "Unite", "Seuil", "Prix d'achat", "Prix unitaire" };
    }

    @Override
    public String[] getAllDataAsString() {
        String uniteNull = unite;
        if (uniteNull == null) {
            uniteNull = "unitaire";
        }
        return new String[] { Integer.toString(id), titre, Float.toString(quantite),
                uniteNull, Float.toString(seuil),
                Float.toString(prixAchat), Float.toString(prixUnitaire) };
    }

    // TODO -
    public static ArrayList<Product> getAllProducts(int pageNum, int limite) {
        try {
            ArrayList<Product> products = new ArrayList<Product>();
            ResultSet rs = fetchTable("products", pageNum, limite);
            while (rs.next()) {
                Product p = new Product(rs.getInt("id"), rs.getString("titre"), rs.getFloat("quantite"),
                        rs.getString("unite"), rs.getFloat("seuil"), rs.getFloat("prixAchat"),
                        rs.getFloat("prixUnitaire"));
                products.add(p);
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
