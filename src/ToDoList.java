/*------------------------------------------------------
My name: Matthew Archer
My subject code: CSIT111
My student number: 6093887
My email address: ma674@uow.edu.au
Assignment number: 3
-------------------------------------------------------*/

import java.util.ArrayList;

class ToDoList {
	
	private ArrayList<Task> list = new ArrayList<>();
	
	public ToDoList() {};
	
	public static void main(String[] args) {
		ToDoList toDoList = new ToDoList();
	}
	
}

class Date {
	
	private int day;
	private int month;
	private int year;
	
	public Date() {
		this(0, 0, 0);
	}
	
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
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
	
	@Override
	public String toString() {
		return String.format("%d/%d/%d", this.day, this.month, this.year);
	}
	
	public boolean isEarlier(Date date) {
		if (date.year >= this.year) {
			if (date.month >= this.month) {
				if (date.day >= this.day) {
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
	
	public Time() {
		this(0, 0);
	}
	
	public Time(int hour, int min) {
		this.hour = hour;
		this.min = min;
	}
	
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
	
	@Override
	public String toString() {
		return String.format("%d:%d", this.hour, this.min);
	}
	
	public boolean isEarlier(Time time) {
		if (time.hour <= this.hour) {
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
	
	public Task() {
	
	}

}