/*------------------------------------------------------
My name: Matthew Archer
My subject code: CSIT111
My student number: 6093887
My email address: ma674@uow.edu.au
Assignment number: 3
-------------------------------------------------------*/

import java.util.ArrayList;
import java.util.Scanner;

class ToDoList {

	private ArrayList<Task> list;

	public ToDoList() {
		//Initialize arraylist
		this.list = new ArrayList<>();
	}

	public Task[] getSortedList() {
		//Create empty array the size of the current arraylist's size
		Task[] sortedList = new Task[this.list.size()];

		//Fill the new list with the unordered values from the arraylist
		for (int i = 0; i < this.list.size(); i++) {
			sortedList[i] = this.list.get(i);
		}

		//Perform a bubble sort of the new 1-D list
		Task temp;
		for (int i = 0; i < sortedList.length; i++) {
			for (int j = 1; j < (sortedList.length - i); j++) {

				if (sortedList[j].isEarlier(sortedList[j - 1])) {
					temp = sortedList[j - 1];
					sortedList[j - 1] = sortedList[j];
					sortedList[j] = temp;
				}

			}
		}

		return sortedList;
	}

	public void addTask(Task task) {
		this.list.add(task);
	}

	public void deleteTask(int taskID) {
		//For each task in the task list, check if the ID given is equal to the task's ID. If it is, remove that task from the list
		//Note, it doesn't use a for-each loop because you can't delete an object while it is being used by the loop
		for (int i = 0; i < this.list.size(); i++) {
			if (this.list.get(i).getTaskID() == taskID) {
				this.list.remove(i);
			}
		}
	}

	public void deleteAllTasks() {
		this.list.clear();
	}

	public int getTaskNumber() {
		return this.list.size();
	}

	public void printList(Task[] tasks) {
		System.out.println("-------------------------");
		for (Task task : tasks) {
			task.printTask();
		}
		System.out.println("-------------------------");
	}

	public static void displayMenu() {
		System.out.println("\n####################");
		System.out.println("1: Add 	a task");
		System.out.println("2: List all tasks");
		System.out.println("3: Delete a task");
		System.out.println("4: Delete all tasks");
		System.out.println("5: Exit the program");
		System.out.println("####################");
		System.out.print("Please select an action from above: ");
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		//Defines the To-Do List instance
		ToDoList toDoList = new ToDoList();

		Task task1 = new Task(new Time(15, 30), new Date(15, 12, 1999), "Finish assignment", "Home", toDoList.getTaskNumber() + 1);
		toDoList.addTask(task1);
		Task task2 = new Task(new Time(5, 45), new Date(16, 12, 1999), "Do exam", "UOW", toDoList.getTaskNumber() + 1);
		toDoList.addTask(task2);
		Task task3 = new Task(new Time(1, 30), new Date(8, 3, 2000), "Dab then yeet", "Swagcity", toDoList.getTaskNumber() + 1);
		toDoList.addTask(task3);

		//Defines a variable that keeps the loop going until user exits
		boolean running = true;
		//Main loop runs until user exists
		while (running) {
			//Displays the menu, and then switches the number to user inputs to choose which action to do
			displayMenu();
			int choice = in.nextInt();
			switch (choice) {
				case 1:
					//Add a task
					//in.nextLine() for this case, and all cases after this, is used to clear the buffer of the scanner
					in.nextLine();
					System.out.print("Please input the new task title: ");
					String taskTitle = in.nextLine();
					System.out.print("Please input the new task date (dd mm yyyy): ");
					int taskDay = in.nextInt();
					int taskMonth = in.nextInt();
					int taskYear = in.nextInt();
					in.nextLine();
					System.out.print("Please input the starting time (hh mm): ");
					int taskHour = in.nextInt();
					int taskMin = in.nextInt();
					in.nextLine();
					System.out.print("Please input the location: ");
					String taskLocation = in.nextLine();

					//Creates a new task using all the values that were input by the user, and the To-Do List's task number to get the task ID
					Task newTask = new Task(new Time(taskHour, taskMin), new Date(taskDay, taskMonth, taskYear), taskTitle, taskLocation, toDoList.getTaskNumber() + 1);
					//Adds the new task to the list
					toDoList.addTask(newTask);

					//Display the sorted tasks
					System.out.printf("Task %d is added. The To-Do list is as follows:\n", newTask.getTaskID());
					toDoList.printList(toDoList.getSortedList());

					break;

				case 2:
					//List all tasks
					toDoList.printList(toDoList.getSortedList());
					break;

				case 3:
					//Delete a task
					toDoList.printList(toDoList.getSortedList());
					in.nextLine();
					System.out.print("Please input the task ID to be deleted: ");
					int taskID = in.nextInt();
					toDoList.deleteTask(taskID);
					System.out.printf("\nTask %d is deleted. The To-Do list is as follow:\n", taskID);
					toDoList.printList(toDoList.getSortedList());
					break;

				case 4:
					//Delete all tasks
					toDoList.deleteAllTasks();
					System.out.println("All tasks deleted. The list is empty");
					break;
				case 5:
					//Exit program
					System.out.println("Thank you for using my To-Do List program, Goodbye!");
					running = false;
					break;
			}
		}
	}
	
}

