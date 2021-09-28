# Duke Task Manager - User Guide

Duke is a Command-Line Interface (CLI) application for managing tasks.  If you are a fast typist,
you'll find Duke a faster way of managing various tasks as opposed to GUI applications.

## Getting Started

- Install Java 11 on your computer.
- Download the latest version of the program from [here]().
- Open a command line/terminal session. Navigate to the directory where the program resides, and enter `java -jar duke.jar`.

## Features 

Duke supports 3 different task types - `todo`, `deadline` and `event`. `deadline` and `event` task types 
support the addition of date and time information, while `todo` task types do not.

When starting the application, the program will automatically attempt to look for a save file called `tasks.txt` located
in the same directory as your app. If this file cannot be found or cannot be opened, the application will create
a new file by the same name to save your tasks - **this will overwrite any previous save files**.

### Create a new task

To create a new task, simply enter the task name. For example,

`submit assignment`

If successful, you will see:

`Added: submit assignment.`

By default, all new tasks created are of the `todo` type. That means that no date-time information can
be tagged to the task. To add date-time information to a task, see [Change task to deadline or event type](#change-task-to-deadline-or-event-type----deadline---event).

### List all tasks - `-list`

To list all tasks currently saved to your program, type

`-list`

A list of all tasks will be displayed in the CLI. The display format is as follows:

`<tasknumber>. [<tasktype>][<taskdone>] <taskname> (<datetime>)`

For example,

- A `todo` task type that is not yet marked as done: `1. [T][ ] submit assignment`
- A `deadline` task type that is marked as done: `2. [D][X] submit assignment (by: 31 Dec 2021 23:59)`

### Mark tasks as done - `-done`

To mark a task as done, you need to first find out the task number of the task you wish to modify. The easiest
way to do so is to `-list` your tasks (see [List all tasks](#list-all-tasks----list)). You can also do a `-find` if
you know the contents of the task description (see [Find tasks](#find-tasks----find)).

Once you know the task number, type

`-done <task-number>`

If successful, you will see

`Congrats! You've completed this task: [X] <task-name>`

For efficiency, you can chain multiple tasks together to be marked as done. For example,

`-done 1 2 3`

will mark task numbers 1, 2 and 3 as done.

### Change task to `deadline` or `event` type - `-deadline` / `-event`

To add date-time information to a task, you need to change it to a `deadline` or `event` type.

`deadline` type tasks will set the date-time as a "by", while `event` type tasks will set the date-time as an "at".

To change to a `deadline` type, enter

`-deadline <task-number> <date-time>`, where the format of `date-time` must be `dd-MM-yyyy HH:mm`

Similarly, to change to a `event` type, enter

`-event <task-number> <date-time>`, where the format of `date-time` must be `dd-MM-yyyy HH:mm`

An acceptable date-time input would be `29-09-2021 11:00`

Once successful, you will see

`Deadline set: 29 Sep 2021 11:00` or `Event set: 29 Sep 2021 11:00`

**IMPORTANT:** Once a task has been changed away from a `todo` type, it cannot be changed back. However,
you can still change between `deadline` and `event` types.

### Find tasks - `-find`

To find tasks matching a certain word or phrase, type

`-find <search-term>`

Note that your search term must be present exactly as it is entered in the task description (but it does not
have to be in its entirety). For example, for a task with the description `buy noodles for lunch`,
`buy noodles` will return you the task, whereas `buy lunch` will not.

*Tip*: The `-find` feature can also return you the task number of a specific task, which you can use for other functions.

### Delete tasks - `-delete`

To remove a task from the task list, you need to first find out the task number of the task you wish to remove. The easiest
way to do so is to `-list` your tasks (see [List all tasks](#list-all-tasks----list)). You can also do a `-find` if
you know the contents of the task description (see [Find tasks](#find-tasks----find)).

Once you know the task number, type

`-delete <task-number>`

Once successful, you will see

`Task <task-number>: <task-name> has been deleted`

For efficiency, you can chain multiple tasks together to be marked as done. For example,

`-delete 1 2 3`

will remove task numbers 1, 2 and 3.

### Exit the program - `-bye`

To quit the program, type `-bye`.

### Saving tasks to file

The program will attempt to save the list of tasks to the `tasks.txt` file automatically every time a change is made to the list
of tasks. If the save attempt is successful, no message will be displayed.

If the save attempt is unsuccessful, an error will display, and
the program will not attempt to re-save the changes. However, the changes will still
be stored in the current run of the program, so you will see the unsaved changes if you run `-list`.

The program will attempt to re-save automatically the next time a change is made to any tasks in the task list. If you quit
the program without a successful save attempt immediately preceding the exit, any unsaved changes will be lost.

[comment]: <> (## Usage)

[comment]: <> (### `Keyword` - Describe action)

[comment]: <> (Describe the action and its outcome.)

[comment]: <> (Example of usage: )

[comment]: <> (`keyword &#40;optional arguments&#41;`)

[comment]: <> (Expected outcome:)

[comment]: <> (Description of the outcome.)

[comment]: <> (```)

[comment]: <> (expected output)

[comment]: <> (```)
