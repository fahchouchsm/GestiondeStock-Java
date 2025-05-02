
import database.DatabaseManager;
import navigation.Konsole;

public class Main {
    public static void main(String[] args) throws Exception {
        new DatabaseManager("jdbc:mysql://localhost:3306/GSJava", "root", "");
        Konsole.clearConsole();
        Konsole.showTitle("Gestion de Stock FitnessHub");
        Konsole.showNavigator();
    }
}
