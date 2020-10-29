package com.cognixia.jump.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.dao.BookCheckoutDaoImp;
import com.cognixia.jump.dao.BookDaoImp;
import com.cognixia.jump.model.Book;


	
@WebServlet("/books/*")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDaoImp bookDao;

    @Override
	public void init() {
		
    	bookDao = new BookDaoImp();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getPathInfo().substring(1);
		String fullUrl = request.getRequestURI();

		System.out.println("HELLLO THE SERVLET PATH IS "+action);
		System.out.println("HELLLO THE fulLURL PATH IS "+fullUrl);

		switch(action) {
		
			case("bookList"):
				listBooks(request, response);
				break;
			case("new"):
				gotoNewBookForm(request, response);
				break;
			case("edit"):
				gotoEditBookForm(request, response);
				break;
			case("update"):
				editBook(request, response);
				break;
			case ("insert"):
				addNewBook(request,response);
				break;
			case ("view"):
				gotoViewBook(request,response);
				break;
			default:
				response.sendRedirect("/");
				break;
						
		} //end of switch 
//		
		
	} //end of doGet()
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	private void listBooks(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<Book> allBooks = bookDao.getAllBooks();
		System.out.println("called listBooks, allBooks = " + allBooks);
		
		request.setAttribute("allBooks", allBooks);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/bookList.jsp");
		System.out.println("sent");
		dispatcher.forward(request, response);
	}
	
	
	private void gotoNewBookForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/bookForm.jsp");
		dispatcher.forward(request, response);
	}
	
	private void gotoEditBookForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String isbn = request.getParameter("isbn");
		Book book = bookDao.getBookByISBN(isbn);
		request.setAttribute("book", book);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/bookForm.jsp");
		dispatcher.forward(request, response);
		
	}
	
	
	private void editBook(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String isbn = request.getParameter("isbn");
		
		String title = request.getParameter("title");
		String descr = request.getParameter("descr");
		String description = request.getParameter("description");
		Book book = new Book(isbn, title, descr, false, null);
		
		if(bookDao.updateBook(book)) {
			System.out.println("Updated book: " + book);
		}
		
		response.sendRedirect("bookList");
	}
	
	
	private void addNewBook(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String isbn = request.getParameter("isbn");
		String title = request.getParameter("title");
		String descr = request.getParameter("descr");
		Book book = new Book(isbn, title, descr, false, null);
		
		if(bookDao.addBook(book)) {
			System.out.println("Updated book: " + book);
		}
		
		response.sendRedirect("bookList");
	}
	
	private void gotoViewBook(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		String isbn = request.getParameter("isbn");
		Book book = bookDao.getBookByISBN(isbn);
		
		request.setAttribute("book", book);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/book.jsp");
		dispatcher.forward(request, response);
		

	}

}
