import java.util.*;

class Task {
    private String title;
    private String description;
    private String priority;
    private boolean isCompleted;

    // Constructor
    public Task(String title, String description, String priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.isCompleted = false;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getPriority() {
        return priority;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsCompleted() {
        this.isCompleted = true;
    }

    public void markAsIncomplete() {
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return "Task: " + title + " | Priority: " + priority + " | Status: " + (isCompleted ? "Completed" : "Not Completed") + "\nDescription: " + description;
    }
}

class ToDoList {
    private List<Task> tasks;

    // Constructor
    public ToDoList() {
        this.tasks = new ArrayList<>();
    }

    // Add a task to the list
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Remove a task by title
    public void removeTask(String title) {
        tasks.removeIf(task -> task.getTitle().equalsIgnoreCase(title));
    }

    // Mark a task as completed
    public void markTaskAsCompleted(String title) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                task.markAsCompleted();
                return;
            }
        }
        System.out.println("Task not found.");
    }

    // Mark a task as incomplete
    public void markTaskAsIncomplete(String title) {
        for (Task task : tasks) {
            if (task.getTitle().equalsIgnoreCase(title)) {
                task.markAsIncomplete();
                return;
            }
        }
        System.out.println("Task not found.");
    }

    // View all tasks, sorted by priority (High -> Medium -> Low)
    public void viewAllTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        // Sort tasks by priority
        tasks.sort(Comparator.comparing(Task::getPriority));

        for (Task task : tasks) {
            System.out.println(task);
            System.out.println("=====================================");
        }
    }

    // View tasks based on priority
    public void viewTasksByPriority(String priority) {
        for (Task task : tasks) {
            if (task.getPriority().equalsIgnoreCase(priority)) {
                System.out.println(task);
                System.out.println("=====================================");
            }
        }
    }
}

public class ToDoListApp {
    private static Scanner scanner = new Scanner(System.in);
    private static ToDoList toDoList = new ToDoList();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nTo-Do List Application");
            System.out.println("1. Add a Task");
            System.out.println("2. Remove a Task");
            System.out.println("3. Mark a Task as Completed");
            System.out.println("4. Mark a Task as Incomplete");
            System.out.println("5. View All Tasks");
            System.out.println("6. View Tasks by Priority");
            System.out.println("7. Exit");

            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    addTask();
                    break;
                case 2:
                    removeTask();
                    break;
                case 3:
                    markTaskAsCompleted();
                    break;
                case 4:
                    markTaskAsIncomplete();
                    break;
                case 5:
                    viewAllTasks();
                    break;
                case 6:
                    viewTasksByPriority();
                    break;
                case 7:
                    System.out.println("Exiting application...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addTask() {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter task priority (High/Medium/Low): ");
        String priority = scanner.nextLine();

        Task task = new Task(title, description, priority);
        toDoList.addTask(task);
        System.out.println("Task added successfully.");
    }

    private static void removeTask() {
        System.out.print("Enter the title of the task to remove: ");
        String title = scanner.nextLine();
        toDoList.removeTask(title);
        System.out.println("Task removed successfully.");
    }

    private static void markTaskAsCompleted() {
        System.out.print("Enter the title of the task to mark as completed: ");
        String title = scanner.nextLine();
        toDoList.markTaskAsCompleted(title);
        System.out.println("Task marked as completed.");
    }

    private static void markTaskAsIncomplete() {
        System.out.print("Enter the title of the task to mark as incomplete: ");
        String title = scanner.nextLine();
        toDoList.markTaskAsIncomplete(title);
        System.out.println("Task marked as incomplete.");
    }

    private static void viewAllTasks() {
        System.out.println("Displaying all tasks:");
        toDoList.viewAllTasks();
    }

    private static void viewTasksByPriority() {
        System.out.print("Enter priority (High/Medium/Low): ");
        String priority = scanner.nextLine();
        System.out.println("Displaying tasks with " + priority + " priority:");
        toDoList.viewTasksByPriority(priority);
    }
}
