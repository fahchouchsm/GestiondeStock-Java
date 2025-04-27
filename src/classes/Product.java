package classes;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import database.DatabaseManager;

public class Product extends Table {
    private int id;
    private String titre;
    private int quantite;
    private String unite;
    private float seuil;
    private float prixAchat;
    private float prixUnitaire;

    public Product(String titre, int quantite, String unite, float seuil, float prixAchat, float prixUnitaire) {
        super("products");
        this.titre = titre;
        this.quantite = quantite;
        this.unite = unite;
        this.seuil = seuil;
        this.prixAchat = prixAchat;
        this.prixUnitaire = prixUnitaire;
        try {
            String query = "INSERT INTO " + tableName
                    + " (titre, quantitie, unite, seuil, prixAchat, prixUnitaire) VALUES ('"
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
    public String[] getColumnsNames() {
        return new String[] { "titre", "quantitie", "unite", "seuil", "prixAchat", "prixUnitaire" };
    }

    // public ArrayList<Product> getProducts(Resultset rs) {
    // try {
    // ArrayList<Product> products = new ArrayList<Product>();
    // while (rs.) {

    // }
    // return products;
    // } catch (Exception e) {
    // e.printStackTrace();
    // return null;
    // }
    // }

}
