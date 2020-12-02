import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactListTest
{
    @Test
    public void addingItemsIncreasesSize()
    {
        ContactItem newContact = new ContactItem("dan", "smith", "123-123-1234", "dan12@gmail.com");

        ContactList newList = new ContactList();
        newList.add(newContact);

        assertEquals(1, newList.size());
    }
    @Test
    public void editingItemsFailsWithAllBlankValues()
    {
        ContactItem newContact = new ContactItem("dan", "smith", "123-123-1234", "dan12@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> newContact.makeChanges("", "", "", ""));

        assertEquals("dan", newContact.getFirstName());
        assertEquals("smith", newContact.getLastName());
        assertEquals("123-123-1234", newContact.getPhoneNumber());
        assertEquals("dan12@gmail.com", newContact.getEmail());
    }
    @Test
    public void editingItemsFailsWithInvalidIndex()
    {
        ContactItem newContact = new ContactItem("dan", "smith", "123-123-1234", "dan12@gmail.com");

        ContactList newList = new ContactList();
        newList.add(newContact);

        assertThrows(IndexOutOfBoundsException.class, () -> newList.makeChanges(1, "Ava", "smith", "123-125-1256", "AvaS@smithmail.com"));
    }
    @Test
    public void editingSucceedsWithBlankFirstName()
    {
        ContactItem newContact = new ContactItem("dan", "smith", "123-123-1234", "dan12@gmail.com");

        newContact.makeChanges("", newContact.getLastName(), newContact.getPhoneNumber(), newContact.getEmail());

        assertEquals("", newContact.getFirstName());
        assertEquals("smith", newContact.getLastName());
        assertEquals("123-123-1234", newContact.getPhoneNumber());
        assertEquals("dan12@gmail.com", newContact.getEmail());
    }
    @Test
    public void editingSucceedsWithBlankLastName()
    {
        ContactItem newContact = new ContactItem("dan", "smith", "123-123-1234", "dan12@gmail.com");

        newContact.makeChanges(newContact.getFirstName(), "", newContact.getPhoneNumber(), newContact.getEmail());

        assertEquals("dan", newContact.getFirstName());
        assertEquals("", newContact.getLastName());
        assertEquals("123-123-1234", newContact.getPhoneNumber());
        assertEquals("dan12@gmail.com", newContact.getEmail());
    }
    @Test
    public void editingSucceedsWithBlankPhone()
    {
        ContactItem newContact = new ContactItem("dan", "smith", "123-123-1234", "dan12@gmail.com");

        newContact.makeChanges(newContact.getFirstName(), newContact.getLastName(), "", newContact.getEmail());

        assertEquals("dan", newContact.getFirstName());
        assertEquals("smith", newContact.getLastName());
        assertEquals("", newContact.getPhoneNumber());
        assertEquals("dan12@gmail.com", newContact.getEmail());
    }
    @Test
    public void editingSucceedsWithNonBlankValues()
    {
        ContactItem newContact = new ContactItem("dan", "smith", "123-123-1234", "dan12@gmail.com");

        newContact.makeChanges("Wanda", "Cosmo", "124-124-1235", "FairGod@Parents.com");

        assertEquals("Wanda", newContact.getFirstName());
        assertEquals("Cosmo", newContact.getLastName());
        assertEquals("124-124-1235", newContact.getPhoneNumber());
        assertEquals("FairGod@Parents.com", newContact.getEmail());
    }
    @Test
    public void newListIsEmpty()
    {
        ContactList newList = new ContactList();
        assertEquals(0, newList.size());
    }
    @Test
    public void removingItemsDecreasesSize()
    {
        ContactItem newItem = new ContactItem("dan", "smith", "123-124-1234", "dan12@gmail.com");

        ContactList newList = new ContactList();
        newList.add(newItem);
        newList.remove(0);

        assertEquals(0, newList.size());
    }
    @Test
    public void removingItemsFailsWithInvalidIndex()
    {
        ContactList isEmpty = new ContactList();
        assertThrows(IndexOutOfBoundsException.class, () -> isEmpty.remove(0));
    }
    @Test
    public void savedContactListCanBeLoaded()
    {
        ContactList newList = new ContactList();
        newList.add(new ContactItem("dan", "smith", "123-123-1234", "dan12@gmail.com"));
        newList.add(new ContactItem("Dr", "Brown", "345-345-3671", "TheRealDr@Hotmail.com"));
        newList.save("important_contacts.txt");

        newList.load("important_contacts.txt");

        assertEquals(2, newList.size());
        assertEquals("Name: dan smith\n" +
                "Phone: 123-123-1234\n" +
                "Email: dan12@gmail.com", newList.getTaskText(0));
        assertEquals("Name: Dr Brown\n" +
                "Phone: 345-345-3671\n" +
                "Email: TheRealDr@Hotmail.com", newList.getTaskText(1));
    }
}
