import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

class ProjectScheduler {
    private Map<String, Task> taskMap;

    public ProjectScheduler() {
        this.taskMap = new HashMap<>();
    }

    public void addTask(String name, int duration) {
        taskMap.put(name, new Task(name, duration));
    }

    public void addDependency(String taskName, String dependencyName) {
        Task task = taskMap.get(taskName);
        Task dependency = taskMap.get(dependencyName);
        if (task != null && dependency != null) {
            task.addDependency(dependency);
        }
    }

    public void scheduleTasks() {
        Map<Task, Integer> inDegree = new HashMap<>();

        for (Task task : taskMap.values()) {
            inDegree.put(task, 0);
        }

        for (Task task : taskMap.values()) {
            for (Task dependency : task.dependencies) {
                inDegree.put(task, inDegree.get(task) + 1);
            }
        }

        Queue<Task> queue = new LinkedList<>();
        for (Map.Entry<Task, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
                entry.getKey().startDate = LocalDate.now(); // Project starts now
                entry.getKey().endDate = entry.getKey().startDate.plusDays(entry.getKey().duration);
            }
        }

        while (!queue.isEmpty()) {
            Task current = queue.poll();

            for (Task dependent : taskMap.values()) {
                if (dependent.dependencies.contains(current)) {
                    LocalDate earliestStartDate = dependent.startDate == null ? current.endDate : dependent.startDate;
                    if (earliestStartDate.isBefore(current.endDate)) {
                        earliestStartDate = current.endDate;
                    }
                    dependent.startDate = earliestStartDate;
                    dependent.endDate = dependent.startDate.plusDays(dependent.duration);

                    inDegree.put(dependent, inDegree.get(dependent) - 1);
                    if (inDegree.get(dependent) == 0) {
                        queue.add(dependent);
                    }
                }
            }
        }

        printSchedule();
    }

    private void printSchedule() {
        System.out.println("Task Schedule:");
        for (Task task : taskMap.values()) {
            System.out.println("Task: " + task.name + ", Start Date: " + task.startDate + ", End Date: " + task.endDate);
        }
    }
}