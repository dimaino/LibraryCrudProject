package com.cognixia.jump.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LibrarianServlet
 */
public class LibrarianServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibrarianServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		
			case("/list"):
				listBookCheckouts(request,response);
				
				
		
		
		
		}
		
		
		
	}
	
	private void listBookCheckouts(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		//call the BookCheckoutDaoImp.java list function 
		
		
	}

	

}
