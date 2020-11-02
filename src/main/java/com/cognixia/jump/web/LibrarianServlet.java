package com.cognixia.jump.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.cognixia.jump.dao.BookDao;
import com.cognixia.jump.dao.BookDaoImp;
import com.cognixia.jump.dao.LibrarianDao;
import com.cognixia.jump.dao.LibrarianDaoImp;
import com.cognixia.jump.dao.PatronDao;
import com.cognixia.jump.dao.PatronDaoImp;
import com.cognixia.jump.helper.Helper;
import com.cognixia.jump.model.BookCheckout;
import com.cognixia.jump.model.Librarian;
import com.cognixia.jump.model.Patron;

@WebServlet("/Librarian/*")
public class LibrarianServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private BookCheckoutDaoImp bookCheckoutDaoImp;
	
	private BookDao bookDao;
	private PatronDao patronDao;
	private BookCheckoutDao checkoutDao;
	private LibrarianDao librarianDao;
	private HttpSession session;
    
    @Override
	public void init() {
		
    	bookCheckoutDaoImp = new BookCheckoutDaoImp();
    	
    	bookDao = new BookDaoImp();
		patronDao = new PatronDaoImp();
		checkoutDao = new BookCheckoutDaoImp();
		librarianDao = new LibrarianDaoImp();
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
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		if(action == null) {
			action = request.getServletPath();
		}
		switch(action) {
			case "/checkout":
				getAllCurrentBookCheckouts(request,response);
	
				break;
			case "/available":
				getAvailable(request,response);
				break;
			case "/return":
	
				break;
			case "/history":
	
				break;
			case "/approve":
				approve(request, response);
				break;
			case "/delete":
				delete(request, response);
				break;
			case "/settings":
				goToLibrarianSettings(request, response);
				break;
			case "/edit":
				edit(request, response);
				break;
			default:
				goToLibrarianDashboard(request, response);
				break;
		}
	}
	
	 private void getAllCurrentBookCheckouts(HttpServletRequest request, HttpServletResponse response) 
	 		throws ServletException, IOException {

	 	List<BookCheckout> currentCheckedOutBooks = checkoutDao.getAllCurrentBookCheckouts();
	 	System.out.println("called getAllCurrentBookCheckouts in LibarianServlet, currentCheckedOutBooks = " + currentCheckedOutBooks);
		
	 	request.setAttribute("currentCheckedOutBooks", currentCheckedOutBooks);
		
	 	RequestDispatcher dispatcher = request.getRequestDispatcher("/checkedOutBooksList.jsp");
		System.out.println("sent");
	 	System.out.println("this is the request being sent"+request.getServerName()+request.getLocalPort());
		System.out.println("this is the response being sent"+response.toString());
	 	dispatcher.forward(request, response);
		
		
	 }
	 private void getAvailable(HttpServletRequest request, HttpServletResponse response) 
	 		throws ServletException, IOException {
	 	List<BookCheckout> pastCheckedOutBooks = checkoutDao.getAllPastBookCheckouts();
	 	System.out.println("called getAllPastBookCheckouts in LibarianServlet, pastCheckedOutBooks = " + pastCheckedOutBooks);
		
	 	request.setAttribute("pastCheckedOutBooks", pastCheckedOutBooks);
		
	 	RequestDispatcher dispatcher = request.getRequestDispatcher("/bookcheckout-list.jsp");
	 	System.out.println("sent");
	 	System.out.println("this is the request being sent"+request.getServerName()+request.getLocalPort());
	 	System.out.println("this is the response being sent"+response.toString()); 
	 	dispatcher.forward(request, response);
		
		
	 }

	
	
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("delete - Librarian");
		session = request.getSession();
		
		if(Helper.userCheck(request, session)) {
			checkoutDao.deleteCheckoutByPatron(Integer.parseInt(request.getParameter("patron_id")));
			patronDao.deletePatron(Integer.parseInt(request.getParameter("patron_id")));
			response.sendRedirect("/LibraryCrudProject/Librarian");
			return;
		}
		response.sendRedirect("/LibraryCrudProject/Access/signinPage");
		return;
	}
	
	private void approve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("approve - Librarian");
		session = request.getSession();
		
		if(Helper.userCheck(request, session)) {
			patronDao.approveAccount(patronDao.getPatronById(Integer.parseInt(request.getParameter("patron_id"))));
			response.sendRedirect("/LibraryCrudProject/Librarian");
			return;
		}
		response.sendRedirect("/LibraryCrudProject/Access/signinPage");
		return;
	}
	
	private void goToLibrarianDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("goToLibrarianDashboard - Librarian");
		session = request.getSession();
		
		if(Helper.userCheck(request, session)) {
			Librarian lib = (Librarian) session.getAttribute("user");
			request.setAttribute("frozenPatrons", patronDao.getFrozenPatrons());
			// TODO: Load in all infromation for the current page.
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/librarianDashboard.jsp");
			dispatch.forward(request, response);
			return;
		}
		response.sendRedirect("/LibraryCrudProject/Access/signinPage");
		return;
	}
	
	private void goToLibrarianSettings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("goToLibrarianSettings - Librarian");
		session = request.getSession();
		
		if(Helper.userCheck(request, session)) {
			RequestDispatcher dispatch = request.getRequestDispatcher("/librarianEdit.jsp");
			dispatch.forward(request, response);	
			return;
		}
		response.sendRedirect("/LibraryCrudProject/Access/signinPage");
		return;
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("edit - Librarian");
		session = request.getSession();
		
		if(Helper.userCheck(request, session)) {
			Librarian lib = (Librarian) session.getAttribute("user");

			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String passConf = request.getParameter("passwordConfirmation");
			
			if(username.isEmpty() || password.isEmpty()) {
				request.setAttribute("error", "All fields need to be filled out.");
				goToLibrarianSettings(request, response);
				return;
			}

			lib.setUsername(username);
			lib.setPassword(password);
			
			if(password.equals(passConf)) {
				if(librarianDao.getLibrarianLogin(username) == null || username.equals(lib.getUsername())) {
					librarianDao.updateLibrarian(lib);
					session.setAttribute("user", lib);
					response.sendRedirect("/LibraryCrudProject/Librarian");
					return;
				}
				request.setAttribute("error", "This user already exists.");
				goToLibrarianSettings(request, response);
				return;
			} else {
				request.setAttribute("error", "The Passwords are not the same.");
				goToLibrarianSettings(request, response);
			}
			return;
		}
		response.sendRedirect("/LibraryCrudProject/Access/signinPage");
		return;
	}
}
