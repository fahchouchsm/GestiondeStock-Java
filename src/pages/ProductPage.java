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
                addItem(columnsName, null);
                showPage(pageNum, limite);
            default:
                showPage(pageNum, limite);
                break;
        }
    }

    protected void addItem(String[] columnsNames, String msgError) {
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
