package com.cognixia.jump.connection;

import java.sql.Date;

import com.cognixia.jump.dao.BookCheckoutDao;
import com.cognixia.jump.dao.BookCheckoutDaoImp;
import com.cognixia.jump.dao.BookDao;
import com.cognixia.jump.dao.BookDaoImp;
import com.cognixia.jump.dao.LibrarianDao;
import com.cognixia.jump.dao.LibrarianDaoImp;
import com.cognixia.jump.dao.PatronDao;
import com.cognixia.jump.dao.PatronDaoImp;
import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.Librarian;
import com.cognixia.jump.model.Patron;

public class TestStuuf {
	public static void main(String[] args) {
		PatronDao pDAO = new PatronDaoImp();
		LibrarianDao lDAO = new LibrarianDaoImp();
		BookDao bDAO = new BookDaoImp();
		BookCheckoutDao bcDAO = new BookCheckoutDaoImp();
//		pDAO.approveAccount(pDAO.getPatronById(1));
//		pDAO.freezeAccount(pDAO.getPatronById(1));
//		System.out.println(pDAO.getAllPatrons());
//		System.out.println(pDAO.getPatronById(10));
//		System.out.println(pDAO.addPatron(new Patron(-1, "Daniel", "Imaino", "dimaino21", "password", true)));
//		System.out.println(pDAO.deletePatron(10));
//		Patron p = pDAO.getPatronById(11);
//		p.setFirst_name("WOWZER");
//		p.setLast_name("BLAM");
//		p.setUsername("USERNAME!");
//		System.out.println(pDAO.updatePatron(p));
//		System.out.println(pDAO.getAllPatrons());
		
		
		
//		System.out.println(lDAO.getAllLibrarians());
//		System.out.println(lDAO.getLibrarianById(1));
//		Librarian lib = new Librarian(-1, "testUsername1", "testPassword1");
//		System.out.println(lDAO.addLibrarian(lib));
//		System.out.println(lDAO.deleteLibrarian(4));
//		Librarian upLib = lDAO.getLibrarianById(1);
//		upLib.setUsername("TestNameUser12");
//		upLib.setPassword("NotAPassword...");
//		System.out.println(lDAO.updateLibrarian(upLib));
//		System.out.println(lDAO.getAllLibrarians());
		
//		System.out.println(bDAO.getAllBooks());
//		System.out.println(bDAO.getBookByISBN("1234567896"));
//		Book book = new Book("9934567896", "Some Book", "Stuff about some book!", false, new Date(System.currentTimeMillis()));
//		System.out.println(bDAO.addBook(book));
//		System.out.println(bDAO.getAllBooks());
//		System.out.println(bDAO.deleteBook("1234567896"));
//		System.out.println(bDAO.getBookByISBN("9934567896"));
//		Book book = bDAO.getBookByISBN("9934567896");
//		book.setDescr("NEW DESCRIPTION");
//		book.setTitle("THE TITTLE");
//		System.out.println(bDAO.returnBook(book));
//		System.out.println(bDAO.getBookByISBN("9934567896"));
//		System.out.println(bDAO.checkoutBook(book));
//		System.out.println(bDAO.getBookByISBN("9934567896"));
//		System.out.println(bDAO.updateBook(book));
//		System.out.println(bDAO.getBookByISBN("9934567896"));
//		System.out.println(bDAO.getAllBooks());
		
	}
}
