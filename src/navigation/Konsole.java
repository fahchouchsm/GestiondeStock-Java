package navigation;

import java.util.ArrayList;
import java.util.Scanner;

import actionsPages.SetQuantitePage;
import actionsPages.SeuilAlertPage;
import pages.CategoriePage;
import pages.ProductPage;
import tables.Table;

public class Konsole {
    private static Scanner sc = new Scanner(System.in);

    public static void showTitle(String name) {
        System.out.println("================================================================ " + name
                + " ================================================================");
    }

    public static void showLine() {
        System.out.println(
                "=======================================================================================================================================");
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int readUserInputInt() {
        int input = sc.nextInt();
        sc.nextLine();
        return input;
    }

    public static String readUserLine() {
        return sc.nextLine();
    }

    public static void closeScanner() {
        sc.close();
    }

    public static void showNavigator() {
        clearConsole();
        System.out.println("[1] Gestion des Produits");
        System.out.println("[2] Gestion des Categorie");
        System.out.println("[3] Modifier Quantité d'un Produit");
        System.out.println("[4] Produits avec un seuil alert");
        System.out.println("[0] Quitter");
        switch (readUserInputInt()) {
            case 1:
                ProductPage productP = new ProductPage();
                productP.showPage(1, 10);
                break;
            case 2:
                CategoriePage categorieP = new CategoriePage();
                categorieP.showPage(1, 100);
                break;
            case 3:
                SetQuantitePage setQuantiteP = new SetQuantitePage();
                setQuantiteP.showPage();
                break;
            case 4:
                SeuilAlertPage seuilAlertP = new SeuilAlertPage();
                seuilAlertP.showPage();
                break;
            default:
                showNavigator();
                break;
        }

    }

    public static void printTable(ArrayList<? extends Table> tables, String[] columns) {
        final int columnWidth = 20;

        for (String column : columns) {
            String truncated = column.length() > columnWidth - 4
                    ? column.substring(0, columnWidth - 4) + "..."
                    : column;
            System.out.printf("%-" + columnWidth + "s", truncated);
        }
        System.out.println();
        String line = new String(new char[columns.length * columnWidth]).replace("\0", "-");
        System.out.println(line);

        for (Table table : tables) {
            String[] rowData = table.getRowsDataAsString();
            for (int i = 0; i < columns.length; i++) {
                String cell = rowData[i];
                String truncated = cell.length() > columnWidth - 5
                        ? cell.substring(0, columnWidth - 5) + "..."
                        : cell;
                System.out.printf("%-" + columnWidth + "s", truncated);
            }
            System.out.println();
        }
        System.out.println(line);
        System.out.println();
    }

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
