package pages;

public abstract class Page {
    protected String pageName;

    public Page(String pageName) {
        this.pageName = pageName;
    }

    // abstract methods
    public abstract void showPage(int pageNum, int limite);

    protected abstract void addItem(String[] columnsNames);
}
