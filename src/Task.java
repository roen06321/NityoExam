import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Task {
    String name;
    int duration; // Duration in days
    List<Task> dependencies; // List of dependent tasks
    LocalDate startDate;
    LocalDate endDate;

    public Task(String name, int duration) {
        this.name = name;
        this.duration = duration;
        this.dependencies = new ArrayList<>();
    }

    public void addDependency(Task task) {
        this.dependencies.add(task);
    }
}