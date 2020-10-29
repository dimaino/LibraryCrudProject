package com.cognixia.jump.dao;

import java.util.List;

import com.cognixia.jump.model.Patron;

public interface PatronDao {

	public List<Patron> getAllPatrons();
	public Patron getPatronById(int id);
	public boolean addPatron(Patron patron);
	public boolean deletePatron(int id);
	public boolean updatePatron(Patron patron);

	public boolean approveAccount(Patron patron);
	public boolean freezeAccount(Patron patron);
	public Patron getPatronLogin(String username);
}
