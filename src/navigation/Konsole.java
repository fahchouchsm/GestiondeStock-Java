package navigation;

import java.util.ArrayList;
import java.util.Scanner;

import classes.Table;

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
        return input;
    }

    public static String readUserLine() {
        return sc.nextLine();
    }

    public static void closeScanner() {
        sc.close();
    }

    // public static void printTable(ArrayList<? extends table> list, String[]
    // columns) {
    // for (String column : columns) {
    // System.out.printf("%-20s", column);
    // }
    // System.out.println();
    // System.out.println(new String(new char[columns.length * 20]).replace("\0",
    // "-"));
    // for (Table item : list) {
    // String[] rowData = item.getData();
    // for (int i = 0; i < columns.length; i++) {
    // System.out.printf("%-20s", rowData[i]);
    // }
    // System.out.println();
    // }
    // }

    public static void printTable(ArrayList<? extends Table> tables, String[] columnsNames) {
        for (String column : columnsNames) {
            System.out.printf("%-20s", column);
        }
        System.out.println();
        System.out.println(new String(new char[columnsNames.length * 20]).replace("\0", "-"));
        for (Table table : tables) {
            String[] rowData = table.getColumnsNames();
            for (int i = 0; i < columnsNames.length; i++) {
                System.out.printf("%-20s", rowData[i]);
            }
            System.out.println();
        }
    }
}
