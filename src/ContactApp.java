import java.util.InputMismatchException;
import java.util.Scanner;

public class ContactApp
{
    private static final Scanner sc = new Scanner(System.in);
    private ContactList contactList;

    public static void main(String[] args)
    {
        TaskApp app = new TaskApp();
        app.selectApp();
    }

    public void contactMainMenu()
    {
        String mainMenuChoice;
        while (true) {
            ContactList.displayContactMainMenu();
            mainMenuChoice = getMenuChoice();

            if (mainMenuChoice.contains("1"))
            {
                contactList = new ContactList();
                System.out.println("A new list has been created");
                contactMenuInput();

            }
            else if (mainMenuChoice.contains("2"))
            {
                try
                {
                    loadContactList();
                    contactMenuInput();

                } catch (IllegalArgumentException | InputMismatchException e)
                {
                    System.out.println(e.getMessage());
                }

            }
            else if (mainMenuChoice.contains("3"))
            {
                System.out.println();
                System.out.println("Thank you for using the Contact List program!");
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

    private void loadContactList()
    {
        System.out.println("Enter the filename to load: ");
        String filename = sc.nextLine();
        contactList.load(filename);

        System.out.println("The file has been loaded");
    }

    private void contactMenuInput()
    {
        String taskMenuChoice;
        while (true) {
            ContactList.displayContactOperationMenu();
            taskMenuChoice = getMenuChoice();
            if (taskMenuChoice.contains("1"))
            {
                displayContacts();

            } else if (taskMenuChoice.contains("2"))
            {
                addContact();
            }
            else if (taskMenuChoice.contains("3"))
            {
                if (contactList.size() > 0)
                {
                    editContact();
                } else {
                    System.out.println("ERROR: No tasks to edit.");
                    System.out.println("Try adding a task first.");
                }
            }
            else if (taskMenuChoice.contains("4"))
            {
                if (contactList.size() > 0) {
                    removeContact();
                } else {
                    System.out.println("There are no tasks to remove :(");
                    System.out.println("Try adding a task first.");
                }
            }
            else if (taskMenuChoice.contains("5"))
            {
                if (contactList.size() > 0) {
                    saveContacts();
                } else {
                    System.out.println("There are no tasks to save :(");
                    System.out.println("Try adding a task first.");
                }
            } else if (taskMenuChoice.contains("6")) {
                break;
            } else {
                System.out.println("Invalid menu choice.");
            }
        }

    }


    private String getMenuChoice()
    {
        System.out.print(">>> ");
        return sc.nextLine();
    }

    private void displayContacts()
    {
        if (contactList.size() > 0) {
            System.out.println("Current Tasks");
            System.out.println("-------------");
            System.out.println();
            System.out.println(contactList.view());
            System.out.println();
        }
        else
        {
            System.out.println("Current Tasks");
            System.out.println("-------------");
            System.out.println();
            System.out.println("There are no current contacts");
            System.out.println();
        }
    }

    private void addContact() {
        System.out.print("First name: ");
        String firstName = sc.nextLine();
        System.out.print("Last name: ");
        String lastName = sc.nextLine();
        System.out.print("Phone number (xxx-xxx-xxxx): ");
        String phoneNumber = sc.nextLine();
        System.out.print("Email address (x@y.z): ");
        String email = sc.nextLine();

        try
        {
            contactList.add(new ContactItem(firstName, lastName, phoneNumber, email));
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void editContact()
    {
        displayContacts();

        System.out.print("Which task will you edit?");
        int index = sc.nextInt();
        sc.nextLine();

        if (index < contactList.size()) {
            System.out.printf("Enter a new first name for task %d: ", index);
            String firstName = sc.nextLine();
            System.out.printf("Enter a new last name for task %d: ", index);
            String lastName = sc.nextLine();
            System.out.printf("Enter a new phone number (xxx-xxx-xxxx) for task %d: ", index);
            String phoneNumber = sc.nextLine();
            System.out.printf("Enter a new email address (x@y.z) for task %d: ", index);
            String email = sc.nextLine();

            try {
                contactList.makeChanges(index, firstName, lastName, phoneNumber, email);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        else
        {
            System.out.println("WARNING: Invalid task number.");
        }
    }

    private void removeContact()
    {
        displayContacts();

        System.out.print("Which task will you remove?");
        int index = sc.nextInt();
        sc.nextLine();

        if (index < contactList.size())
        {
            contactList.remove(index);
        }
        else
        {
            System.out.println("WARNING: Invalid task number.");
        }
    }

    private void saveContacts()
    {
        if (contactList.size() > 0)
        {
            System.out.println("Enter the filename to save as: ");
            String filename = sc.nextLine();
            contactList.save(filename);

            System.out.println("The task list has been saved!");
        }
        else
        {
            System.out.println("ERROR: no task list to save.");
        }
    }

}
