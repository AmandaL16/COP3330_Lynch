import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class ContactList
{
    private List<ContactItem> contacts;

    public ContactList()
    {
        this.contacts = new ArrayList<>();
    }
    public int size()
    {
        return contacts.size();
    }
    public void add(ContactItem contact)
    {
        contacts.add(contact);
    }
    private ContactItem get(int index)
    {
        return contacts.get(index);
    }
    public void remove(int index)
    {
        contacts.remove(index);
    }

    public static void displayContactOperationMenu()
    {
        System.out.println("\nList Operation Menu");
        System.out.println("-------------------");
        System.out.println("1) view the list");
        System.out.println("2) add an item");
        System.out.println("3) edit an item");
        System.out.println("4) remove an item");
        System.out.println("5) save the current list");
        System.out.println("6) quit to the main menu");
        System.out.println();
    }

    public static void displayContactMainMenu()
    {
        System.out.println();
        System.out.println("Main Menu");
        System.out.println("---------");
        System.out.println("1) create a new list");
        System.out.println("2) load an existing list");
        System.out.println("3) quit");
        System.out.println();
    }

    public String view()
    {
        StringBuilder sb = new StringBuilder();
        ContactItem contact;
        for(int x = 0; x < this.size(); x++)
        {
            contact = this.get(x);

            sb.append(String.format("%d) %s%n", x, contact));
        }

        return sb.toString();
    }


    public void makeChanges(int index, String firstName, String lastName, String phoneNumber, String email)
    {
        this.get(index).makeChanges(firstName, lastName, phoneNumber, email);
    }

    public void save(String filename)
    {
        try(Formatter output = new Formatter(filename))
        {
            output.format("contacts%n");
            for (ContactItem contact : contacts)
            {
                output.format("%s%n", contact.getFirstName());
                output.format("%s%n", contact.getLastName());
                output.format("%s%n", contact.getPhoneNumber());
                output.format("%s%n", contact.getEmail());

            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public void load(String filename)
    {
        List<ContactItem> backupList = contacts;

        contacts = new ArrayList<>();
        try(Scanner input = new Scanner(Paths.get(filename)))
        {
            String header = input.nextLine();
            if(header.equalsIgnoreCase("contacts"))
            {
                while(input.hasNext())
                {
                    String firstName = input.nextLine();
                    String lastName = input.nextLine();
                    String phoneNumber = input.nextLine();
                    String email = input.nextLine();

                    ContactItem contact = new ContactItem(firstName, lastName, phoneNumber, email);
                    this.add(contact);
                }
            }
            else
            {
                contacts = backupList;
                throw new InputMismatchException("WARNING: file name does not exist.");
            }
        }
        catch(FileNotFoundException e)
        {
            contacts = backupList;
            throw new IllegalArgumentException("WARNING: file was not found.");
        }
        catch (IOException e)
        {
            contacts = backupList;
            throw new IllegalArgumentException("WARNING: could not load contact data.");
        }

    }
    public String getContactFirstName(int index)
    {
        return this.get(index).getFirstName();
    }
    public String getContactLastName(int index)
    {
        return this.get(index).getLastName();
    }
    public String getContactPhoneNumber(int index)
    {
        return this.get(index).getPhoneNumber();
    }
    public String getContactEmail(int index)
    {
        return this.get(index).getEmail();
    }
    public String getTaskText(int index)
    {
        return this.get(index).toString();
    }
}
