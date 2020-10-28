package com.cognixia.jump.dao;

import java.util.List;

import com.cognixia.jump.model.Librarian;
import com.cognixia.jump.model.Patron;

public interface LibrarianDao {

	public List<Librarian> getAllLibrarians();
	public Librarian getLibrarianById(int id);
	public boolean addLibrarian(Librarian librarian);
	public boolean deleteLibrarian(int id);
	public boolean updateLibrarian(Librarian librarian);
	
	public Librarian getLibrarianLogin(String username, String password);
}
