package actionsPages;

import navigation.Konsole;
import tables.Product;
import java.util.ArrayList;

public class SeuilAlertPage implements simplePageInt {

    @Override
    public void showPage() {
        Konsole.clearConsole();
        Konsole.showTitle("Produits avec un seuil d'alerte");

        // Get products below alert threshold
        ArrayList<Product> alertProducts = Product.getProductsBelowSeuil();

        if (alertProducts.isEmpty()) {
            System.out.println("Aucun produit en dessous du seuil d'alerte.");
        } else {
            Konsole.printTable(alertProducts, Product.getColumnsNames());
        }

        System.out.println("\n[0] Retour");
        int choice = Konsole.readUserInputInt();
        if (choice == 0) {
            Konsole.showNavigator();
        } else {
            showPage();
        }
    }
}