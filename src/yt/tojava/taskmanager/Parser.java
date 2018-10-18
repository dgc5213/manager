package yt.tojava.taskmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Parser {

    protected static String fullCommand;

    protected static List<Task> tasks = new ArrayList<>();


    public Parser(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public static String getCommandWord(String fullCommand) {
        fullCommand = fullCommand.trim();  //It returns a copy of this string with leading and trailing white space removed, or this string if it has no leading or trailing white space.
//        System.out.println("----PARSER----getCommandWord--fullCommand:" + fullCommand);
        String command = fullCommand.split(" ")[0];//extract the first word of the user input
//        System.out.println("----PARSER----getCommandWord------firstword:" + command);
        return command;
    }

    private static void exit() {
        System.out.println("Bye!");
        boolean isExit = true;
    }


    private static String filterTaskDetails(String line) {
        String task_detail = "";
        int len = line.split(" ").length; //// to check who many words
        String[] line_arr = line.split(" ", 2);
        if (len > 1) {
            task_detail = line_arr[1];  // filter out the first words
        }
        return task_detail;
    }

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

    public static Deadline addDeadline(String fullCommand) throws TaskManagerException {
        String task_detail = filterTaskDetails(fullCommand); ///omit the word add from the task description.
        String task_detail_by = filterDoByDetails(fullCommand);
        task_detail = task_detail.replace("/by" + task_detail_by, "");
        Deadline d = new Deadline(task_detail, task_detail_by);
        d.setBy(task_detail_by);
        tasks.add(d);
        System.out.println("Tasks in the list:" + tasks.size());
//        System.out.println("<------!!!->:" + tasks);
        return d;
    }



//    private static void markAsDone(String fullCommand) {
//        int index = Integer.parseInt(fullCommand.substring("done".length()).trim());
//        tasks.get(index - 1).setDone(true);
//        System.out.println("Tasks in the list: " + tasks.size());
//        System.out.println("--->Task check true:" + tasks);
//    }




}
