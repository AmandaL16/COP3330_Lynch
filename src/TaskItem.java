import java.time.LocalDate;

public class TaskItem
{
    private String title;
    private String description;
    private LocalDate dueDate;
    private boolean completed;


    public String getTitle()
    {
        return title;
    }
    public String getDescription()
    {
        return description;
    }
    public LocalDate getDueDate()
    {
        return dueDate;
    }
    public boolean isComplete()
    {
        return completed;
    }
    public void complete(boolean completed)
    {
        this.completed = completed;
    }

    public TaskItem(String title, String description, String dueDate)
    {
        if(title.isBlank())
        {
            throw new IllegalArgumentException("WARNING: task title must be 1 character long!");
        }

        LocalDate d;
        try
        {
            d = LocalDate.parse(dueDate);
        }
        catch(java.time.format.DateTimeParseException e)
        {
            throw new IllegalArgumentException("WARNING: invalid due date.");
        }

        this.title = title;
        this.description = description;
        this.dueDate = d;
        this.completed = false;
    }

    public void makeChanges(String title, String description, String dueDate)
    {
        if(title.isBlank())
        {
            throw new IllegalArgumentException("WARNING: task title must be 1 character long!");
        }

        LocalDate d;
        try
        {
            d = LocalDate.parse(dueDate);
        }
        catch(java.time.format.DateTimeParseException e)
        {
            throw new IllegalArgumentException("WARNING: invalid due date. Task not updated");
        }

        this.title = title;
        this.description = description;
        this.dueDate = d;
        this.completed = false;
    }

    @Override
    public String toString()
    {
        return String.format("[%s] %s: %s", dueDate, title, description);
    }

}
