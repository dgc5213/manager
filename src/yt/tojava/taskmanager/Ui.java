package yt.tojava.taskmanager;

import java.util.Scanner;

public class Ui {


    private static Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public static String readUserCommand() {
        System.out.println("Ui<---->Your task?");
        return in.nextLine().trim();
//        Scanner scan = new Scanner(System.in);  // Reading from System.in
//        String s=scan.nextLine();
//        return s;
    }

    public static void printWelcome() {
        System.out.println("Welcome to TaskManager");
    }

    public String showToUser(String s) {
        return s;
    }


    public static void printError(String message) {
        System.out.println(message);
    }


}


