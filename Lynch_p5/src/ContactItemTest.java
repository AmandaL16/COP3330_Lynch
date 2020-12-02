import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class ContactItemTest
{
    @Test
    public void creationFailsWithAllBlankValues()
    {
        assertThrows(IllegalArgumentException.class, () -> new ContactItem("", "", "", ""));
    }
    @Test
    public void creationSucceedsWithBlankEmail()
    {
        ContactItem newContact = new ContactItem("dan", "smith", "123-123-1234", "");

        ContactList newList = new ContactList();
        newList.add(newContact);

        assertEquals(1, newList.size());
    }
    @Test
    public void creationSucceedsWithBlankFirstName()
    {
        ContactItem newContact = new ContactItem("", "smith", "123-123-1234", "dan12@gmail.com");

        ContactList newList = new ContactList();
        newList.add(newContact);

        assertEquals(1, newList.size());
    }
    @Test
    public void creationSucceedsWithBlankLastName()
    {
        ContactItem newContact = new ContactItem("dan", "", "123-123-1234", "dan12@gmail.com");

        ContactList newList = new ContactList();
        newList.add(newContact);

        assertEquals(1, newList.size());
    }
    @Test
    public void creationSucceedsWithBlankPhone()
    {
        ContactItem newContact = new ContactItem("dan", "smith", "", "dan12@gmail.com");

        ContactList newList = new ContactList();
        newList.add(newContact);

        assertEquals(1, newList.size());
    }
    @Test
    public void creationSucceedsWithNonBlankValues()
    {
        ContactItem newContact = new ContactItem("dan", "smith", "123-123-1234", "dan12@gmail.com");

        ContactList newList = new ContactList();
        newList.add(newContact);

        assertEquals(1, newList.size());
    }
    @Test
    public void editingFailsWithAllBlankValues()
    {
        ContactItem newContact = new ContactItem("dan", "smith", "123-123-1234", "dan12@gmail.com");

        assertThrows(IllegalArgumentException.class, () -> newContact.makeChanges("", "", "", ""));

        assertEquals("dan", newContact.getFirstName());
        assertEquals("smith", newContact.getLastName());
        assertEquals("123-123-1234", newContact.getPhoneNumber());
        assertEquals("dan12@gmail.com", newContact.getEmail());
    }
    @Test
    public void editingSucceedsWithBlankEmail()
    {
        ContactItem newContact = new ContactItem("dan", "smith", "123-123-1234", "dan12@gmail.com");

        newContact.makeChanges(newContact.getFirstName(), newContact.getLastName(), newContact.getPhoneNumber(), "");

        assertEquals("dan", newContact.getFirstName());
        assertEquals("smith", newContact.getLastName());
        assertEquals("123-123-1234", newContact.getPhoneNumber());
        assertEquals("", newContact.getEmail());
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

        newContact.makeChanges("Wanda", "Cosmo", "124-124-1235", "FairyGod@Parents.com");

        assertEquals("Wanda", newContact.getFirstName());
        assertEquals("Cosmo", newContact.getLastName());
        assertEquals("124-124-1235", newContact.getPhoneNumber());
        assertEquals("FairyGod@Parents.com", newContact.getEmail());
    }
    @Test
    public void testToString()
    {
        ContactItem contact = new ContactItem("dan", "smith", "123-123-1234", "dan12@gmail.com");

        String expected = "Name: dan smith\n" +
                "Phone: 123-123-1234\n" +
                "Email: dan12@gmail.com";

        assertEquals(expected, contact.toString());
    }
}
