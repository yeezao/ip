import Standard.StandardMethods;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static final String SOMETHING_WRONG = "Sorry, something went wrong. ";
    private static final String FILE_PATH = "tasks.txt";

    private static int nextAdd = 0;

    private static File file;

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

    private static void makeTaskDone(String[] inputWords, ArrayList<Task> tasks) {
        for (int i = 1; i < inputWords.length; i++) {
            try {
                int num = Integer.parseInt(inputWords[i]) - 1;
                tasks.get(num).markAsDone();
                System.out.println("Congrats! You've completed this task: [X] "
                        + tasks.get(num).getTaskDesc());
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println(SOMETHING_WRONG +
                        "Please ensure that you have entered the index of the task(s) to be marked as done");
                break;
            }
        }
    }

    private static void listAllTasks(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            StringBuilder sb = new StringBuilder();
            String done = tasks.get(i).isPending() ? "[ ]" : "[X]";
            sb.append(i + 1).append(": ").append(tasks.get(i).getType())
                    .append(done).append(" ").append(tasks.get(i).getTaskDesc());
            if (!tasks.get(i).getAdditionalInfo().isEmpty()) {
                sb.append(" ").append(tasks.get(i).getAdditionalInfo());
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

    private static void deleteTask(String[] inputWords, ArrayList<Task> tasks) {

        for (int i = inputWords.length - 1; i > 0; i--) {
            try {
                int num = Integer.parseInt(inputWords[i]) - 1;
                System.out.println("Task " + (num + 1) + ": "
                        + tasks.get(num).getTaskDesc() + " has been deleted");
                if (tasks.size() == 1) {
                    tasks.clear();
                } else {
                    tasks.remove(num);
                }
                nextAdd--;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println(e);
                System.out.println(SOMETHING_WRONG +
                        "Please ensure that you have entered the index of the task(s) to be marked as done");
                break;
            }
        }

    }

    private static void tasksCRUD(String input, ArrayList<Task> tasks) {

        String[] inputWords = input.split(" ");
        if (inputWords[0].equals("done")) {
            makeTaskDone(inputWords, tasks);
        } else if (inputWords[0].equals("delete")) {
            deleteTask(inputWords, tasks);
        } else {
//            if (inputWords.length < 2) {
//                System.out.println(SOMETHING_WRONG + "Please enter more information after your command.");
//            }
            if (inputWords[0].equals("deadline")) {
                int num = extractIndexToModify(inputWords);
                if (num >= 0) {
                    Deadline deadline = new Deadline(tasks.get(num).getTaskDesc());
                    String deadlineString = createRemainingString(inputWords);
                    deadline.setByDateTime(deadlineString);
                    tasks.set(num, deadline);
                    System.out.println("Deadline set: " + deadlineString);
                }
            } else if (inputWords[0].equals("event")) {
                int num = extractIndexToModify(inputWords);
                if (num >= 0) {
                    Event event = new Event(tasks.get(num).getTaskDesc());
                    String eventString = createRemainingString(inputWords);
                    event.setAtDateTime(eventString);
                    tasks.set(num, event);
                    System.out.println("Event set at: " + eventString);
                }
            } else {
                Todo todo = new Todo(input);
                tasks.add(todo);
//                tasks[nextAdd] = temp;
                nextAdd++;
                System.out.println("Added: " + input + ".");
            }
        }
        try {
            writeToFile(tasks);
        } catch (IOException e) {
            //TODO: print some error here
        }

    }




    private static void userCommands() {

        boolean isProgramRunning = true;
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        openFile(tasks);

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
                tasksCRUD(input, tasks);
            }
        }
    }

    private static void openFile(ArrayList<Task> tasks) {

        try {
            file = new File(FILE_PATH);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] splitLine = line.split(" -- ");

                int TASK_TYPE = 0;
                int TASK_ISDONE = 1;
                int TASK_DESC = 2;
                int TASK_ADDN_DESC = 3;

                switch (splitLine[TASK_TYPE]) {
                case "[E]":
                    Event event = new Event(splitLine[TASK_DESC]);
                    event.setPending(splitLine[TASK_ISDONE].equals("true"));
                    event.setAtDateTime(splitLine[TASK_ADDN_DESC]);
                    tasks.add(event);
                    break;
                case "[D]":
                    Deadline deadline = new Deadline(splitLine[TASK_DESC]);
                    deadline.setPending(splitLine[TASK_ISDONE].equals("true"));
                    deadline.setByDateTime(splitLine[TASK_ADDN_DESC]);
                    tasks.add(deadline);
                    break;
                case "[T]":
                    Todo todo = new Todo(splitLine[TASK_DESC]);
                    todo.setPending(splitLine[TASK_ISDONE].equals("true"));
                    tasks.add(todo);
                    break;
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("No save file found. Starting program with blank file.");
        }

    }

    private static void writeToFile(ArrayList<Task> tasks) throws IOException {

        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (Task task : tasks) {
            String sb = task.getType() + " -- " + task.isPending() + " -- " +
                    task.getTaskDesc() + " -- " + task.getAdditionalInfoSave() +
                    System.lineSeparator();
            fileWriter.write(sb);
        }
        fileWriter.close();

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

        StandardMethods.goodBye();

    }


}
