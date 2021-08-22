import java.util.Scanner;

public class Duke {

    private static void firstGreet() {
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        System.out.println("-------------------------------------");
    }

    private static void userCommands() {
        boolean continueProgram = true;
        Scanner scanner = new Scanner(System.in);

        while (continueProgram) {
            System.out.print("Enter your command here: ");
            String input = scanner.nextLine();
            if (!input.equals("bye")) {
                System.out.println("You entered: " + input);
            } else {
                continueProgram = false;
            }
        }
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("---------------------");

        firstGreet();

        userCommands();

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("-------------------------------------");


    }
}
