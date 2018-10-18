package yt.tojava.taskmanager;

import java.util.*;

/**
 * TaskList class that is responsible for keeping the in-memory task list. Most likely
 * this class will use an ArrayList inside it.
 */
public class TaskList {

    private List<Task> tasks;

    public TaskList() {
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * @param line read full command
     *             change the task status to done
     */
    public void markAsDone(String line) {
        int index = Integer.parseInt(line.substring("done".length()).trim());
        this.tasks.get(index - 1).setDone(true);
        System.out.println("Done tasks in the list: " + tasks.size());
    }

    /**
     * print the tasks from the list one by one
     */
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + tasks.get(i));
        }
    }

    /**
     * @param t get the individual task
     *          and add it to the task list
     */
    public void addTask(Task t) {
        tasks.add(t);
    }


    /*public void deleteTask(List<Task> tasks){
        int index = Integer.parseInt(line.substring("done".length()).trim());
        this.tasks.get(index - 1).deleteT();
        }
    }*/

    /**
     * save the task to the hard disk
     *
     * @throws TaskManagerException show the exception error
     */
    public void saveTasks() throws TaskManagerException {
        Storage.saveToDisk(tasks);
    }


}