package UserInteractions;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static Standard.StandardStrings.SOMETHING_WRONG;

public class TaskList {

    private static int nextAdd = 0;

    /**
     * CRUD method for modifying the {@link ArrayList<Task>} of user tasks
     *
     * @param input user-entered input to the CLI (unparsed)
     * @param tasks list of user tasks
     */
    public static void tasksCRUD(String input, ArrayList<Task> tasks) {

        String[] inputWords = input.split(" ");
        if (inputWords[0].equals("done")) {
            makeTaskDone(inputWords, tasks);
        } else if (inputWords[0].equals("delete")) {
            deleteTask(inputWords, tasks);
        } else {
            if (inputWords[0].equals("deadline")) {
                int num = Parser.extractIndexToModify(inputWords);
                if (num >= 0) {
                    Deadline deadline = new Deadline(tasks.get(num).getTaskDesc(), inputWords);
                    tasks.set(num, deadline);
                    System.out.println("Deadline set: " +
                            deadline.getByDateTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
                }
            } else if (inputWords[0].equals("event")) {
                int num = Parser.extractIndexToModify(inputWords);
                if (num >= 0) {
                    Event event = new Event(tasks.get(num).getTaskDesc(), inputWords);
                    String eventString = Parser.createRemainingString(inputWords);
                    event.setAtDateTime(Parser.parseToDateTime(eventString));
                    tasks.set(num, event);
                    System.out.println("Event set at: " +
                            event.getAtDateTime().format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
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
            Storage.writeToFile(tasks);
        } catch (IOException e) {
            System.out.println("Couldn't save your changes. We'll try again the next time you make a change.");
        }

    }

    /**
     * Marks a selected task as done
     *
     * @param inputWords user input split into words in a {@link String[]} array - contains the index
     *                   of the task to be removed
     * @param tasks list of user tasks
     */
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

    /**
     * Removes the selected task from the task list
     *
     * @param inputWords user input split into words in a {@link String[]} array - contains the index
     *                   of the task to be removed
     * @param tasks list of user tasks
     */
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

    /**
     * Searches through the task list for tasks matching the search term, and passes any matches for printing
     *
     * @param tasks list of user tasks
     * @param searchTerm search term with "find" removed
     */
    public static void findTasks(ArrayList<Task> tasks, String searchTerm) {
        boolean hasFoundTask = false;
        if (tasks.isEmpty()) {
            System.out.println("There are no tasks in your list.");
            return;
        }
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskDesc().contains(searchTerm)) {
                if (!hasFoundTask) {
                    System.out.println("We found the following tasks:");
                    hasFoundTask = true;
                }
                Ui.printSingleTask(tasks.get(i), i);
            }
        }
        if (!hasFoundTask) {
            System.out.println("No tasks matching your search term were found.");
        }
    }



}
