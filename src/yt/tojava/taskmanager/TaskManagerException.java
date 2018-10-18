package yt.tojava.taskmanager;

/**
 * TaskManagerException class that inherits the Exception class
 * Override the constructor that takes a String parameter so that you can specify the error information when
 * you create a TaskManagerException object.
 */
public class TaskManagerException extends Exception {
    public TaskManagerException(String message) {
        super(message);
    }
}
