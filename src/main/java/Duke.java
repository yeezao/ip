import javax.sound.midi.SysexMessage;
import java.lang.reflect.Array;
import java.util.Scanner;

public class Duke {

    private static final String SOMETHING_WRONG = "Sorry, something went wrong. ";

    private static int nextAdd = 0;

    private static void firstGreet() {
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        System.out.println("-------------------------------------");
    }

    private static String createRemainingString(String[] inputWords) {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < inputWords.length; i++) {
            sb.append(inputWords[i]);
            if (i < inputWords.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    private static void makeTaskDone(String[] inputWords, Task[] tasks) {
        for (int i = 1; i < inputWords.length; i++) {
            try {
                int num = Integer.parseInt(inputWords[i]) - 1;
                tasks[num].markAsDone();
                System.out.println("Congrats! You've completed this task: [X] "
                        + tasks[num].getTaskDesc());
            } catch (NumberFormatException e) {
                System.out.println(SOMETHING_WRONG +
                        "Please ensure that you have entered the index of the task(s) to be marked as done");
                break;
            }
        }
    }

    private static void listAllTasks(Task[] tasks) {
        for (int i = 0; i < nextAdd; i++) {
            StringBuilder sb = new StringBuilder();
            String done = tasks[i].isPending() ? "[ ]" : "[X]";
            sb.append(i + 1).append(": ").append(tasks[i].getType())
                    .append(done).append(" ").append(tasks[i].getTaskDesc());
            if (!tasks[i].getAdditionalInfo().isEmpty()) {
                sb.append(" ").append(tasks[i].getAdditionalInfo());
            }
            System.out.println(sb);
        }
    }

    private static int extractIndexToModify(String[] inputWords) {

        int num = -1;
        try {
            num = Integer.parseInt(inputWords[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(SOMETHING_WRONG
                    + "Please input the item number you wish to edit.");
        }
        return num;

    }

    private static void tasksAddOrModify(String input, Task[] tasks) {

        String[] inputWords = input.split(" ");
        if (inputWords[0].equals("done")) {
            makeTaskDone(inputWords, tasks);
        } else {
//            if (inputWords.length < 2) {
//                System.out.println(SOMETHING_WRONG + "Please enter more information after your command.");
//            }
            if (inputWords[0].equals("deadline")) {
                int num = extractIndexToModify(inputWords);
                if (num >= 0) {
                    Deadline temp = new Deadline(tasks[num].getTaskDesc());
                    String deadlineString = createRemainingString(inputWords);
                    temp.setByDateTime(deadlineString);
                    tasks[num] = temp;
                    System.out.println("Deadline set: " + deadlineString);
                }
            } else if (inputWords[0].equals("event")) {
                int num = extractIndexToModify(inputWords);
                if (num >= 0) {
                    Event temp = new Event(tasks[num].getTaskDesc());
                    String eventString = createRemainingString(inputWords);
                    temp.setAtDateTime(eventString);
                    tasks[num] = temp;
                    System.out.println("Event set at: " + eventString);
                }
            } else {
                Todo temp = new Todo(input);
                tasks[nextAdd] = temp;
                System.out.println("Added: " + input);
                nextAdd++;
            }
        }

    }


    private static void userCommands() {

        boolean isProgramRunning = true;
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[100];

        while (isProgramRunning) {
            System.out.print("Enter your command here: ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println(SOMETHING_WRONG
                        + "Your input is empty - please enter a command and additional options.");
            } else if (input.equals("help")) {
                StandardMethods.printHelpMessage();
            } else if (input.equals("bye")) {
                isProgramRunning = false;
            } else if (input.equals("list")) {
                listAllTasks(tasks);
            } else {
                tasksAddOrModify(input, tasks);
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
