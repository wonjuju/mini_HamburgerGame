package model;

public class User {
	private String Id;
	private String password;
	private int salary;

	public User(String Id, String password) {
		this.Id = Id;
		this.password = password;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getSalary() {
		return salary;
	}

	public User(String id, int salary) {
		this.Id = id;
		this.salary = salary;
	}
	
	public User(String id) {
		this.Id = Id;
	}
	



}
