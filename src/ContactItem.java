public class ContactItem
{
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    public ContactItem(String firstName, String lastName, String phoneNumber, String email)
    {
        if(firstName.isBlank() && lastName.isBlank() && phoneNumber.isBlank() && email.isBlank())
        {
            throw new IllegalArgumentException("WARNING: you must input a value for at least one category!" +
                    " no contact was created :(");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public void makeChanges(String firstName, String lastName, String phoneNumber, String email)
    {
        if(firstName.isBlank() && lastName.isBlank() && phoneNumber.isBlank() && email.isBlank())
        {
            throw new IllegalArgumentException("WARNING: you must input a value for at least one category!" +
                    " contact unable to update :(");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName()
    {
        return firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public String getEmail()
    {
        return email;
    }

    @Override
    public String toString()
    {
        return String.format("Name: %s %s\nPhone: %s\nEmail: %s", firstName, lastName, phoneNumber, email);
    }

}
