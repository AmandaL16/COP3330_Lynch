import java.util.ArrayList;


public class TaskList
{
    public static ArrayList<TaskItem> list = new ArrayList();

    public TaskList()
    {
        // left blank on purpose
    }

    public static void addTask(TaskItem task)
    {
        list.add(task);
    }

    public void deleteTask(int taskNumber)
    {
        //For each task in the task list, check if the num given is equal to the task's num. If it is, remove that task from the list
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getTaskNumber() == taskNumber)
            {
                list.remove(i);
                break;
            }
        }
    }

    public void completeTask(int taskNumber)
    {
        //For each task in the task list, check if the num given is equal to the task's num.
        for (int i = 0; i < list.size(); i++)
        {
            TaskItem validate = new TaskItem();

            if (list.get(i).getTaskNumber() == taskNumber)
            {
                validate.markCompleted();
                break;
            }
        }
    }
    public void incompleteTask(int taskNumber)
    {
        //For each task in the task list, check if the num given is equal to the task's num.
        for (int i = 0; i < list.size(); i++)
        {
            TaskItem validate = new TaskItem();

            if (list.get(i).getTaskNumber() == taskNumber)
            {
                validate.markIncomplete();
                break;
            }
        }
    }

    public int currTaskNumber(int taskNumber)
    {
        //For each task in the task list, check if the num given is equal to the task's num. If it is, keeps the number the same
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getTaskNumber() == taskNumber)
            {
                return taskNumber;
            }
        }
        return taskNumber; // returns the current task number
    }

    // prints tasks that are marked as completed
    public void printCompletedTasks()
    {
        for(int i = 0; i < list.size(); i++)
        {
            TaskItem temp = list.get(i);

            if(temp.isComplete())
            {
                temp.printTask();
            }
        }
    }

    // prints tasks that arent marked as completed
    public void printIncompleteTasks()
    {
        for(int i = 0; i < list.size(); i++)
        {
            TaskItem temp = list.get(i);

            if(!temp.isComplete())
            {
                temp.printTask();
            }
        }
    }

    public int getTaskNumber()
    {
        return list.size();
    }

    public void printList()
    {

        for (int i = 0; i < list.size(); i++)
        {
            list.get(i).printTask();
        }
    }

    public static void displayMainMenu()
    {
        System.out.println("\nMain Menu");
        System.out.println("---------");
        System.out.println("1) create a new list");
        System.out.println("2) load an existing list");
        System.out.println("3) quit");
        System.out.println();
    }

    public static void displayTaskMenu()
    {
        System.out.println("\n\nList Operation Menu");
        System.out.println();
        System.out.println("---------");
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
}
