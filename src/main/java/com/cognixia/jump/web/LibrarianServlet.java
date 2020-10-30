package com.cognixia.jump.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.dao.BookCheckoutDao;
import com.cognixia.jump.dao.BookCheckoutDaoImp;
import com.cognixia.jump.dao.BookDao;
import com.cognixia.jump.dao.BookDaoImp;
import com.cognixia.jump.model.BookCheckout;
import com.cognixia.jump.model.Librarian;





/**
 * Servlet implementation class LibraryServlet
 */

@WebServlet("/LibrarianServlet/*")
public class LibrarianServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private HttpSession session;
	private BookCheckoutDao checkoutDao;
	private BookDao bookDao;
	
	@Override
	public void init() {
		
		
		checkoutDao = new BookCheckoutDaoImp();
		bookDao = new BookDaoImp();

		
		
	}
	
       
  
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getPathInfo();
		
		if(action == null) {
			action = request.getServletPath();
		}

		//String action = "library/listCurrentBookCheckouts";
		String fullUrl = request.getRequestURI();
		System.out.println("HELLLO THE SERVLET PATH IS "+action);
		System.out.println("HELLLO THE SERVLET FULL PATH IS "+fullUrl);

		
		switch(action) {
	
		case("/listCurrentBookCheckouts"):
			getAllCurrentBookCheckouts(request,response);
			break;
		case("/listPastBookCheckouts"):
			getAllPastBookCheckouts(request,response);
			break;
		default: // default will take you to the goToLibrarianDashboard() function
			//This function will take you to the librarianDashboard.jsp page 
			goToLibrarianDashboard(request,response);
			break;
		}
		
	}
	
	private void goToLibrarianDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		
		if(session != null) {
			if(session.getAttribute("user") != null) {
				//Librarian lib = (Librarian) session.getAttribute("user");
				
				RequestDispatcher dispatch = request.getRequestDispatcher("/librarianDashboard.jsp");
				dispatch.forward(request, response);	
			} else {
				response.sendRedirect("/LibraryCrudProject/AccessServlet/signinPage");
			}
		} else {
			response.sendRedirect("/LibraryCrudProject/AccessServlet/signinPage");
		}
	}
	
	private void getAllCurrentBookCheckouts(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		List<BookCheckout> currentCheckedOutBooks = checkoutDao.getAllCurrentBookCheckouts();
		System.out.println("called getAllCurrentBookCheckouts in LibarianServlet, currentCheckedOutBooks = " + currentCheckedOutBooks);
		
		request.setAttribute("currentCheckedOutBooks", currentCheckedOutBooks);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/bookcheckout-list.jsp");
		System.out.println("sent");
		System.out.println("this is the request being sent"+request.getServerName()+request.getLocalPort());
		System.out.println("this is the response being sent"+response.toString());
		dispatcher.forward(request, response);
		
		
	}
	private void getAllPastBookCheckouts(HttpServletRequest request, HttpServletResponse response) 
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
	/*
	private void updateBookCheckouts(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String item = request.getParameter("item");
		int qty = Integer.parseInt(request.getParameter("qty"));
		String description = request.getParameter("description");
		
		Product product = new Product(id, item, qty, description);
		
		if ( productDao.updateProduct(product) ) {
			System.out.println("Updated product: " + product);
		}
		
		response.sendRedirect("list");
		
		
	}
	*/

	


}
