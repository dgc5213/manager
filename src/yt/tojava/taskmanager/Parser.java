package yt.tojava.taskmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Parser class containing methods that deals with parsing the user command to extract meaningful details from it.
 */
public class Parser {

//    protected static String fullCommand;

    protected static List<Task> tasks = new ArrayList<>();


    /**
     * @param fullCommand read the full command from the user input
     * @return Returns a the command word i.e., the first word of the given fullCommand
     */
    public static String getCommandWord(String fullCommand) {
        fullCommand = fullCommand.trim();  //It returns a copy of this string with leading and trailing white space removed, or this string if it has no leading or trailing white space.
//        System.out.println("----PARSER----getCommandWord--fullCommand:" + fullCommand);
        String command = fullCommand.split(" ")[0];//extract the first word of the user input
//        System.out.println("----PARSER----getCommandWord------firstword:" + command);
        return command;
    }


    /**
     *
     * @param fullCommand read the full command from the user input
     *  input as edit task_number todo/ dealine
     * @return as todo/deadline
     */
    public static String  getEditTask(String fullCommand){
        fullCommand = fullCommand.trim();  //It returns a copy of this string with leading and trailing white space removed, or this string if it has no leading or trailing white space.
        String command = fullCommand.split(" ",3)[2].trim();//extract the first word of the user input
        return command;
    }



    /**
     * @param line read full command
     * @return return task details
     */
    static String filterTaskDetails(String line) {
        String task_detail = "";
        int len = line.split(" ").length; //// to check who many words
        String[] line_arr = line.split(" ", 2);
        if (len > 1) {
            task_detail = line_arr[1];  // filter out the first words
        }
        return task_detail;
    }

    /**
     * @param line read full command
     * @return return task by details
     */
    public static String filterDoByDetails(String line) {
        String taskdoby_detail = "";
        //// check if the statement contains /by
        String pattern = " *.*/by.*";
        boolean isMatch = Pattern.matches(pattern, line);
        if (isMatch) {
            String[] line_arr = line.split("/by");
            taskdoby_detail = line_arr[1];
        }
        return taskdoby_detail;
    }


    /**
     * @param fullCommand read the full command from the user input
     * @return Returns a Todo object to match the fullCommand.
     * @throws TaskManagerException show exception error
     */
    public static Todo addTodo(String fullCommand) throws TaskManagerException {
        String task_detail = filterTaskDetails(fullCommand);
        if (task_detail.isEmpty()) {
            throw new TaskManagerException("Empty description for TODO");
        } else {
            tasks.add(new Todo(task_detail));
            System.out.println("Tasks in the list:" + tasks.size());
            return new Todo(task_detail);
        }
    }


    /**
     * @param fullCommand read the full command from the user input
     * @return Return a Deadline object to match the fullCommand.
     * @throws TaskManagerException show exception error
     */
    public static Deadline addDeadline(String fullCommand) throws TaskManagerException {
        String task_detail = filterTaskDetails(fullCommand); ///omit the first keyword add from the task description.
        String task_detail_by = filterDoByDetails(fullCommand);
        task_detail = task_detail.replace("/by" + task_detail_by, "");
        if (task_detail.isEmpty()) {
            throw new TaskManagerException("Empty description for Deadline");
        } else {
            Deadline d = new Deadline(task_detail, task_detail_by);
            d.setBy(task_detail_by);
            tasks.add(d);
            System.out.println("Tasks in the list:" + tasks.size());
//        System.out.println("<------!!!->:" + tasks);
            return d;
        }
    }

    /**
     *
     * @param fullCommand read the full command from the user input
     * @param d Return a Deadline object after editing deadline details
     */
    protected static void editDeadline(String fullCommand, Deadline d)  {
        String task_detail = filterTaskDetails(fullCommand);
        String task_detail_by = filterDoByDetails(fullCommand);
        task_detail = task_detail.replace("/by" + task_detail_by, "");
        d.setDescription(task_detail);
        d.setBy(task_detail_by);
    }




}
