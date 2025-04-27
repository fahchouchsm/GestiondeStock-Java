
import java.util.ArrayList;

import classes.Product;
import database.DatabaseManager;
import navigation.Konsole;

public class Main {
    public static void main(String[] args) throws Exception {
        Konsole.clearConsole();
        DatabaseManager.connnectDB();
        ArrayList<Product> products = new ArrayList<Product>();
        Konsole.printTable(products, args);
    }
}
