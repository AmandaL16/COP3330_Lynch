import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class TaskList
{
    private List<TaskItem> items;

    public static void displaySelectionMenu()
    {
        System.out.println("Select Your Application");
        System.out.println("-----------------------");
        System.out.println("1) task list");
        System.out.println("2) contact list");
        System.out.println("3) quit");
        System.out.println();
    }

    public static void displayMainMenu()
    {
        System.out.println();
        System.out.println("Main Menu");
        System.out.println("---------");
        System.out.println("1) create a new list");
        System.out.println("2) load an existing list");
        System.out.println("3) quit");
        System.out.println();
    }
    public static void displayOperationMenu()
    {
        System.out.println("\nList Operation Menu");
        System.out.println("-------------------");
        System.out.println("1) view the list");
        System.out.println("2) add an item");
        System.out.println("3) edit an item");
        System.out.println("4) remove an item");
        System.out.println("5) mark an item as completed");
        System.out.println("6) un-mark an item as completed");
        System.out.println("7) save the current list");
        System.out.println("8) quit to the main menu");
        System.out.println();
    }

    public TaskList()
    {
        this.items = new ArrayList<>();
    }
    public int size()
    {
        return items.size();
    }
    public void add(TaskItem item)
    {
        items.add(item);
    }
    private TaskItem get(int index)
    {
        return items.get(index);
    }
    public void remove(int index)
    {
        items.remove(index);
    }


    public String view()
    {
        StringBuilder sb = new StringBuilder();
        TaskItem item;
        for(int i = 0; i < this.size(); i++)
        {
            item = this.get(i);
            if (item.isComplete())
            {
                sb.append(String.format("%d) *** %s%n", i, item));
            }
            else if (!item.isComplete())
            {
                sb.append(String.format("%d) %s%n", i, item));
            }
        }

        return sb.toString();
    }

    public String viewCompletedTasks()
    {
        StringBuilder sb = new StringBuilder();
        TaskItem item;
        for(int i = 0; i < this.size(); i++)
        {
            item = this.get(i);
            if (item.isComplete())
            {
                sb.append(String.format("%d) %s%n", i, item));
            }
        }

        return sb.toString();
    }

    public String viewUncompletedTasks()
    {
        StringBuilder sb = new StringBuilder();
        TaskItem item;
        for(int i = 0; i < this.size(); i++)
        {
            item = this.get(i);
            if (!item.isComplete())
            {
                sb.append(String.format("%d) %s%n", i, item));
            }
        }

        return sb.toString();
    }

    public void makeChanges(int index, String title, String description, String dueDate)
    {
        this.get(index).makeChanges(title, description, dueDate);
    }
    public void complete(int index, boolean completed)
    {
        this.get(index).complete(completed);
    }
    public void save(String filename)
    {
        try(Formatter output = new Formatter(filename))
        {
            output.format("tasks%n");
            for (TaskItem item : items)
            {
                output.format("%s%n", item.getDueDate());
                output.format("%s%n", item.getTitle());
                output.format("%s%n", item.getDescription());
                if (item.isComplete())
                {
                    output.format("complete%n");
                }
                else
                {
                    output.format("incomplete%n");
                }
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void load(String filename)
    {
        List<TaskItem> backupList = items;

        items = new ArrayList<>();
        try(Scanner input = new Scanner(Paths.get(filename)))
        {
            String header = input.nextLine();
            if(header.equalsIgnoreCase("tasks"))
            {
                while(input.hasNext())
                {
                    String dueDate = input.nextLine();
                    String title = input.nextLine();
                    String description = input.nextLine();
                    String complete = input.nextLine();

                    TaskItem item = new TaskItem(title, description, dueDate);
                    item.complete(complete.equalsIgnoreCase("complete"));
                    this.add(item);
                }
            }
            else
            {
                items = backupList;
                throw new InputMismatchException("WARNING: file name does not exist.");
            }
        }
        catch(FileNotFoundException e)
        {
            items = backupList;
            throw new IllegalArgumentException("WARNING: file was not found.");
        }
        catch (IOException e)
        {
            items = backupList;
            throw new IllegalArgumentException("WARNING: could not load task data.");
        }

    }
    public String getTaskTitle(int index)
    {
        return this.get(index).getTitle();
    }
    public String getTaskDescription(int index)
    {
        return this.get(index).getDescription();
    }
    public String getTaskDueDate(int index)
    {
        return this.get(index).getDueDate().toString();
    }
    public boolean isTaskComplete(int index)
    {
        return this.get(index).isComplete();
    }
    public String getTaskText(int index)
    {
        return this.get(index).toString();
    }

}
