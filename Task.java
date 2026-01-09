import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
   private String description; // buy groceries
   private boolean isCompleted;
   private LocalDate duedate;
   
   // Constructor
   public Task(String description){
    this.description = description;
    this.isCompleted = false;
    this.duedate = null;
   }

   public Task(String description, LocalDate dueDate) {
      this.description = description;
      this.isCompleted = false;
      this.duedate = dueDate;
  }

   // Getters and Setters
   public String getDescription(){
    return description;
   }

   public boolean isCompleted(){
    return isCompleted;
   }

   public void setCompleted(boolean completed){
    isCompleted = completed;
   }

   public LocalDate getDueDate(){
      return duedate;
   }

   public void setDueDate(LocalDate dueDate) {
      this.duedate = dueDate;
  }

  public boolean isDueSoon() {
   if (duedate == null) return false;
   LocalDate today = LocalDate.now();
   return duedate.equals(today) || duedate.equals(today.plusDays(1));
}

@Override
public String toString() {
   String status = isCompleted ? "[x]" : "[ ]";
   String dueInfo = (duedate != null) ? 
       " (Due: " + duedate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ")" : "";
   return status + " " + description + dueInfo;
}
}


