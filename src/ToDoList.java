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

				if (sortedList[j - 1].isEarlier(sortedList[j])) {
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
		for (Task task : this.list) {
			if (task.getTaskID() == taskID) {
				this.list.remove(task);
			}
		}
	}

	public void deleteAllTasks() {
		this.list.clear();
	}

	public void printList(Task[] tasks) {
		System.out.println("-------------------------");
		for (Task task : tasks) {
			System.out.println(task.toString());
		}
		System.out.println("-------------------------");
	}

	public static void displayMenu() {
		System.out.println("####################");
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

		//Defines a variable that keeps the loop going until user exits
		boolean running = true;
		while (running) {
			displayMenu();
			int choice = in.nextInt();
			switch (choice) {
				case 1:
					//Add task
					break;

				case 2:
					//List all tasks
					break;

				case 3:
					//Delete a task
					break;

				case 4:
					//Delete all tasks

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
		//Checks if the other date is earlier than this one's year
		if (date.year <= this.year) {
			//If it is, narrow down to month
			if (date.month <= this.month) {
				//If it is again, narrow down to the day, then return the final result
				if (date.day <= this.day) {
					return false;
				}
			}
		}
		
		return true;
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
		//First checks if the other time's hour is earlier than this one
		if (time.hour <= this.hour) {
			//If it is, narrow it down to checking if the other time's min is earlier than this one
			if (time.min <= this.min) {
				return false;
			}
		}
		
		return true;
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
		System.out.printf("* Task %d: %s, %s, %s, %s", this.taskID, this.title, this.date.toString(), this.time.toString(), this.location);
	}

	//Returns true if this task is earlier than task provided as parameter.
	public boolean isEarlier(Task task) {
		//If the other task's date is earlier than this task's date, it must be earlier
		if (this.date.isEarlier(task.getDate())) {
			return true;
		//However, if the dates are the same, narrow the check down to the tasks' times
		} else if (!this.date.isEarlier(task.getDate()) && !task.getDate().isEarlier(this.date)) {
			return this.date.isEarlier(task.date);
		}
		return false;
	}

}