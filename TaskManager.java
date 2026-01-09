import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private List<Task> tasks;
    

    public TaskManager(){
        this.tasks = new ArrayList<>();
    }

    // Core Methods //

    // 1. Add a new task 
    public void addTask(String description){
      tasks.add(new Task(description));
      MessageSystem.taskAdded(description);
   }

    // 2. Mark as completed
    public void markTaskCompleted(int index){
        if(index < 0 || index >= tasks.size()){
            System.out.println("Invalid task number!");
            return;
        }

        tasks.get(index).setCompleted(true);
        MessageSystem.taskCompleted(tasks.get(index).getDescription());
    }

    // 3. Delete a task 
    public void deleteTask(int index){
        if(index < 0 || index >= tasks.size()){
            MessageSystem.generalMessage("Invalid task number!");
            return;
        }

        Task removedTask = tasks.remove(index);
        System.out.println("Deleted task: " + removedTask.getDescription());
    }

    // 4. Print all tasks 
    public void printAllTasks(){
        if(tasks.isEmpty()){
            System.out.println("No tasks yet!");
        } else {
            System.out.println("\n--- Your Tasks ---");
            for(int i = 0; i < tasks.size(); i++){
                Task task = tasks.get(i);
                String status = task.isCompleted() ? "[x]" : "[ ]";
                System.out.println((i+1) + ". " + status + task.getDescription());

            }
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void saveTasksToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("tasks.txt"))) {
            for (Task t : tasks) {
                String due = (t.getDueDate() != null) ? t.getDueDate().toString() : "null";
                writer.println(t.getDescription() + "|" + due + "|" + t.isCompleted());
            }       
        }catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
            }
    }

    public void loadTasksFromFile() {
        File file = new File("tasks.txt");
        if (!file.exists()) return; // nothing to load yet

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 3) continue;
                String description = parts[0];
                LocalDate dueDate = parts[1].equals("null") ? null : LocalDate.parse(parts[1]);
                boolean completed = Boolean.parseBoolean(parts[2]);

                Task task = (dueDate == null) ? new Task(description) : new Task(description, dueDate);
                task.setCompleted(completed);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }
}

