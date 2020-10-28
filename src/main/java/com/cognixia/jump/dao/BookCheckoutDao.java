package com.cognixia.jump.dao;

import java.sql.Date;
import java.util.List;

import com.cognixia.jump.model.BookCheckout;


/*
 * 
 * 	private int checkout_id;
	private int patron_id;
	private String isbn;
	private Date checkedout;
	private Date due_date;
	private Date returned;
 *
 *Total of 12 functions 
 */

public interface BookCheckoutDao {
	
	//all the book in the library that has been currently checked out 
	// make sure the due_date is in future and returned = false 
	//using and because some people return before due date 
	//if we don't check returned than we might say its checked out but has already been returned
	public List<BookCheckout> getAllCurrentBookCheckouts();
	
	
	//all the books that had been checked out in the past
	// make sure that the due_date is in the past and returned =true
	//both conditions must be true because having a due date does not guarantee return of book
	//book was checked out in the past but not right now , so its available 
	public List<BookCheckout> getAllPastBookCheckouts();
	
	
	//to check how many books were checked out before certain date 
	public List<BookCheckout> getAllBookCheckoutsByDate( Date date);
	
	//to check how many books were checked out between certain dates 
	public List<BookCheckout> getAllBookCheckoutsBetweenDate( Date date1, Date date2);
	
	//-------------------------------------------------------------------------------------//
	
	//gets all the current books checked out by patron id 
	//public List<BookCheckout> getAllCurrentCheckoutsByPatronId(int patronID, Date date);
	public List<BookCheckout> getAllCurrentCheckoutsByPatronId(int patronID);

	
	public List<BookCheckout> getAllPastCheckoutsByPatronId(int patronID);
	
	public List<BookCheckout> getAllCurrentBookCheckoutsByISBN( String ISBN);
	
	public List<BookCheckout> getAllPastBookCheckoutsByISBN( String ISBN);
	
	//-------------------------------------------------------------------------------------//
	
	
	BookCheckout getBookCheckoutByCheckoutId(int id);
	
	
	
	//add the currently checked out book to the BookCheckout table 
	public boolean addBookCheckout(BookCheckout bookCheckout);
	
	////delete the currently checked out book to the BookCheckout table 
	public boolean deleteBookCheckout(int id);
	
	//BookCheckout table should not be allowed to update !!!
	public boolean updateBookCheckout(BookCheckout bookCheckout);

	
}
