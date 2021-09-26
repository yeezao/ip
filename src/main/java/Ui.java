import Tasks.Task;

import java.util.ArrayList;

public class Ui {

    protected static void listAllTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks in your list.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            printSingleTask(tasks.get(i), i);
        }
    }
    
    protected static void printSingleTask(Task task, int i) {
        StringBuilder sb = new StringBuilder();
        String done = task.isPending() ? "[ ]" : "[X]";
        sb.append(i + 1).append(": ").append(task.getType())
                .append(done).append(" ").append(task.getTaskDesc());
        if (!task.getAdditionalInfo().isEmpty()) {
            sb.append(" ").append(task.getAdditionalInfo());
        }
        System.out.println(sb);
    }

    public static void printHelpMessage() {

        System.out.println("-----------------------HELP------------------------");
        System.out.println("Welcome to Duke! Inputs follow the syntax below:");
        System.out.println("- To add a task, simply type the name of your task.");
        System.out.println("- To add a deadline to your task, enter 'deadline [some string]'");
        System.out.println("- To add a date to an event, enter 'event [some string]'");
        System.out.println("- To list all tasks, type 'list'");
        System.out.println("- To mark a task/event/deadline as done, type 'done [tasknumber]'");
        System.out.println("- To exit the program, type 'bye'");
        System.out.println("---------------------------------------------------");

    }

    public static void goodBye() {

        System.out.println("Exiting the program now. Goodbye!");

    }

    public static void firstGreet() {


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("---------------------");

        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        System.out.println("-------------------------------------");
        System.out.println("(note: type 'help' anytime for more information)");
    }

    public static void dateTimeError() {
        System.out.println("Please check that the date-time is entered in the correct format of dd-mm-yyyy hh:mm");
        System.out.println("The deadline/event date-time has been set to the current time. " +
                "Please try this operation again");
    }

}
