import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class ReminderSystem {
    private TaskManager taskManager;
    private Set<String> remindedTasks; // store descriptions of tasks already reminded

    public ReminderSystem(TaskManager taskManager) {
        this.taskManager = taskManager;
        this.remindedTasks = new HashSet<>();
    }

    // Check reminders once — call this before showing the menu
    public void checkReminders() {
        for (Task task : taskManager.getTasks()) {
            if (!task.isCompleted() && task.getDueDate() != null) {
                LocalDate today = LocalDate.now();
                LocalDate due = task.getDueDate();
                String taskKey = task.getDescription() + due; // unique key

                // Check if we already reminded for this task
                if (!remindedTasks.contains(taskKey)) {
                    if (due.isEqual(today)) {
                        MessageSystem.taskDueToday(task.getDescription(), due.toString());
                        remindedTasks.add(taskKey); // mark as reminded
                    } else if (due.isEqual(today.plusDays(1))) {
                        MessageSystem.taskDueSoon(task.getDescription(), due.toString());
                        remindedTasks.add(taskKey);
                    }
                    else if (due.isBefore(today)) {
                        MessageSystem.taskOverdue(task.getDescription(), due.toString());
                        remindedTasks.add(taskKey);
                    }                    
                }
            }
        }
    }
}
