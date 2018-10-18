import  yt.tojava.taskmanager.*;
import java.io.FileNotFoundException;
import static javafx.application.Platform.exit;

public class TaskManager {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;



    public TaskManager(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            System.out.println("Loaded file to task list");
        } catch (FileNotFoundException e) {
            ui.showToUser("Problem reading file. Starting with an empty task list");
            tasks = new TaskList();
        }
    }

    public void run() throws TaskManagerException {
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
                        tasks.addTask(Parser.addDeadline( fullCommand));
                        break;
                    case "done":
                        tasks.markAsDone(fullCommand);
                        break;
                    case"print":
                        tasks.printTasks();
                        break;
                    case"save":
                        tasks.saveTasks();
                        break;
                    default:
                        System.out.println("Unknown command! please try again");
                        break;
                }
            }
            catch (TaskManagerException e1) {
             Ui.printError(e1.getMessage());

            }

        }
        exit();
    }

    public static void main(String[] args) throws TaskManagerException {

        String file_path = "C:\\Users\\user\\Desktop\\CodeYear2S1\\TIC2002SE_JavaCode\\TaskManager\\src\\tasks.txt";
        new TaskManager(file_path).run();
    }

}
