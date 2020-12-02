import java.util.InputMismatchException;
import java.util.Scanner;

public class TaskApp
{
    private static final Scanner sc = new Scanner(System.in);
    private TaskList taskList;

    public static void main(String[] args)
    {
        TaskApp app = new TaskApp();
        app.selectApp();
    }

    public void selectApp()
    {
        String mainMenuChoice;
        ContactApp contact = new ContactApp();

        while (true)
        {
            TaskList.displaySelectionMenu();
            mainMenuChoice = getMenuChoice();

            if (mainMenuChoice.contains("1"))
            {
                mainMenu();
            }
            else if (mainMenuChoice.contains("2"))
            {
                contact.contactMainMenu();
            }
            else if (mainMenuChoice.contains("3"))
            {
                System.out.println();
                System.out.println("Thank you for using the program!");
                break;
            }
            else
            {
                System.out.println("WARNING: that is not a valid menu option.");
                System.out.println("returning to Application selection menu.");
                System.out.println();
            }
        }
    }

    public void mainMenu()
    {
        String mainMenuChoice;
        while (true) {
            TaskList.displayMainMenu();
            mainMenuChoice = getMenuChoice();

            if (mainMenuChoice.contains("1"))
            {
                taskList = new TaskList();
                System.out.println("A new list has been created");
                taskMenuInput();

            }
            else if (mainMenuChoice.contains("2"))
            {
                try {
                    loadList();
                    taskMenuInput();

                } catch (IllegalArgumentException | InputMismatchException e)
                {
                    System.out.println(e.getMessage());
                }

            }
            else if (mainMenuChoice.contains("3"))
            {
                System.out.println();
                System.out.println("Thank you for using the ToDo List program!");
                System.out.println("returning to Application selection menu.");
                System.out.println();
                break;
            }
            else
            {
                System.out.println("WARNING: that is not a valid menu option.");
                System.out.println("returning to main menu.\n");
            }
        }
    }


    private String getMenuChoice() {
        System.out.print(">>> ");
        return sc.nextLine();
    }
    private void loadList()
    {
        System.out.println("Enter the filename to load: ");
        String filename = sc.nextLine();
        taskList.load(filename);

        System.out.println("The file has been loaded");
    }

    private void taskMenuInput()
    {
        String taskMenuChoice;
        while (true) {
            TaskList.displayOperationMenu();
            taskMenuChoice = getMenuChoice();
            if (taskMenuChoice.contains("1"))
            {
                displayItems();

            } else if (taskMenuChoice.contains("2")) {
                addItem();
            } else if (taskMenuChoice.contains("3")) {
                if (taskList.size() > 0) {
                    editItem();
                } else {
                    System.out.println("ERROR: No tasks to edit.");
                    System.out.println("Try adding a task first.");
                }
            } else if (taskMenuChoice.contains("4")) {
                if (taskList.size() > 0) {
                    removeItem();
                } else {
                    System.out.println("There are no tasks to remove :(");
                    System.out.println("Try adding a task first.");
                }
            } else if (taskMenuChoice.contains("5")) {
                if (taskList.size() > 0) {
                    markItem();
                } else {
                    System.out.println("There are no tasks to mark :(");
                    System.out.println("Try adding a task first.");
                }
            } else if (taskMenuChoice.contains("6")) {
                if (taskList.size() > 0)
                {
                    unmarkItem();
                }
                else
                {
                    System.out.println("No tasks to unmark.");
                    System.out.println("Try adding a task first.");
                }
            } else if (taskMenuChoice.contains("7")) {
                if (taskList.size() > 0) {
                    saveItems();
                } else {
                    System.out.println("There are no tasks to save :(");
                    System.out.println("Try adding a task first.");
                }
            } else if (taskMenuChoice.contains("8")) {
                break;
            } else {
                System.out.println("Invalid menu choice.");
            }
        }

    }


    private void displayItems()
    {
        if (taskList.size() > 0)
        {
            System.out.println("Current Tasks");
            System.out.println("-------------");
            System.out.println();
            System.out.println(taskList.view());
            System.out.println();
        }
        else
        {
            System.out.println("Current Tasks");
            System.out.println("-------------");
            System.out.println();
            System.out.println("There are no current tasks");
            System.out.println();
        }
    }

    private void addItem() {
        System.out.print("Task title: ");
        String title = sc.nextLine();
        System.out.print("Task description: ");
        String description = sc.nextLine();
        System.out.print("Task due date (YYYY-MM-DD): ");
        String dueDate = sc.nextLine();

        try
        {
            taskList.add(new TaskItem(title, description, dueDate));
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void editItem()
    {
        displayItems();

        System.out.print("Which task will you edit?");
        int index = sc.nextInt();
        sc.nextLine();

        if (index < taskList.size()) {
            System.out.printf("Enter a new title for task %d: ", index);
            String title = sc.nextLine();
            System.out.printf("Enter a new task description for task %d: ", index);
            String description = sc.nextLine();
            System.out.printf("Enter a new task due date (YYYY-MM-DD) for task %d: ", index);
            String dueDate = sc.nextLine();

            try {
                taskList.makeChanges(index, title, description, dueDate);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        else
        {
            System.out.println("WARNING: Invalid task number.");
        }
    }

    private void removeItem()
    {
        displayItems();

        System.out.print("Which task will you remove?");
        int index = sc.nextInt();
        sc.nextLine();

        if (index < taskList.size())
        {
            taskList.remove(index);
        }
        else
        {
            System.out.println("WARNING: Invalid task number.");
        }
    }

    private void markItem()
    {
        System.out.println("Uncompleted Tasks");
        System.out.println("-----------------");
        System.out.println();
        System.out.println(taskList.viewUncompletedTasks());

        System.out.print("Which task will you mark as complete?");
        int index = sc.nextInt();

        if(index > taskList.size())
        {
            System.out.println("WARNING: invalid task number");
        }
        else if(taskList.isTaskComplete(index))
        {
            System.out.println("WARNING: task is already completed");
        }
        else
        {
            taskList.complete(index, true);
        }

    }

    private void unmarkItem()
    {
        System.out.println("Completed Tasks");
        System.out.println("---------------");
        System.out.println();
        System.out.println(taskList.viewCompletedTasks());

        System.out.print("Which task will you unmark as complete?");
        int index = sc.nextInt();


        if(index > taskList.size())
        {
            System.out.println("WARNING: invalid task number");
        }
        else if(!taskList.isTaskComplete(index))
        {
            System.out.println("WARNING: task is already incomplete");
        }
        else
        {
            taskList.complete(index, false);
        }

    }

    private void saveItems()
    {
        if (taskList.size() > 0)
        {
            System.out.println("Enter the filename to save as: ");
            String filename = sc.nextLine();
            taskList.save(filename);

            System.out.println("The task list has been saved!");
        }
        else
        {
            System.out.println("ERROR: no task list to save.");
        }
    }

}
