package navigation;

import java.util.ArrayList;
import java.util.Scanner;

import pages.ProductPage;
import tables.Table;

public class Konsole {
    private static Scanner sc = new Scanner(System.in);

    public static void showTitle(String name) {
        System.out.println("================================================================ " + name
                + " ================================================================");
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
        System.out.println("[3] Produits avec un seuil alert");
        System.out.println("[0] Quitter");
        switch (readUserInputInt()) {
            case 1:
                ProductPage productP = new ProductPage();
                productP.showPage(1, 10);
                break;
            default:
                showNavigator();
                break;
        }

    }

    public static void printTable(ArrayList<? extends Table> tables, String[] columns) {
        for (String column : columns) {
            System.out.printf("%-20s", column);
        }
        System.out.println();
        String line = new String(new char[columns.length * 20]).replace("\0", "-");
        System.out.println(line);
        for (Table table : tables) {
            String[] rowData = table.getRowsDataAsString();
            for (int i = 0; i < columns.length; i++) {
                System.out.printf("%-20s", rowData[i]);
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
