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
        System.out.println("[4] Rechercher un produit");

        int choice = Konsole.readUserInputInt();

        switch (choice) {
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
                showPage(pageNum, limite);
                break;
            case 4:
                searchProduct();
                break;
            default:
                showPage(pageNum, limite);
                break;
        }
    }

    private void searchProduct() {
        Konsole.clearConsole();
        Konsole.showTitle("Recherche de Produit");
        System.out.println("Entrez le nom du produit (ou une partie du nom):");
        String searchTerm = Konsole.readUserLine().trim();

        if (searchTerm.isEmpty()) {
            System.out.println("Le terme de recherche ne peut pas être vide!");
            Konsole.sleep(2000);
            showPage(1, 10);
            return;
        }

        ArrayList<Product> searchResults = Product.searchProductsByTitle(searchTerm);

        Konsole.clearConsole();
        Konsole.showTitle("Résultats de Recherche");

        if (searchResults.isEmpty()) {
            System.out.println("Aucun produit trouvé pour: " + searchTerm);
        } else {
            Konsole.printTable(searchResults, Product.getColumnsNames());
        }

        System.out.println("\n[0] Retour à la liste des produits");
        System.out.println("[1] Nouvelle recherche");

        int choice = Konsole.readUserInputInt();
        switch (choice) {
            case 0:
                showPage(1, 10);
                break;
            case 1:
                searchProduct();
                break;
            default:
                showPage(1, 10);
                break;
        }
    }

    protected void addItem(String[] columnsNames) {
        Konsole.clearConsole();
        ArrayList<String> inputs = new ArrayList<>();

        for (int i = 1; i < columnsNames.length; i++) {
            String inputAddon;
            if (i == 2) {
                inputAddon = " (optional)";
            }
            if (i == 3) {
                inputAddon = " ";
            } else {
                inputAddon = " :";
            }
            System.out.println("- " + columnsNames[i] + inputAddon);
            System.out.flush();

            String input = Konsole.readUserLine().trim();

            if (i != 2 && i != 3 && i != 4) {
                while (input.isEmpty()) {
                    System.out.println("Le champ requis! Essayer à nouveau :");
                    input = Konsole.readUserLine().trim();
                }
            }

            inputs.add(input.isEmpty() ? null : input);
        }

        new Product(
                inputs.get(0),
                Float.parseFloat(inputs.get(1)),
                inputs.get(2),
                inputs.get(3) == null ? 0.0f : Float.parseFloat(inputs.get(3)),
                inputs.get(4) == null ? 0.0f : Float.parseFloat(inputs.get(4)),
                Float.parseFloat(inputs.get(5)));
        Konsole.clearConsole();
        System.out.println("Vous avez Ajouter un produit " + inputs.get(0) + "....");
        Konsole.sleep(3000);
    }
}