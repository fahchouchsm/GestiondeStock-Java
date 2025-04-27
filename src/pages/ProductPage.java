package pages;

import java.util.ArrayList;

import navigation.Konsole;
import tables.Product;

public class ProductPage extends Page {

    public ProductPage() {
        super("Gestion des Produits");
    }

    @Override
    public void showPage(int pageNum, int limite) {
        Konsole.clearConsole();
        Konsole.showTitle(pageName);
        ArrayList<Product> products = Product.getAllProducts(pageNum, limite);
        String[] columnsName = Product.getColumnsNames();
        if (products.isEmpty()) {
            System.out.println("Il n'y a pas de produits");
        } else {
            Konsole.printTable(products, columnsName);
        }
        System.out.println("[0] Quitter ");
        System.out.println("[1] Suivant");
        System.out.println("[2] Precedent");
        System.out.println("[3] Ajouter un nouveau produit");

        switch (Konsole.readUserInputInt()) {
            case 0:
                Konsole.showNavigator();
                break;
            case 1:
                showPage(pageNum + 1, limite);
                break;
            case 2:
                if (pageNum == 1) {
                    showPage(pageNum, limite);
                } else {
                    showPage(pageNum - 1, limite);
                }
                break;
            case 3:
                addItem(columnsName);
            default:
                showPage(pageNum, limite);
                break;
        }
    }

    // TODO -
    @Override
    protected void addItem(String[] columnsNames) {
        Konsole.clearConsole();
        ArrayList<String> inputs = new ArrayList<>();
        for (int i = 1; i < columnsNames.length; i++) {
            System.out.println(columnsNames[i] + " :");
            System.out.flush();
            String input = Konsole.readUserLine();
            inputs.add(input);
        }
    }
}
