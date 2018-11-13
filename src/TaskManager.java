import yt.tojava.taskmanager.*;

import java.util.ArrayList;
import java.util.List;

import static javafx.application.Platform.exit;

/**
 * It is the main class named TaskManager to execute the code
 */
public class TaskManager {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * @param filePath read file path from hard disk
     *                 execute to load file or create empty list
     */
    public TaskManager(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        String loadOption = Ui.readFileLoadOptionCommand();
        loadOption = loadOption.toUpperCase();
        System.out.println(loadOption);

        switch (loadOption) {
            case "Y":
                try {
                    tasks = new TaskList(storage.load());
                    System.out.println("Loaded file to task list");
                } catch (java.io.FileNotFoundException e) {
                    Ui.showToUser("Problem reading file. Starting with an empty task list");
                    tasks = new TaskList();
                }
                break;
            case "N":
                Ui.showToUser("Starting with an empty task list");
                List<Task> empList = new ArrayList<>();
                tasks = new TaskList(empList);
                break;
            default:
                Ui.showToUser("Invalid. Please enter option again.");
                // Terminate JVM
                System.exit(0);
        }
        exit();

    }

    /**
     * execute task manager functions
     */
    public void run() {
        Ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = Ui.readUserCommand();
                String commandWord = Parser.getCommandWord(fullCommand);

                switch (commandWord) {
                    case "exit":
                    case "":
                        isExit = true;
                        break;
                    case "todo":
                        tasks.addTask(Parser.addTodo(fullCommand));
                        break;
                    case "deadline":
                        tasks.addTask(Parser.addDeadline(fullCommand));
                        break;
                    case "done":
                        tasks.markAsDone(fullCommand);
                        break;
                    case "notdone":
                        tasks.markAsNotDone(fullCommand);
                        break;
                    case "edit":
                        tasks.markAsEdit(fullCommand);
                        break;
                    case "print":
                        tasks.printTasks();
                        break;
                    case "remove":
                        tasks.removeTask(fullCommand);
                        break;
                    case "save":
                        String pathOption = Ui.askFileSaveCommand();
                        pathOption = pathOption.toUpperCase();
                        System.out.println(pathOption);
                        switch (pathOption) {
                            case "DP":
                                tasks.saveTasks();
                                break;
                            case "NP":
                                String newPath = Ui.getNewPathCommand();
                                System.out.println("New Path:" + newPath);
                                storage.setfilePath(newPath);
                                tasks.saveTasks();
                                break;
                            default:
                                Ui.showToUser("Invalid. Please try it again.");
                                System.exit(0);
                                break;
                        }

                        break;
                    case "clear":
                        tasks.clearTask();
                        break;
                    default:
                        System.out.println("Unknown command! please try again");
                        break;
                }
            } catch (TaskManagerException e1) {
                Ui.printError(e1.getMessage());
            }

        }
        exit();
    }

    public static void main(String[] args) {
        String file_path = "C:\\Users\\user\\Desktop\\CodeYear2S1\\TIC2002SE_JavaCode\\TaskManager\\src\\tasks.txt";
        new TaskManager(file_path).run();
    }

}
