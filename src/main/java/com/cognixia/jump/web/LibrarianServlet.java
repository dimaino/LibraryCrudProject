package com.cognixia.jump.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.BookCheckoutDaoImp;
import com.cognixia.jump.model.BookCheckout;


/**
 * Servlet implementation class LibrarianServlet
 */
public class LibrarianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookCheckoutDaoImp bookCheckoutDaoImp;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibrarianServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init() {
		
    	bookCheckoutDaoImp = new BookCheckoutDaoImp();
	}
	
	@Override
	public void destroy() {
		
		try {
			ConnectionManager.getConnection().close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
    
    
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getServletPath();
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
			default:
				response.sendRedirect("/");
				break;
				
				
		
		
		
		} //end of switch 
		
		
		
	} //end of doGet()
	
	private void getAllCurrentBookCheckouts(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		List<BookCheckout> currentCheckedOutBooks = bookCheckoutDaoImp.getAllCurrentBookCheckouts();
		System.out.println("called getAllCurrentBookCheckouts in LibarianServlet, currentCheckedOutBooks = " + currentCheckedOutBooks);
		
		request.setAttribute("currentCheckedOutBooks", currentCheckedOutBooks);
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("BookCheckout-list.jsp");
		System.out.println("sent");
		System.out.println("this is the request being sent"+request.getServerName()+request.getLocalPort());
		System.out.println("this is the response being sent"+response.toString());
		
		
	}
	private void getAllPastBookCheckouts(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		List<BookCheckout> pastCheckedOutBooks = bookCheckoutDaoImp.getAllPastBookCheckouts();
		System.out.println("called getAllPastBookCheckouts in LibarianServlet, pastCheckedOutBooks = " + pastCheckedOutBooks);
		
		request.setAttribute("currentCheckedOutBooks", pastCheckedOutBooks);
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("BookCheckout-list.jsp");
		System.out.println("sent");
		System.out.println("this is the request being sent"+request.getServerName()+request.getLocalPort());
		System.out.println("this is the response being sent"+response.toString()); 
		
		
	}

	

}
