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

import static Standard.StandardStrings.FILE_PATH;

public class Storage {

    /**
     * Attempts to open the save file with the user's list of tasks. If there is an error opening the file,
     * the method returns the same empty {@link ArrayList<Task>} that was sent into the method.
     *
     * @param tasks empty {@link ArrayList<Task>} to be populated by save file information
     * @return the populated {@link ArrayList<Task>} (if save file is succesfully read from) or an empty
     */
    protected static ArrayList<Task> openFile(ArrayList<Task> tasks) {

        try {
            File file = new File(FILE_PATH);
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
                    event.setAtDateTime(Parser.parseToDateTime(splitLine[TASK_ADDN_DESC]));
                    tasks.add(event);
                    break;
                case "[D]":
                    Deadline deadline = new Deadline(splitLine[TASK_DESC]);
                    deadline.setPending(splitLine[TASK_ISDONE].equals("true"));
                    deadline.setByDateTime(Parser.parseToDateTime(splitLine[TASK_ADDN_DESC]));
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

        return tasks;

    }

    /**
     * Writes any changes to the save file
     *
     * @param tasks {@link ArrayList<Task>} of user's tasks to be read from
     * @throws IOException - save will be aborted
     */
    protected static void writeToFile(ArrayList<Task> tasks) throws IOException {

        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (Task task : tasks) {
            String sb = task.getType() + " -- " + task.isPending() + " -- " +
                    task.getTaskDesc() + " -- " + task.getAdditionalInfoSave() +
                    System.lineSeparator();
            fileWriter.write(sb);
        }
        fileWriter.close();

    }

}
