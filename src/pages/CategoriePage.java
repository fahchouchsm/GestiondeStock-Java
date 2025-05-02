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
}
