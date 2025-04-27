
import java.util.ArrayList;

import classes.Product;
import database.DatabaseManager;
import navigation.Konsole;

public class Main {
    public static void main(String[] args) throws Exception {
        Konsole.clearConsole();
        DatabaseManager db = new DatabaseManager("jdbc:mysql://localhost:3306/GSJava", "root", "");

    }
}
