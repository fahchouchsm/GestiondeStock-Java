package pages;

import navigation.Konsole;

public class HomePage extends Page {

    public HomePage(String pageName) {
        super(pageName);
    }

    @Override
    public void showPage() {
        Konsole.showTitle(pageName);
    }
}
