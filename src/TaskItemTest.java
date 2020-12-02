import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskItemTest
{
    @Test
    public void constructorFailsWithInvalidDueDate()
    {
        assertThrows(IllegalArgumentException.class, () -> new TaskItem("chores", "clean room", "0000-14-39"));
    }
    @Test
    public void constructorFailsWithInvalidTitle()
    {
        assertThrows(IllegalArgumentException.class, () -> new TaskItem("", "", "2020-12-12"));
    }
    @Test
    public void constructorSucceedsWithValidDueDate()
    {
        TaskItem task = new TaskItem("chores", "clean room", "2020-12-12");
        assertEquals("chores", task.getTitle());
        assertEquals("clean room", task.getDescription());
        assertEquals("2020-12-12", task.getDueDate().toString());
    }
    @Test
    public void constructorSucceedsWithValidTitle()
    {
        TaskItem task = new TaskItem("chores", "clean room", "2020-12-12");

        assertEquals("chores", task.getTitle());
        assertEquals("clean room", task.getDescription());
        assertEquals("2020-12-12", task.getDueDate().toString());
    }
    @Test
    public void editingDescriptionSucceedsWithExpectedValue()
    {
        TaskItem task = new TaskItem("chores", "clean room", "2020-12-12");

        task.makeChanges(task.getTitle(), "laundry", task.getDueDate().toString());

        assertEquals("chores", task.getTitle());
        assertEquals("laundry", task.getDescription());
        assertEquals("2020-12-12", task.getDueDate().toString());
    }
    @Test
    public void editingDueDateFailsWithInvalidDateFormat()
    {
        TaskItem task = new TaskItem("chores", "clean room", "2020-12-12");
        assertThrows(IllegalArgumentException.class, () -> task.makeChanges(task.getTitle(), task.getDescription(),"Oct 12th, 2999"));

        assertEquals("chores", task.getTitle());
        assertEquals("clean room", task.getDescription());
        assertEquals("2020-12-12", task.getDueDate().toString());
    }
    @Test
    public void editingDueDateFailsWithInvalidYYYMMDD()
    {
        TaskItem task = new TaskItem("chores", "clean room", "2020-12-12");
        assertThrows(IllegalArgumentException.class, () -> task.makeChanges(task.getTitle(), task.getDescription(),"2020-29-35"));

        assertEquals("chores", task.getTitle());
        assertEquals("clean room", task.getDescription());
        assertEquals("2020-12-12", task.getDueDate().toString());
    }
    @Test
    public void editingDueDateSucceedsWithExpectedValue()
    {
        TaskItem task = new TaskItem("chores", "clean room", "2020-12-12");

        TaskList list = new TaskList();
        list.add(task);
        list.makeChanges(0, task.getTitle(), task.getDescription(), "2020-12-29");

        assertEquals("chores", task.getTitle());
        assertEquals("clean room", task.getDescription());
        assertEquals("2020-12-29", task.getDueDate().toString());
    }
    @Test
    public void editingTitleFailsWithEmptyString()
    {
        TaskItem task = new TaskItem("Chores", "clean room", "2020-12-12");

        assertThrows(IllegalArgumentException.class, () -> task.makeChanges("", task.getDescription(), task.getDueDate().toString()));

        assertEquals("Chores", task.getTitle());
        assertEquals("clean room", task.getDescription());
        assertEquals("2020-12-12", task.getDueDate().toString());
    }
    @Test
    public void editingTitleSucceedsWithExpectedValue()
    {
        TaskItem task = new TaskItem("Chores", "clean room", "2020-12-12");

        task.makeChanges("doctors", task.getDescription(), task.getDueDate().toString());

        assertEquals("doctors", task.getTitle());
        assertEquals("clean room", task.getDescription());
        assertEquals("2020-12-12", task.getDueDate().toString());
    }
}
