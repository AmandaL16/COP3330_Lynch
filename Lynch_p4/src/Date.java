public class Date {
    private int year;
    private int month;
    private int day;

    //Default constructor
    public Date()
    {
        this(0, 0, 0);
    }

    public Date(int year, int month, int day)
    {
        this.year = year;
        this.month = month;
        this.day = day;
    }
    //Following methods for the month, day, and year
    public int getYear()
    {
        return this.year;
    }

    public int getDay()
    {
        return this.day;
    }

    public int getMonth()
    {
        return this.month;
    }

    public void setYear(int year)
    {
        if (year < 1)
        {
            System.out.println("Invalid year");
        }
        this.year = year;
    }
    public void setMonth(int month)
    {
        if(month < 1 || month > 12)
        {
            System.out.println("Invalid month");
        }

        this.month = month;
    }

    public void setDay(int day)
    {
        if(day < 1 || day > 31)
        {
            System.out.println("Invalid day");
        }

        this.day = day;
    }

    //Returns date in YYYY-MM-DD format
    public String toString()
    {
        return String.format("%d-%d-%d", this.year, this.month, this.day);
    }
}
