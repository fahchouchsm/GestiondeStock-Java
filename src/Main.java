
import database.DatabaseManager;
import navigation.Konsole;

public class Main {
    public static void main(String[] args) throws Exception {
        new DatabaseManager("jdbc:mysql://localhost:3306/gsJava", "root", "");
        Konsole.clearConsole();
        Konsole.showNavigator();
    }
}
