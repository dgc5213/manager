package yt.tojava.taskmanager;

import java.util.Scanner;

/**
 * Ui class that will be responsible for interacting with the user. Ideally, only this class
 * should interact with the user.
 */
public class Ui {


    private static Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * @return the user input information
     */
    public static String readUserCommand() {
        System.out.println("Your task?");
        return in.nextLine().trim();
//        Scanner scan = new Scanner(System.in);  // Reading from System.in
//        String s=scan.nextLine();
//        return s;
    }

    /**
     * print the welcome message
     */
    public static void printWelcome() {
        System.out.println("Welcome to TaskManager");
    }


    /**
     *
     * @return ask for the file load option
     */
    public static String readFileLoadOptionCommand() {
        System.out.println("Would you like to load file from hard disk?");
        System.out.println("Please enter: Y/N");
        return in.nextLine().trim();
    }

    /**
     *
     * @return ask for the file save option: default path or new path
     */
    public static String askFileSaveCommand() {
        System.out.println("Would you like to save task to DefaultPath (Overwrite) or NewPath?");
        System.out.println("Please enter: DP/NP");
        return in.nextLine().trim();
    }

    /**
     *
     * @return ask for the new path
     */
    public static String getNewPathCommand() {
        System.out.println("Please enter New Path( C:\\Users\\user\\Desktop\\...):");
        return in.nextLine().trim();
    }



    /**
     * @param s message
     * @return return the message that need to show to the user
     */
    public static void showToUser(String s) {
        System.out.println(s);
    }

    /**
     * @param message message
     *                print the message
     */
    public static void printError(String message) {
        System.out.println(message);
    }


}


