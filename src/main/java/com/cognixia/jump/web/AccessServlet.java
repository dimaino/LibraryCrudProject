package com.cognixia.jump.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.LibrarianDao;
import com.cognixia.jump.dao.LibrarianDaoImp;
import com.cognixia.jump.dao.PatronDao;
import com.cognixia.jump.dao.PatronDaoImp;
import com.cognixia.jump.model.Librarian;
import com.cognixia.jump.model.Patron;

//@WebServlet("/")
public class AccessServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	private PatronDao patronDao;
	private LibrarianDao librarianDao;
	private HttpSession session;

	@Override
    public void init() {
		patronDao = new PatronDaoImp();
		librarianDao = new LibrarianDaoImp();
		session = null;
    }
	
	@Override
	public void destroy() {
		try {
			ConnectionManager.getConnection().close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("HERE in Access");
		String action = request.getPathInfo();
		String fullUrl = request.getRequestURI();
		
		System.out.println(action + " THIS is the action");

		switch(action) {
			case "/signupPage":
				
				goToSignupForm(request, response);
				break;
			case "/signup":
				signup(request, response);
				break;
			case "/signinPage":
				System.out.println("signinPage");
				goToSigninForm(request, response);
				break;
			case "/signin":
				signin(request, response);
				break;
//			case "/patronDashboard":
//				System.out.println("Patron Dashboard Hit.");
//				response.sendRedirect("/LibraryCrudProject/PatronServlet");
////				goToPatronDashboard(request, response);
//				break;
			case "/librarianDashboard":
				goToLibrarianDashboard(request, response);
				break;
			case "/logout":
				goToLogout(request, response);
//				if (request.getParameter("logout") != null) {  
//				    session.invalidate();
//				}
				response.sendRedirect("/");
				break;
			case "/list":
				System.out.println("BAD LIST");
				break;
			case "/test":
				System.out.println("test");
				break;
			case "/test2":
				System.out.println("test2");
				break;
			default:
				response.sendRedirect("/LibraryCrudProject");
				break;
		}
	}
	
	private void goToSignupForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("WE HEre");
		RequestDispatcher dispatch = request.getRequestDispatcher("AccessServlet/signup.jsp");
		dispatch.forward(request, response);
	}
	
	private void goToSigninForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
		dispatch.forward(request, response);
	}
	
	private void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void signin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("uname").trim().toLowerCase();
		String password = request.getParameter("psw").trim();
		
		response.setContentType("text/html");
		
		// This will track which user is logged in.
		session = request.getSession();  

		Patron pat = null;
		Librarian lib = null;
		
		if((pat = patronDao.getPatronLogin(username, password)) != null) {
			session.setAttribute("user", pat);
			System.out.println("User found in the database!");
//			response.sendRedirect("patronDashboard");
			response.sendRedirect("/LibraryCrudProject/PatronServlet");
		} else if((lib = librarianDao.getLibrarianLogin(username, password)) != null) {
			session.setAttribute("user", lib);
			System.out.println("Librarian found in the database!");
			response.sendRedirect("librarianDashboard");
		} else {
			System.out.println("Incorrect username or password!");
			goToSigninForm(request, response);
		}
	}
	
	
//	private void goToPatronDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		if(session != null) {
//			if(session.getAttribute("user") != null) {
////				RequestDispatcher dispatch = request.getRequestDispatcher("patronDashboard.jsp");
////				dispatch.forward(request, response);	
//				response.sendRedirect("/LibraryCrudProject/patronDashboard");
//			}
//		} else {
//			response.sendRedirect("/LibraryCrudProject/signinPage");
//		}
//	}
	
	private void goToLibrarianDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher("librarianDashboard.jsp");
		dispatch.forward(request, response);
	}
	
	private void goToLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("logout");
	}
}