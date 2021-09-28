package UserInteractions;

import Tasks.Task;

import java.util.ArrayList;

public class Ui {

    /**
     * Prints out all tasks in the {@link ArrayList<Task>}.
     *
     * @param tasks list of tasks
     */
    public static void listAllTasks(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks in your list.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            printSingleTask(tasks.get(i), i);
        }
    }

    /**
     * Prints out a single {@link Task}
     *
     * @param task single {@link Task} to be printed
     * @param i index of task to be printed (from the {@link ArrayList<Task>})
     */
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

    /**
     * Prints the help message when the user enters "help"
     */
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

    /**
     * Prints the goodbye message when the user enters "bye"
     */
    public static void goodBye() {

        System.out.println("Exiting the program now. Goodbye!");

    }

    /**
     * Prints the welcome message when the user first enters the program
     */
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
        System.out.println("(note: type '-help' anytime for more information)");
    }

    /**
     * Prints an error message when there is an error parsing the {@link java.time.LocalDateTime} from the user's input
     */
    public static void dateTimeError() {
        System.out.println("Please check that the date-time is entered in the correct format of dd-mm-yyyy hh:mm");
        System.out.println("The deadline/event date-time has been set to the current time. " +
                "Please try this operation again");
    }

}
