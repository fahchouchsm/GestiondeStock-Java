package pages;

import java.util.ArrayList;

import navigation.Konsole;
import tables.Product;

public class ProductPage extends Page {

    public ProductPage() {
        super("Produits");
    }

    // TODO -
    @Override
    public void showPage() {
        Konsole.showTitle(pageName);
        ArrayList<Product> products = Product.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("Il n'y a pas de produits");
        } else {
            Konsole.printTable(products, products.get(0).getColumnsNames());
        }
    }
}
