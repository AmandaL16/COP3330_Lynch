import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;


public class App
{
    private static final Scanner sc = new Scanner(System.in);
    public static String filename = "tasks.obj";


    public static void main(String[] args)
    {
        boolean menu = true;
        TaskList toDoList = new TaskList();
        App prompt = new App();

        while (menu)
        {
            toDoList.displayMainMenu();

            int decision = sc.nextInt(); // decision is running through all cases when 8 is hit

            switch (decision)
            {
                case 1:
                    prompt.newTask();
                    continue;
                case 2:
                    try {
                        prompt.loadTask(filename);
                    } catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                    continue;
                case 3:
                    prompt.quit();
                    break;
                default:
                    throw new IllegalStateException("WARNING: input a valid number " + decision + " is not valid!");
            }
        }

    }

    public void taskMenuInput()
    {
        boolean working = true;
        TaskList toDoList = new TaskList();
        App prompt = new App();

        while (working)
        {

            toDoList.displayTaskMenu();

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    //List all tasks
                    prompt.viewTasks();
                    continue;
                case 2:
                    //Add a task
                    prompt.addItem();
                    continue;
                case 3:
                    // deletes the current task and has the user re-input the values
                    prompt.editItem();
                    continue;
                case 4:
                    //Delete a task
                    prompt.removeItem();
                    continue;
                case 5:
                    // mark an item as complete
                    prompt.markAsComplete();
                    continue;
                case 6:
                    // un-mark an item as complete
                    prompt.unmarkAsComplete();
                    continue;
                case 7:
                    // save the current list
                    prompt.saveList(filename);
                    continue;
                case 8:
                    //Exit to main menu
                    prompt.mainMenu();
                    working = false; // exits the switch for the display menu
                    break;
                default:
                    throw new IllegalStateException("WARNING: input a valid number " + choice + " is not valid!");
            }
        }
    }

    // relates to the main menu
    public void newTask()
    {
        taskMenuInput();
    }
    //TODO
    public void loadTask(String filename) throws FileNotFoundException
    {

        sc.nextLine();
        System.out.print("Enter the name of the file to read: ");
        filename = sc.nextLine();

        try
        {

            FileInputStream fileInputStream = new FileInputStream(filename);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            TaskList.list = (ArrayList<TaskItem>)objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();

        } catch (IOException e)
        {
            e.printStackTrace();

        } catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public void quit()
    {
        System.exit(0);
    }

    // relates to tasks menu
    public void viewTasks()
    {
        TaskList toDoList = new TaskList();
        System.out.println("Current Tasks");
        System.out.println("-------------");
        toDoList.printList(); //TODO not printing the curr tasks
    }
    public void addItem()
    {
        TaskList toDoList = new TaskList();
        sc.nextLine();
        System.out.print("Task title: ");
        String taskName = sc.nextLine();
        System.out.print("Task description: ");
        String taskDescription = sc.nextLine();
        System.out.print("Task due date (YYYY-MM-DD): ");
        String taskDate = sc.nextLine();
        String[] datetoArray = taskDate.split("-");
        int taskYear = Integer.parseInt(datetoArray[0]); //TODO this is wrong
        int taskMonth = Integer.parseInt(datetoArray[1]);
        int taskDay = Integer.parseInt(datetoArray[2]);
        Date date = new Date(taskYear, taskMonth, taskDay);


        //Creates a new task using all the values that were input by the user, and the To-Do List's task number to get the taskNumber
        TaskItem newTask = new TaskItem(date, taskName, taskDescription, toDoList.getTaskNumber() + 1);
        //Adds the new task to the list
        toDoList.addTask(newTask);
    }
    public void editItem()
    {
        TaskList toDoList = new TaskList();

        System.out.println("Current Tasks");
        System.out.println("-------------");
        toDoList.printList();

        System.out.print("Which task will you edit?");
        int taskNumber = sc.nextInt();
        // TODO want to keep the task number the same but delete/change all other details; check if right
        toDoList.deleteTask(taskNumber);

        // changing details about the task
        sc.nextLine();
        System.out.print("Task title: ");
        String taskName = sc.nextLine();
        System.out.print("Task description: ");
        String taskDescription = sc.nextLine();
        System.out.print("Task due date (YYYY-MM-DD): ");
        String taskDate = sc.nextLine();
        String[] datetoArray = taskDate.split("-");
        int taskYear = Integer.parseInt(datetoArray[0]);
        int taskMonth = Integer.parseInt(datetoArray[1]);
        int taskDay = Integer.parseInt(datetoArray[2]);
        Date date = new Date(taskYear, taskMonth, taskDay);

        // adding the new edited version to the list
        // TODO check if toDoList.currTaskNumber() is the right way to keep the taskNumber the same
        TaskItem editedTask = new TaskItem(date, taskName, taskDescription, toDoList.currTaskNumber(taskNumber));
        //Adds the new edited task to the list
        toDoList.addTask(editedTask);

    }
    public void removeItem()
    {
        try {
            TaskList toDoList = new TaskList();
            toDoList.printList();
            sc.nextLine();
            System.out.print("Which task will you remove?");

            // TODO does this delete their choice?
            int taskNumber = sc.nextInt();
            toDoList.deleteTask(taskNumber);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void markAsComplete()
    {
        try {
            TaskList toDoList = new TaskList();
            //TaskItem taskStatus = new TaskItem();
            System.out.println("Uncompleted Tasks");
            System.out.println("-------------");
            // TODO this is not right??
            toDoList.printIncompleteTasks();

            System.out.print("Which task will you mark as completed?");
            int taskNumber = sc.nextInt();
            toDoList.completeTask(taskNumber); // TODO Need to fix
            toDoList.printList(); // TODO check if this will only print the list without the ***
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    public void unmarkAsComplete()
    {
        try {
            TaskList toDoList = new TaskList();
            //TaskItem taskStatus = new TaskItem();
            System.out.println("Completed Tasks");
            System.out.println("-------------");

            toDoList.printCompletedTasks();
            System.out.print("Which task will you un-mark as completed?");
            int taskNumber = sc.nextInt();
            toDoList.incompleteTask(taskNumber); // TODO this is causing issues
            //taskStatus.markIncomplete();// should remove *** from task
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    //TODO
    public void saveList(String filename)
    {
        try {

            sc.nextLine();
            System.out.print("Enter the name of the file to save: ");
            filename = sc.nextLine();

            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(TaskList.list);

            objectOutputStream.close();
            fileOutputStream.close();

            System.out.println("\nFile saved!\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mainMenu()
    {
        // left blank on purpose
    }
}
