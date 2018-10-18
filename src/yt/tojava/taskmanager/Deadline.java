package yt.tojava.taskmanager;

/**
 * Represents a  deadline task that to be done by a specific date/time.
 * The deadline task is one kind of the task
 */
public class Deadline extends Todo {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Changes the deadline description of the task.
     * This may involve a lengthy legal process.
     *
     * @param by This deadline description (BY)
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * Gets the deadline description of the task.
     *
     * @return This deadline description (BY)
     */
    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() + "do by: " + by;
    }
}