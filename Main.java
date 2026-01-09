import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {

        Scanner obj = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        
        taskManager.loadTasksFromFile();
        
        ReminderSystem reminderSystem = new ReminderSystem(taskManager);
        reminderSystem.checkReminders();

        System.out.println("=== To-Do List Manager ===");

        while (true) {
            reminderSystem.checkReminders();
            // Display menu
            System.out.println("\nChoose an option");
            System.out.println("1. Add Task");
            System.out.println("2. Mark Task as Completed");
            System.out.println("3. Delete Task");
            System.out.println("4. View All Tasks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            int choice = obj.nextInt();
            obj.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Task Description: ");
                    String description = obj.nextLine();

                    System.out.print("Enter due date (YYYY-MM-DD) or leave empty: ");
                    String dateInput = obj.nextLine().trim();
                    LocalDate dueDate = null;

                    if (!dateInput.isEmpty()) {
                        try {
                            dueDate = LocalDate.parse(dateInput);
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format! Task will have no due date.");
                        }
                    }

                    Task newTask = (dueDate == null) ? new Task(description) : new Task(description, dueDate);
                    taskManager.getTasks().add(newTask);
                    MessageSystem.taskAdded(description);

                    break;

                case 2:
                    taskManager.printAllTasks();
                    System.out.print("Enter task number to mark as complete: ");
                    int completeIndex = obj.nextInt() - 1;
                    taskManager.markTaskCompleted(completeIndex);
                    break;

                case 3:
                    taskManager.printAllTasks();
                    System.out.print("Enter task number to delete: ");
                    int deleteIndex = obj.nextInt() - 1;
                    taskManager.deleteTask(deleteIndex);
                    break;

                case 4:
                    
                    taskManager.printAllTasks();
                    reminderSystem.checkReminders();
                    break;
    

                case 5:
                    // Final reminder before exit
                    reminderSystem = new ReminderSystem(taskManager);
                    reminderSystem.checkReminders();
                
                    // Save all tasks to file
                    System.out.println("\n Saving your tasks before exit...");
                    taskManager.saveTasksToFile();
                
                    // Friendly exit message
                    System.out.println("\n Goodbye! Don't forget your tasks!");
                    obj.close();
                    System.exit(0);
                    break;
            }
        }
    }
}
