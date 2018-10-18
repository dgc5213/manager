package yt.tojava.taskmanager;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static String file_path;

    public Storage(String filePath) {
        file_path = filePath;
    }

    /*  Get Tasks From txt File*/
    public List<Task> load() throws FileNotFoundException {
        List<Task> loadedTasks = new ArrayList<>();
        try {
            List<String> lines = getLines(file_path);
//            System.out.println(lines);
            for (String line : lines) {
                if (line.trim().isEmpty()) { //ignore empty lines
                    continue;
                }
//                System.out.println(line);
                loadedTasks.add(createTask(line));
            }
        } catch (FileNotFoundException e) {
            printError_file("problem encountered while loading data: " + e.getMessage());
        }
//        System.out.println("Storage<-----------loadedTasks0----------->:" + loadedTasks.get(0));
//        System.out.println("Storage<-----------loadedTasks1----------->:" + loadedTasks.get(1));
//        System.out.println("Storage<-----------loadedTasks2----------->:" + loadedTasks.get(2));
//        System.out.println("Storage<-----------loadedTasks3----------->:" + loadedTasks.get(3));
        return loadedTasks;
    }


    protected static void saveToDisk(List<Task> tasks) {

        File out = new File(file_path);
        if (out.exists()) {
            out.delete();
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(out))) {
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i) instanceof Deadline) {
                    bw.append("D ");
                    bw.append("| ").append(tasks.get(i).isDone() ? "1" : "0").append(" |").append(tasks.get(i).getDescription()).append("|").append(((Deadline) tasks.get(i)).getBy());
                } else if (tasks.get(i) instanceof Todo) {
                    bw.append("T ");
                    bw.append("| ").append(tasks.get(i).isDone() ? "1" : "0").append(" |").append(tasks.get(i).getDescription());

                }
                bw.append("\n");
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Task save to disk data/tasks.txt");
    }


    private static List<String> getLines(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()) {
            list.add(s.nextLine());

        }
        s.close();
        return list;
    }


    private static Task createTask(String line) {
        String TaskType = null;
        String TaskDetail;
        String TaskDeadlineDetail = null;
        boolean TaskDone = false;
        String[] line_arr = line.split("\\|");

        /* get the value of TaskDetail & TaskDeadlineDetail */
        TaskDetail = line_arr[2];
        // System.out.println("TaskDetail:"+TaskDetail);
        if (line_arr.length > 3) {
            TaskDeadlineDetail = line_arr[3];
            //   System.out.println("TaskDeadlineDetail:"+TaskDeadlineDetail);
        }

        Task t = new Task("hello-default");
        /* pass the value to Task:  todo / deadline */
        if (line_arr[0].equals("D ")) {
            TaskType = "deadline";
            if (line_arr[1].equals(" 1 ")) {
                TaskDone = true;
            } else if (line_arr[1].equals(" 0 ")) {
                TaskDone = false;
            }
            Deadline d = new Deadline(TaskDetail, TaskDeadlineDetail);
            d.setBy(TaskDeadlineDetail);
            t = d;
            t.setDone(TaskDone);
        } else if (line_arr[0].equals("T ")) {
            TaskType = "todo";
            if (line_arr[1].equals(" 1 ")) {
                TaskDone = true;
            } else if (line_arr[1].equals(" 0 ")) {
                TaskDone = false;
            }
            Todo td = new Todo(TaskDetail);
            t = td;
            t.setDone(TaskDone);
        }
        return t;
    }

    private static void printError_file(String message) throws FileNotFoundException {
        System.out.println(message);
    }



}
