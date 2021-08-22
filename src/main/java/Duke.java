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
        String[] storage = new String[100];
        int nextAdd = 0;

        while (continueProgram) {
            System.out.print("Enter your command here: ");
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                continueProgram = false;
            } else if (input.equals("list")) {
                for (int i = 0; i < nextAdd; i++) {
                    System.out.println((i + 1) + ": " + storage[i]);
                }
            } else {
                storage[nextAdd] = input;
                System.out.println("Added: " + input);
                nextAdd++;
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
