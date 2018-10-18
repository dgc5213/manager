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
        System.out.println("Ui<---->Your task?");
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
     * @param s message
     * @return return the message that need to show to the user
     */
    public String showToUser(String s) {
        return s;
    }

    /**
     * @param message message
     *                print the message
     */
    public static void printError(String message) {
        System.out.println(message);
    }


}


