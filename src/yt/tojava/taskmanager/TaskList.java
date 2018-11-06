package yt.tojava.taskmanager;

import java.util.*;

/**
 * TaskList class that is responsible for keeping the in-memory task list. Most likely
 * this class will use an ArrayList inside it.
 */

public class TaskList {

    public List<Task> tasks;


    /**
     *
     * @param tasks declare the task list
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * declare the task list
     */
    public TaskList() {
    }



    /**
     * @param line read full command
     *             change the task status to done (true)
     */
    public void markAsDone(String line) {
        int index = Integer.parseInt(line.substring("done".length()).trim());
        this.tasks.get(index - 1).setDone(true);
    }

    /**
     *
     * @param line read full command
     *      change the task status to false
     */
    public void markAsNotDone(String line) {
        int index = Integer.parseInt(line.substring("notdone".length()).trim());
        this.tasks.get(index - 1).setDone(false);
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
     *
     * @param line get input from commend line
     *  parser the user input and remove the task from task list accordingly
     */
    public void removeTask(String line) {
        int index = Integer.parseInt(line.substring("remove".length()).trim());
//        System.out.println("remove task index:"+index);
        System.out.println("remove task:"+this.tasks.get(index - 1));
        Task t= this.tasks.get(index - 1);
//        System.out.println("Before Delete tasks(list size): " + tasks.size());
        tasks.remove(t);
        System.out.println("After removing task, remaining task list size: " + tasks.size());
    }

    /**
     * @param t get the individual task
     *          and add it to the task list
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * removes all of the elements from this list.The list will be empty after this call returns.
     *
     */
     public void clearTask(){
         System.out.println("Performing clear operation !!");
         tasks.clear();
         System.out.println("Now, list consists of "+ tasks.size() +" elements");
    }

    /**
     *
     * @param fullCommand read full command
     *    edit task's details accordingly
     */
    public void markAsEdit(String fullCommand) {
        /// fullCommand as: edit 1 deadline write report /by this Friday 4pm
        String newTaskCommand=Parser.getEditTask(fullCommand); //....deadline write report /by this Friday 4pm.
        String commandWord = Parser.getCommandWord(newTaskCommand);  // get the keyword: todo / deadline
        String task_detail = Parser.filterTaskDetails(newTaskCommand); ///  write report /by this Friday 4pm
        int index = Integer.parseInt(fullCommand.split(" ")[1]);

        switch (commandWord) {
            case "todo":
                System.out.println("<----This is todo edition---->");
                Todo t= (Todo) this.tasks.get(index - 1);
                System.out.println("Before editing the Todo:"+t);
                t.setDescription(task_detail);
                System.out.println("After editing the Todo:"+t);
                break;
            case "deadline":
                System.out.println("<----This is deadline edition---->");
                Deadline d= (Deadline) this.tasks.get(index - 1);
                System.out.println("Before editing the deadline:"+d);
                Parser.editDeadline(newTaskCommand,d);
                System.out.println("After editing the deadline:"+d);

//                String task_detail_by = Parser.filterDoByDetails(newTaskCommand);
//                task_detail = task_detail.replace("/by" + task_detail_by, "");
//                d.setDescription(task_detail);
//                d.setBy(task_detail_by);
//                System.out.println("After editing the deadline"+d);

                break;
        }

    }

    /**
     * save the task to the hard disk
     */
    public void saveTasks()  {
        Storage.saveToDisk(tasks);
    }




}