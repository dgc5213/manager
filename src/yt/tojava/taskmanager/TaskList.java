package yt.tojava.taskmanager;

import java.util.*;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void markAsDone(String line) {
        int index = Integer.parseInt(line.substring("done".length()).trim());
        this.tasks.get(index - 1).setDone(true);
        System.out.println("Done tasks in the list: " + tasks.size());
    }

    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + tasks.get(i));
        }
    }

    public void addTask(Task t) {
        tasks.add(t);

    }


    /*public void deleteTask(List<Task> tasks){
        int index = Integer.parseInt(line.substring("done".length()).trim());
        this.tasks.get(index - 1).deleteT();
        }
    }*/

    public void saveTasks() throws TaskManagerException {
        Storage.saveToDisk(tasks);
    }

    public int size() {
        return tasks.size();
    }


}