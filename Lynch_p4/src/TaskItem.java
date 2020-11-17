import java.io.Serializable;

public class TaskItem implements Serializable
{
    private Date date; // TODO find a way to combine into this class
    private String description;
    private final int taskNumber;
    private String taskName;
    private boolean complete;


    //Default constructor
    public TaskItem()
    {
        this(null,null,null, 0);
    }
    public TaskItem(Date date, String taskName, String description, int taskNumber)
    {
        this.complete = false;
        this.date = date;
        this.description = description;
        this.setTaskName(taskName);
        this.taskNumber = taskNumber;
    }

    public Date getDate() {
        return this.date;
    }
    public String getDescription() {
        return this.description;
    }
    public String getTaskName()
    {
        return this.taskName;
    }

    //TODO doesnt throw exception
    public void setTaskName(String taskName) throws NullPointerException
    {
        //try
        //{
        if (!(taskName.trim().equals("") || taskName == null))
        {
            this.taskName = taskName.trim();
        }
        //}
        //catch (Exception e)
        //{
        System.out.println("WARNING: title must be at least 1 character long; task not created");
        //  e.printStackTrace();
        //}

    }
    public int getTaskNumber()
    {
        return this.taskNumber;
    }
    public void setDate(Date date)
    {
        this.date = date;
    }
    public void setDescription(String description)
    {
        // description can be of length 0
        this.description = description.trim();
    }


    // Prints out the current task in the format of “Task number) [due date] name: description”
    public void printTask()
    {
        System.out.println((complete?"***":"") + this.taskNumber + ")" + "[" + this.date.toString() + "] " + this.taskName +": " + this.description);
    }

    // will return true if the task is marked as completed, otherwise it will return false
    public boolean isComplete()
    {
        return this.complete;
    }
    public boolean markIncomplete()
    {
        this.complete = false;
        return this.complete;
    }
    public boolean markCompleted()
    {
        this.complete = true;
        return this.complete;
    }

}
