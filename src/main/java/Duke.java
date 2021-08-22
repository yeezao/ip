import java.util.Scanner;

public class Duke {

    private static void firstGreet() {
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        System.out.println("-------------------------------------");
    }

    private static void userCommands() {

        boolean isProgramRunning = true;
        Scanner scanner = new Scanner(System.in);
        Task[] storage = new Task[100];
        int nextAdd = 0;

        while (isProgramRunning) {
            System.out.print("Enter your command here: ");
            String input = scanner.nextLine();
            if (input.contains("done")) {
                String[] words = input.split(" ");
                for (int i = 1; i < words.length; i++) {
                    int num = Integer.parseInt(words[i]) - 1;
                    storage[num].markAsDone();
                    System.out.println("Congrats! You've completed this task: [X] " + storage[num].getTaskDesc());
                }
            } else if (input.equals("bye")) {
                isProgramRunning = false;
            } else if (input.equals("list")) {
                for (int i = 0; i < nextAdd; i++) {
                    String done = storage[i].isPending() ? "[ ]" : "[X]";
                    System.out.println((i + 1) + ": " + done + " " + storage[i].getTaskDesc());
                }
            } else {
                Task temp = new Task(input);
                storage[nextAdd] = temp;
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

    public static class Task {

        boolean isPending = true;
        String taskDesc;

        public Task() {
            //empty constructor
        }

        public Task(String taskDesc) {
            this.taskDesc = taskDesc;
        }

        public boolean isPending() {
            return isPending;
        }

        public void setPending(boolean isPending) {
            this.isPending = isPending;
        }

        public String getTaskDesc() {
            return taskDesc;
        }

        public void setTaskDesc(String taskDesc) {
            this.taskDesc = taskDesc;
        }

        public void markAsDone() {
            this.isPending = false;
        }
    }
}
