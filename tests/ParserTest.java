import org.junit.Test;
import yt.tojava.taskmanager.Deadline;
import yt.tojava.taskmanager.Parser;
import yt.tojava.taskmanager.TaskManagerException;
import yt.tojava.taskmanager.Todo;

import static org.junit.Assert.assertEquals;

public class ParserTest {

    @Test
    public void getCommandWord() {
        assertEquals("todo", Parser.getCommandWord("todo read book"));
        assertEquals("deadline", Parser.getCommandWord("deadline return book /by next Friday"));
        assertEquals("exit", Parser.getCommandWord("exit"));
        assertEquals("xyz", Parser.getCommandWord(" xyz ")); // leading and trailing spaces
    }

    @Test
    public void filterTaskDetails() {
        String task_detail = Parser.filterDoByDetails("deadline write report /by deadline write report");
        String expected = " deadline write report";
        assertEquals(expected, task_detail);
    }

    @Test
    public void filterDoByDetails() {
        String task_detail_by = Parser.filterDoByDetails("deadline write report /by deadline write report");
        String expected = " deadline write report";
        assertEquals(expected, task_detail_by);
    }

    @Test
    public void addTodo() throws TaskManagerException {
        Todo actual = Parser.addTodo("todo read book");
        Todo expected = new Todo("read book");
        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void addDeadline() throws TaskManagerException {
        Todo actual = Parser.addDeadline("deadline write report /by deadline write report");
        Deadline expected = new Deadline("write report ", " deadline write report");
        assertEquals(expected.toString(), actual.toString());

    }


}