import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TaskListTest
{
    @Test
    public void addingItemsIncreasesSize() //
    {
        TaskItem newItem = new TaskItem("chores", "clean house", "2020-12-12");

        TaskList newList = new TaskList();
        newList.add(newItem);

        assertEquals(1, newList.size());
    }
    @Test
    public void completingTaskItemChangesStatus()
    {
        TaskItem newItem = new TaskItem("chores", "clean house", "2020-12-12");

        TaskList newList = new TaskList();
        newList.add(newItem);
        newList.complete(0, true);

        assertTrue(newList.isTaskComplete(0));
    }
    @Test
    public void completingTaskItemFailsWithInvalidIndex()
    {
        TaskItem newItem = new TaskItem("chores", "clean house", "2020-12-12");

        TaskList newList = new TaskList();
        newList.add(newItem);

        assertThrows(IndexOutOfBoundsException.class, () -> newList.complete(1, true));
    }
    @Test
    public void editingItemDescriptionFailsWithInvalidIndex()
    {
        TaskItem newItem = new TaskItem("chores", "clean house", "2020-12-12");

        TaskList newList = new TaskList();
        newList.add(newItem);

        assertThrows(IndexOutOfBoundsException.class, () -> newList.makeChanges(1, newItem.getTitle(), "fold clothes", newItem.getDueDate().toString()));
    }
    @Test
    public void editingItemDescriptionSucceedsWithExpectedValue()
    {
        TaskItem newItem = new TaskItem("chores", "clean house", "2020-12-12");

        TaskList newList = new TaskList();
        newList.add(newItem);

        newList.makeChanges(0, newItem.getTitle(), "fold laundry", newItem.getDueDate().toString());
        assertEquals("fold laundry", newList.getTaskDescription(0));
    }
    @Test
    public void editingItemDueDateSucceedsWithExpectedValue()
    {
        TaskItem newItem = new TaskItem("chores", "clean house", "2020-12-12");

        TaskList newList = new TaskList();
        newList.add(newItem);

        newList.makeChanges(0, newItem.getTitle(), newItem.getDescription(), "2020-12-30");
        assertEquals("2020-12-30", newList.getTaskDueDate(0));
    }
    @Test
    public void editingItemTitleFailsWithEmptyString()
    {
        TaskItem newItem = new TaskItem("chores", "clean house", "2020-12-12");
        TaskList newList = new TaskList();
        newList.add(newItem);

        assertThrows(IllegalArgumentException.class, () -> newList.makeChanges(0, "", newItem.getDescription(), newItem.getDueDate().toString())); //???
    }
    @Test
    public void editingItemTitleFailsWithInvalidIndex() //
    {
        TaskItem newItem = new TaskItem("chores", "clean house", "2020-12-12");
        TaskList newList = new TaskList();
        newList.add(newItem);

        assertThrows(IndexOutOfBoundsException.class, () -> newList.makeChanges(1, "Laundry", "fold clothes", "2020-12-21")); //???
    }
    @Test
    public void editingItemTitleSucceedsWithExpectedValue() //
    {
        TaskItem newItem = new TaskItem("chores", "clean house", "2020-12-12");

        TaskList newList = new TaskList();
        newList.add(newItem);

        newList.makeChanges(0, "Laundry", newItem.getDescription(), newItem.getDueDate().toString());
        assertEquals("Laundry", newList.getTaskTitle(0));
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidDateFormat()
    {

        TaskItem newItem = new TaskItem("chores", "clean house", "2020-12-12");

        TaskList newList = new TaskList();
        newList.add(newItem);

        assertThrows(IllegalArgumentException.class, () -> newList.makeChanges(0, newItem.getTitle(), newItem.getDescription(), "oct 9th, 2020"));

    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex()
    {
        TaskItem newItem = new TaskItem("chores", "clean house", "2020-12-12");

        TaskList newList = new TaskList();
        newList.add(newItem);

        assertThrows(IndexOutOfBoundsException.class, () -> newList.makeChanges(1, newItem.getTitle(), newItem.getDescription(), "2020-12-03"));
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidYYYMMDD()
    {
        TaskItem newItem = new TaskItem("chores", "clean house", "2020-12-12");

        TaskList newList = new TaskList();
        newList.add(newItem);

        assertThrows(IllegalArgumentException.class, () -> newList.makeChanges(0, newItem.getTitle(), newItem.getDescription(), "2020-13-41"));
    }
    @Test
    public void gettingItemDescriptionFailsWithInvalidIndex()
    {
        TaskList newList = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> newList.getTaskDescription(0));
    }
    @Test
    public void gettingItemDescriptionSucceedsWithValidIndex()
    {
        TaskItem newItem = new TaskItem("chores", "clean house", "2020-12-12");

        TaskList newList = new TaskList();
        newList.add(newItem);

        assertEquals("clean house", newList.getTaskDescription(0));
    }
    @Test
    public void gettingItemDueDateFailsWithInvalidIndex() //
    {
        TaskList newList = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> newList.getTaskDueDate(0));
    }
    @Test
    public void gettingItemDueDateSucceedsWithValidIndex() //
    {
        TaskItem newItem = new TaskItem("chores", "clean house", "2020-12-12");

        TaskList newList = new TaskList();
        newList.add(newItem);

        assertEquals("2020-12-12", newList.getTaskDueDate(0));
    }
    @Test
    public void gettingItemTitleFailsWithInvalidIndex() //
    {
        TaskList newList = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> newList.getTaskTitle(0));
    }
    @Test
    public void gettingItemTitleSucceedsWithValidIndex() //
    {
        TaskItem newItem = new TaskItem("chores", "clean house", "2020-12-12");

        TaskList newList = new TaskList();
        newList.add(newItem);

        assertEquals("chores", newList.getTaskTitle(0));
    }
    @Test
    public void newListIsEmpty() //
    {
        TaskList isEmpty = new TaskList();
        assertEquals(0, isEmpty.size());
    }
    @Test
    public void removingItemsDecreasesSize()
    {
        TaskItem newItem = new TaskItem("chores", "clean house", "2020-12-12");

        TaskList newList = new TaskList();
        newList.add(newItem);
        newList.remove(0);

        assertEquals(0, newList.size());
    }
    @Test
    public void removingItemsFailsWithInvalidIndex()
    {
        TaskList isEmpty = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> isEmpty.remove(0));
    }
    @Test
    public void savedTaskListCanBeLoaded()
    {
        TaskList newList = new TaskList();
        newList.add(new TaskItem("chores", "clean house", "2020-12-12"));
        newList.add(new TaskItem("doctors", "get meds", "2020-12-22"));
        newList.save("dec_tasks.txt");

        newList.load("dec_tasks.txt");

        assertEquals(2, newList.size());
        assertEquals("[2020-12-12] chores: clean house", newList.getTaskText(0));
        assertEquals("[2020-12-22] doctors: get meds", newList.getTaskText(1));

    }
    @Test
    public void uncompletingTaskItemChangesStatus()
    {
        TaskItem newItem = new TaskItem("chores", "clean house", "2020-12-12");

        TaskList newList = new TaskList();
        newList.add(newItem);
        newList.complete(0, false);

        assertFalse(newList.isTaskComplete(0));
    }
    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex()
    {
        TaskItem newItem = new TaskItem("chores", "clean house", "2020-12-12");

        TaskList newList = new TaskList();
        newList.add(newItem);

        assertThrows(IndexOutOfBoundsException.class, () -> newList.complete(1, false));
    }
}