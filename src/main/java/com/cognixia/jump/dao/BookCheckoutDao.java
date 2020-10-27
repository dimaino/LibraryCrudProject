package com.cognixia.jump.dao;

import java.util.List;

import com.cognixia.jump.model.BookCheckout;

public interface BookCheckoutDao {

	public List<BookCheckout> getAllBookCheckouts();
	public BookCheckout getBookCheckoutById(int id);
	public boolean addBookCheckout(BookCheckout bookCheckout);
	public boolean deleteBookCheckout(int id);
	public boolean updateBookCheckout(BookCheckout bookCheckout);
	
	public List<BookCheckout> viewPastCheckedOutBooks();
	public List<BookCheckout> viewCurrentCheckedOutBooks();
}
