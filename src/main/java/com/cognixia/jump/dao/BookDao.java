package com.cognixia.jump.dao;

import java.util.List;

import com.cognixia.jump.model.Book;

public interface BookDao {
	
	public List<Book> getAllBooks();
	public Book getBookByISBN(String isbn);
	public boolean addBook(Book book);
	public boolean deleteBook(String isbn);
	public boolean updateBook(Book book);
	
	public boolean returnBook(Book book);
	public boolean checkoutBook(Book book);
	public Book getBookByTitle(String title);
}
