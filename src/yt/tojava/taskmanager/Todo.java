package yt.tojava.taskmanager;

/**
 * ToDo : a task to do someday
 * Todo class inherit from Task class
 */
public class Todo extends Task {
    //protected boolean isDone;

    /**
     * @param description description of todo task
     */
    public Todo(String description) {
        super(description);
        //isDone = false;
    }
    public void setDescription(String description) {        this.description = description;
    }

//    public void setDone(boolean done) {
//        isDone = done;
//    }

//    public boolean isDone() {
//        return isDone;
//    }

    /**
     * The method does override or implement a method declared in a supertype.
     * The method has a signature that is override-equivalent to that of any public method declared in Object.
     * to overwrite task status
     */

    @Override
    public String toString() {
        String status = null;
        if (isDone) {
            status = "Yes";
        } else {
            status = "No";
        }
        return super.toString() + System.lineSeparator() + "is done? " + status;
    }
}
