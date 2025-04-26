
import database.DatabaseManager;
import navigation.Konsole;

public class App {
    public static void main(String[] args) throws Exception {
        Konsole.clearConsole();
        DatabaseManager.connnectDB();
    }
}
