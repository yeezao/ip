import Tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

import static Standard.StandardStrings.SOMETHING_WRONG;

public class Parser {

    protected static void userCommands() {

        boolean isProgramRunning = true;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        Storage.openFile(tasks);

        while (isProgramRunning) {
            System.out.print("Enter your command here: ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println(SOMETHING_WRONG
                        + "Your input is empty - please enter a command and additional options.");
            } else if (input.equals("help")) {
                Ui.printHelpMessage();
            } else if (input.equals("bye")) {
                isProgramRunning = false;
            } else if (input.equals("list")) {
                Ui.listAllTasks(tasks);
            } else {
                TaskList.tasksCRUD(input, tasks);
            }
        }
    }

    protected static String createRemainingString(String[] inputWords) {
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < inputWords.length; i++) {
            sb.append(inputWords[i]);
            if (i < inputWords.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    protected static int extractIndexToModify(String[] inputWords) {

        int num = -1;
        try {
            num = Integer.parseInt(inputWords[1]) - 1;
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(SOMETHING_WRONG
                    + "Please input the item number you wish to edit.");
        }
        return num;

    }

}
