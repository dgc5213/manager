package yt.tojava.taskmanager;

/**
 * FileNotFoundException class that inherits the Exception class
 * Override the constructor that takes a String parameter so that you can specify the error information when
 * you create a FileNotFoundException object.
 */
public class FileNotFoundException extends Exception {
    public FileNotFoundException(String message) {
        super(message);
    }
}
