public class Duke {

    private static void levelZero() {
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        System.out.println("-------------------------------------");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("-------------------------------------");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("");
        System.out.println("---------------------");
        System.out.println("");

        levelZero();

    }
}
