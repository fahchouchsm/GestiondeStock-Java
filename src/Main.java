
import java.util.ArrayList;

import database.DatabaseManager;
import navigation.Konsole;
import tables.Product;

public class Main {
    public static void main(String[] args) throws Exception {
        Konsole.clearConsole();
        DatabaseManager db = new DatabaseManager("jdbc:mysql://localhost:3306/GSJava", "root", "");
        Konsole.showNavigator();
    }
}
