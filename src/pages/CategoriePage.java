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
        // TODO
    }

    @Override
    protected void addItem(String[] columnsNames, String msgError) {

    };
}
