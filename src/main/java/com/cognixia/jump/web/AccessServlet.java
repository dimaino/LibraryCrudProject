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

@WebServlet("/AccessServlet/*")
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
		System.out.println("HEre");
		switch(action) {
			case "/signupPage":
				goToSignupForm(request, response);
				break;
			case "/signup":
				signup(request, response);
				break;
			case "/signinPage":
				goToSigninForm(request, response);
				break;
			case "/signin":
				signin(request, response);
				break;
			case "/logout":
				goToLogout(request, response);
				break;
			default:
				response.sendRedirect("/LibraryCrudProject");
				break;
		}
	}
	
	private void goToSignupForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher("/signup.jsp");
		dispatch.forward(request, response);
	}
	
	private void goToSigninForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher("/login.jsp");
		dispatch.forward(request, response);
	}
	
	private void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Trying to sign up");
	}
	
	private void signin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("uname").trim().toLowerCase();
		String password = request.getParameter("psw").trim();
		response.setContentType("text/html");
		
		session = request.getSession();  

		Patron pat = null;
		Librarian lib = null;
		
		if((pat = patronDao.getPatronLogin(username, password)) != null) {
			session.setAttribute("user", pat);
			System.out.println("User found in the database!");
			response.sendRedirect("/LibraryCrudProject/PatronServlet");
		} else if((lib = librarianDao.getLibrarianLogin(username, password)) != null) {
			session.setAttribute("user", lib);
			System.out.println("Librarian found in the database!");
			response.sendRedirect("/LibraryCrudProject/LibrarianServlet");
		} else {
			System.out.println("Incorrect username or password!");
			goToSigninForm(request, response);
		}
	}
	
	private void goToLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(session != null) {
			session.invalidate();
		}
		response.sendRedirect("/LibraryCrudProject");
	}
}