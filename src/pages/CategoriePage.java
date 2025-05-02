package pages;

import navigation.Konsole;
import tables.Categorie;

public class CategoriePage extends Page {

    public CategoriePage() {
        super("categories");
    }

    @Override
    public void showPage(int pageNum, int limite) {
        Konsole.clearConsole();
        Konsole.showTitle(pageName);
        Categorie currentCat = Categorie.getCategorie(pageNum);
        System.out.println(currentCat.getNom());
        System.out.println(currentCat.getDescription());
        Konsole.showLine();
        System.out.println("Nom du categorie :");
        System.out.println(" - " + currentCat.getNom());
        System.out.println("Description :");
        System.out.println(" - " + currentCat.getDescription());
    }

    @Override
    protected void addItem(String[] columnsNames, String msgError) {

    };
}
