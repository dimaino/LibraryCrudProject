package com.cognixia.jump.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.BookCheckoutDao;
import com.cognixia.jump.dao.BookCheckoutDaoImp;
import com.cognixia.jump.dao.BookDaoImp;
import com.cognixia.jump.helper.Helper;
import com.cognixia.jump.model.Book;


	
@WebServlet("/books/*")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookDaoImp bookDao;
	private HttpSession session;
	private BookCheckoutDao checkoutDao;

    @Override
	public void init() {
		
    	bookDao = new BookDaoImp();
    	session = null;
    	checkoutDao = new BookCheckoutDaoImp();
	}
    
    @Override
	public void destroy() {
		try {
			ConnectionManager.getConnection().close();
			session.invalidate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
			case ("delete"):
				delete(request, response);
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
		
		System.out.println("listBooks - book");
		session = request.getSession();
		
		if(Helper.userCheck(request, session)) {
			List<Book> allBooks = bookDao.getAllBooks();
			System.out.println("called listBooks, allBooks = " + allBooks);
			
			request.setAttribute("allBooks", allBooks);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/bookList.jsp");
			System.out.println("sent");
			dispatcher.forward(request, response);
			return;
		}
		response.sendRedirect("/LibraryCrudProject/Access/signinPage");
		return;
	}
	
	
	private void gotoNewBookForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		System.out.println("gotoNewBookForm - book");
		session = request.getSession();
		
		if(Helper.userCheck(request, session)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/bookForm.jsp");
			dispatcher.forward(request, response);
			return;
		}
		response.sendRedirect("/LibraryCrudProject/Access/signinPage");
		return;
	}
	
	private void gotoEditBookForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("gotoEditBookForm - book");
		session = request.getSession();
		
		if(Helper.userCheck(request, session)) {
			String isbn = request.getParameter("isbn");
			Book book = bookDao.getBookByISBN(isbn);
			request.setAttribute("book", book);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/bookForm.jsp");
			dispatcher.forward(request, response);
			return;
		}
		response.sendRedirect("/LibraryCrudProject/Access/signinPage");
		return;
	}
	
	
	private void editBook(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("editBook - book");
		session = request.getSession();
		
		if(Helper.userCheck(request, session)) {
			String isbn = request.getParameter("isbn");
			Book book = bookDao.getBookByISBN(isbn);
			
			String title = request.getParameter("title");
			String descr = request.getParameter("descr");
			
			book.setTitle(title);
			book.setDescr(descr);
			
			if(bookDao.getBookByTitle(title) == null) {
				bookDao.updateBook(book);
				response.sendRedirect("bookList");
				return;
			}
			request.setAttribute("error", "This Title already exists.");
			gotoEditBookForm(request, response);
			return;
		}
		response.sendRedirect("/LibraryCrudProject/Access/signinPage");
		return;
	}
	
	
	private void addNewBook(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		System.out.println("addNewBook - book");
		session = request.getSession();
		
		if(Helper.userCheck(request, session)) {
			String isbn = request.getParameter("isbn");
			String title = request.getParameter("title");
			String descr = request.getParameter("descr");
			Book book = new Book(isbn, title, descr, false, null);
			
			if(bookDao.getBookByISBN(isbn) == null) {
				if(bookDao.getBookByTitle(title) == null) {
					if(isbn.length() == 10) {
						bookDao.addBook(book);
						response.sendRedirect("bookList");
						return;
					}
					request.setAttribute("error", "The ISBN needs to be 10 characters long.");
					gotoNewBookForm(request, response);
					return;
				}
				request.setAttribute("error", "This Title already exists.");
				gotoNewBookForm(request, response);
				return;
			}
			request.setAttribute("error", "This ISBN already exists.");
			gotoNewBookForm(request, response);
			return;
		}
		response.sendRedirect("/LibraryCrudProject/Access/signinPage");
		return;
	}
	
	private void gotoViewBook(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		System.out.println("gotoViewBook - book");
		session = request.getSession();
		
		if(Helper.userCheck(request, session)) {
			String isbn = request.getParameter("isbn");
			Book book = bookDao.getBookByISBN(isbn);
			
			request.setAttribute("book", book);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/book.jsp");
			dispatcher.forward(request, response);
			return;
		}
		response.sendRedirect("/LibraryCrudProject/Access/signinPage");
		return;
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("delete - book");
		session = request.getSession();
		
		if(Helper.userCheck(request, session)) {
			String isbn = request.getParameter("isbn");
			checkoutDao.deleteCheckoutByBook(isbn);
			
			bookDao.deleteBook(isbn);
			
			response.sendRedirect("bookList");
			return;
		}
		response.sendRedirect("/LibraryCrudProject/Access/signinPage");
		return;
	}
}
