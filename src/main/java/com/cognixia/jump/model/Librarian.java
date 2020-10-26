package com.cognixia.jump.model;

public class Librarian {

	private int librarian_id;
	private String username;
	private String password;

	public Librarian(int librarian_id, String username, String password) {
		super();
		this.librarian_id = librarian_id;
		this.username = username;
		this.password = password;
	}

	public int getLibrarian_id() {
		return librarian_id;
	}

	public void setLibrarian_id(int librarian_id) {
		this.librarian_id = librarian_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Librarian [librarian_id=" + librarian_id + ", username=" + username + ", password=" + password + "]";
	}
}
