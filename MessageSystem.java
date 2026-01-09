// MessageSystem.java
public class MessageSystem {

    public static void taskAdded(String taskName) {
        System.out.println(" Task added: \"" + taskName + "\"");
    }

    public static void taskCompleted(String taskName) {
        System.out.println(" Task completed: \"" + taskName + "\" Great job!");
    }

    public static void taskDeleted(String taskName) {
        System.out.println(" Task deleted: \"" + taskName + "\"");
    }

    public static void taskDueSoon(String taskName, String dueDate) {
        System.out.println("\n* Reminder: Task \"" + taskName + "\" is due soon (" + dueDate + ")");
    }

    public static void taskDueToday(String taskName, String dueDate) {
        System.out.println("\n* Reminder: Task \"" + taskName + "\" is DUE TODAY (" + dueDate + ")!");
    }

    public static void taskOverdue(String taskName, String dueDate) {
        System.out.println("\n* OVERDUE: Task \"" + taskName + "\" was due on " + dueDate + "!");
    }
    

    public static void generalMessage(String message) {
        System.out.println(" " + message);
    }
}
