package UserInteractions;

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
                    String[] eventWords = splitLine[TASK_ADDN_DESC].split(" ");
                    Event event = new Event(splitLine[TASK_DESC], eventWords);
                    event.setPending(splitLine[TASK_ISDONE].equals("true"));
                    tasks.add(event);
                    break;
                case "[D]":
                    String[] deadlineWords = splitLine[TASK_ADDN_DESC].split(" ");
                    Deadline deadline = new Deadline(splitLine[TASK_DESC], deadlineWords);
                    deadline.setPending(splitLine[TASK_ISDONE].equals("true"));
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
