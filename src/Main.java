import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        ProjectScheduler scheduler = new ProjectScheduler();

        // Define tasks
        scheduler.addTask("Task A", 10);
        scheduler.addTask("Task B", 10);
        scheduler.addTask("Task C", 10);
        scheduler.addTask("Task D", 10);

        // Define dependencies (Task D depends on Task A and Task B)
        scheduler.addDependency("Task D", "Task A");
        scheduler.addDependency("Task D", "Task B");
        scheduler.addDependency("Task B", "Task C");

        scheduler.scheduleTasks();
    }


}