class Date {
	
	private int day;
	private int month;
	private int year;

	//Default constructor, leaves class attributes as close to "blank" as possible
	public Date() {
		this(0, 0, 0);
	}
	
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	//Following methods are accessors and mutators for day, month, and year attributes
	public int getDay() {
		return this.day;
	}
	
	public int getMonth() {
		return this.month;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public void setDay(int day) {
		this.day = day;
	}
	
	public void setMonth(int month) {
		this.month = month;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	//Returns date in DD/MM/YYYY format
	public String toString() {
		return String.format("%d/%d/%d", this.day, this.month, this.year);
	}

	//Returns whether this date is earlier than another date provided as parameter. If they are the same, returns false
	public boolean isEarlier(Date date) {
		//If the years are the same, narrow the comparison down the months
		if (this.year == date.year) {
			//Repeat the same thing as years, but narrowed down for months
			if (this.month == date.month) {
				if (this.day == date.day) {
					//Returns false because if they are the same date, then one is not earlier than the other
					return false;
				} else if (this.day < date.day) {
					return true;
				} else {
					return false;
				}
			} else if (this.month < date.month) {
				return true;
			} else {
				return false;
			}
		//If this year is before the other year, than it is earlier
		} else if (this.year < date.year) {
			return true;
		//Otherwise, it is not earlier
		} else {
			return false;
		}
	}
}

class Time {
	
	private int hour;
	private int min;

	//Default constructor for time
	public Time() {
		this(0, 0);
	}
	
	public Time(int hour, int min) {
		this.hour = hour;
		this.min = min;
	}

	//Accessors and mutator methods for hour and min attributes
	public int getHour() {
		return this.hour;
	}
	
	public int getMin() {
		return this.min;
	}
	
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	public void setMin(int min) {
		this.min = min;
	}
	
	//Returns time in HH:MM format
	public String toString() {
		return String.format("%d:%d", this.hour, this.min);
	}

	//Returns whether or not this time is earlier than the time provided as a parameter. Returns false if both same
	public boolean isEarlier(Time time) {
		//If they are the same hour, narrow the comparison down to the minute
		if (this.hour == time.hour) {
			//Exact same process, but for min
			if (this.min == time.min) {
				//Returns false because if they're the same time, one is not earlier than the other
				return false;
			} else if (this.min < time.min) {
				return true;
			} else {
				return false;
			}
		//If this hour is less than the other hour, it is earlier, so return true
		} else if (this.hour < time.hour) {
			return true;
		//Otherwise, the time isn't earlier
		} else {
			return false;
		}
	}
	
}

class Task {

	private Time time;
	private Date date;
	private String title;
	private String location;
	private int taskID;

	//Default constructor leaves attributes blank
	public Task() {
		this(null, null, null, null, 0);
	}

	public Task(Time time, Date date, String title, String location, int taskID) {
		this.time = time;
		this.date = date;
		this.title = title;
		this.location = location;
		this.taskID = taskID;
	}

	//Following are accessors and mutators for time, date, title, location, and taskID attributes
	public Time getTime() {
		return this.time;
	}

	public Date getDate() {
		return this.date;
	}

	public String getTitle() {
		return this.title;
	}

	public String getLocation() {
		return this.location;
	}

	public int getTaskID() {
		return this.taskID;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	//Prints out the current task in the format of “* Task ID: title, dd/mm/yyyy, hh:mm, location”
	public void printTask() {
		System.out.printf("* Task %d: %s, %s, %s, %s\n", this.taskID, this.title, this.date.toString(), this.time.toString(), this.location);
	}

	//Returns true if this task is earlier than task provided as parameter.
	public boolean isEarlier(Task task) {
		//If the other task's date is earlier than this task's date, it must be earlier
		if (this.date.isEarlier(task.getDate())) {
			return true;
		//However, if the dates are the same, narrow the check down to the tasks' times
		} else if (!this.date.isEarlier(task.getDate()) && !task.getDate().isEarlier(this.date)) {
			return this.time.isEarlier(task.time);
		}
		return false;
	}

}