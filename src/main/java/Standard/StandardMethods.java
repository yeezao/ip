package Standard;

public class StandardMethods {

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

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("-------------------------------------");

    }

}
