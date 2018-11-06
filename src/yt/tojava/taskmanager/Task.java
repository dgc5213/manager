package yt.tojava.taskmanager;

/**
 * Task class contains task description, task status
 */
public class Task {

    protected String description;
    protected boolean isDone;
    protected String comment;


    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Gets the task description
     *
     * @return task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * change the task status
     *
     * @param done change to done
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     *
     * @return declare is done status
     */
    public boolean isDone() {
        return isDone;
    }




    /**
     * The method does override or implement a method declared in a supertype.
     * The method has a signature that is override-equivalent to that of any public method declared in Object.
     */
    @Override
    public String toString() {
        return "description: " + description;
    }


}