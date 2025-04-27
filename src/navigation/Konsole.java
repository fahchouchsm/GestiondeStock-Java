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
        return sc.nextInt();
    }

    public static String readUserLine() {
        return sc.nextLine();
    }

    public static void closeScanner() {
        sc.close();
    }

    public static void showNavigator() {
        while (true) {
            clearConsole();
            ProductPage productP = new ProductPage();
            productP.showPage();
            break;
        }
    }

    public static void printTable(ArrayList<? extends Table> tables, String[] columnsNames) {
        for (Table table : tables) {
            String[] rowData = table.getColumnsNames();
            for (int i = 0; i < columnsNames.length; i++) {
                System.out.printf("%-20s", rowData[i]);
            }
            System.out.println();
        }
    }
}
