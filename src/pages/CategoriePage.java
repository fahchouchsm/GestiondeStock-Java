package pages;

import java.util.ArrayList;
import navigation.Konsole;
import tables.Categorie;
import tables.Product;

public class CategoriePage extends Page {

    public CategoriePage() {
        super("categories");
    }

    @Override
    public void showPage(int pageNum, int limite) {
        Categorie currentCat = Categorie.getCategorie(pageNum);
        if (currentCat != null) {
            Konsole.clearConsole();
            Konsole.showTitle(pageName);
            System.out.println("Id :");
            System.out.println(" - " + currentCat.getId());
            System.out.println("Nom du categorie :");
            System.out.println(" - " + currentCat.getNom());
            System.out.println("Description :");
            System.out.println(" - " + currentCat.getDescription());
            Konsole.showLine();
            Konsole.printTable(Categorie.getCategorieProducts(currentCat.getId(), limite), Product.getColumnsNames());
            System.out.println("[0] Quitter ");
            System.out.println("[1] Suivant");
            System.out.println("[2] Precedant");
            System.out.println("[3] Ajouter une categorie");
            System.out.println("[4] Ajouter un produit à cette catégorie");
            int input = Konsole.readUserInputInt();
            switch (input) {
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
                    addItem(Categorie.getColumsNames());
                    showPage(pageNum, limite);
                    break;
                case 4:
                    addProductToCategory(currentCat.getId());
                    showPage(pageNum, limite);
                    break;
                default:
                    showPage(pageNum, limite);
                    break;
            }
        } else {
            Konsole.clearConsole();
            Konsole.showTitle(pageName);
            System.out.println("Aucune catégorie trouvée.");
            System.out.println("[0] Quitter ");
            System.out.println("[1] Ajouter une catégorie");
            int input = Konsole.readUserInputInt();
            switch (input) {
                case 0:
                    Konsole.showNavigator();
                    break;
                case 1:
                    addItem(Categorie.getColumsNames());
                    showPage(pageNum, limite);
                    break;
                default:
                    showPage(pageNum, limite);
                    break;
            }
        }
    }

    @Override
    protected void addItem(String[] columnsNames) {
        String addon;
        ArrayList<String> inputs = new ArrayList<>();
        String input;
        for (int i = 1; i < columnsNames.length; i++) {
            addon = "";
            if (i == 2) {
                addon = " (optionnel) :";
            } else {
                addon = " :";
            }
            System.out.println(columnsNames[i] + addon);
            input = Konsole.readUserLine();
            inputs.add(input);
        }
        new Categorie(inputs.get(0), inputs.get(1));
        Konsole.clearConsole();
        System.out.println("Vous avez ajouter la categorie " + inputs.get(0) + "....");
        Konsole.sleep(3000);
    }

    private void addProductToCategory(int categoryId) {
        System.out.println("Comment souhaitez-vous ajouter un produit ?");
        System.out.println("[1] Rechercher par nom");
        System.out.println("[2] Entrer l'ID directement");
        int choice = Konsole.readUserInputInt();

        int productId = -1;
        Product selectedProduct = null;

        switch (choice) {
            case 1:
                System.out.println("Entrez le nom du produit à rechercher (ou une partie du nom) :");
                String searchTerm = Konsole.readUserLine().trim();
                if (searchTerm.isEmpty()) {
                    System.out.println("Le terme de recherche ne peut pas être vide.");
                    Konsole.sleep(2000);
                    return;
                }
                ArrayList<Product> searchResults = Product.searchProductsByTitle(searchTerm);
                if (searchResults.isEmpty()) {
                    System.out.println("Aucun produit trouvé pour : " + searchTerm);
                    Konsole.sleep(2000);
                    return;
                }
                Konsole.clearConsole();
                System.out.println("Résultats de la recherche :");
                Konsole.printTable(searchResults, Product.getColumnsNames());
                System.out.println("Entrez l'ID du produit à ajouter à cette catégorie :");
                productId = Konsole.readUserInputInt();
                for (Product p : searchResults) {
                    if (p.getId() == productId) {
                        selectedProduct = p;
                        break;
                    }
                }
                if (selectedProduct == null) {
                    System.out.println("ID de produit invalide.");
                    Konsole.sleep(2000);
                    return;
                }
                break;

            case 2:
                System.out.println("Entrez l'ID du produit à ajouter à cette catégorie :");
                productId = Konsole.readUserInputInt();
                selectedProduct = Product.getProductById(productId);
                if (selectedProduct == null) {
                    System.out.println("Produit non trouvé pour l'ID : " + productId);
                    Konsole.sleep(2000);
                    return;
                }
                break;

            default:
                System.out.println("Choix invalide.");
                Konsole.sleep(2000);
                return;
        }

        if (Categorie.isProductInCategory(categoryId, productId)) {
            System.out.println("Le produit est déjà dans cette catégorie.");
        } else {
            Categorie.addProductToCategory(categoryId, productId);
            System.out.println("Produit ajouté à la catégorie avec succès.");
        }
        Konsole.sleep(2000);
    }
}