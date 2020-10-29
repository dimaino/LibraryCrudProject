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
import com.cognixia.jump.helper.Helper;
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
		String fullUrl = request.getRequestURI();
		System.out.println(fullUrl);
		System.out.println("AccessServlet");
		switch(action) {
			case "/signupPage":
				System.out.println("IN signupPage");
				goToSignupForm(request, response);
				break;
			case "/signup":
				System.out.println("IN signup");
				signup(request, response);
				break;
			case "/signinPage":
				goToSigninForm(request, response);
				break;
			case "/signin":
				signin(request, response);
				break;
			case "/success":
				System.out.println("IN SUCCESS");
				successPage(request, response);
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
		session = request.getSession();
		System.out.println("goToSignupForm - AccessServlet");
		
		String forward = "/signup.jsp";
		String redirect = "/LibraryCrudProject";
		session.setAttribute("url", request.getRequestURI());
		
		Helper.checkIfLoggedIn(forward, redirect, request, response, session);
	}
	
	private void goToSigninForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();
		System.out.println("goToSigninForm - AccessServlet");
		
		String forward = "/login.jsp";
		String redirect = "/LibraryCrudProject";
		System.out.println(request.getRequestURI());
		session.setAttribute("url", request.getRequestURI());
		
		Helper.checkIfLoggedIn(forward, redirect, request, response, session);
	}
	
	private void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Trying to sign up");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String passConf = request.getParameter("passwordConfirmation");
		
		session = request.getSession(); 
		
		System.out.println(firstName);
		System.out.println(lastName);
		System.out.println(username);
		System.out.println(password);
		System.out.println(passConf);
		
		if(firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty()) {
			request.setAttribute("error", "All fields need to be filled out.");
			goToSignupForm(request, response);
			return;
		}
		
		Patron patron = null;
		
		if(password.equals(passConf)) {
			if((patron = patronDao.getPatronLogin(username)) == null) {
				patron = new Patron(
						-1,
						firstName,
						lastName,
						username,
						password, 
						false
				);
				patronDao.addPatron(patron);
				session.setAttribute("user", patron);
				response.sendRedirect("/LibraryCrudProject/AccessServlet/success");
				return;
			}
			System.out.println("This user already exists.");
			request.setAttribute("error", "This user already exists.");
			goToSignupForm(request, response);
			return;
		} else {
			System.out.println("Passwords are not equal.");
			request.setAttribute("error", "The Passwords are not the same.");
			goToSignupForm(request, response);
		}
		return;
	}
	
	private void signin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username").trim().toLowerCase();
		String password = request.getParameter("password").trim();
		response.setContentType("text/html");
		
		session = request.getSession();  
		
		if(username.isEmpty() && password.isEmpty()) {
			request.setAttribute("error", "The username or password is incorrect.");
			System.out.println("Incorrect username or password!");
			goToSigninForm(request, response);
			return;
		}

		Patron pat = null;
		Librarian lib = null;
		
		if((pat = patronDao.getPatronLogin(username)) != null) {
			if(!pat.isAccount_frozen()) {
				session.setAttribute("user", pat);
				System.out.println("User found in the database!");
				response.sendRedirect("/LibraryCrudProject/PatronServlet");
				return;
			}
			request.setAttribute("error", "This account needs to be unfrozen by a librarian.");
			System.out.println("Frozen Account!");
			goToSigninForm(request, response);
			return;
		} else if((lib = librarianDao.getLibrarianLogin(username, password)) != null) {
			session.setAttribute("user", lib);
			
			System.out.println("Librarian found in the database!");
			response.sendRedirect("/LibraryCrudProject/LibrarianServlet");
			return;
		} else {
			request.setAttribute("error", "The username or password is incorrect.");
			System.out.println("Incorrect username or password!");
			goToSigninForm(request, response);
		}
		return;
	}
	
	private void goToLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession(); 
		if(session != null) {
			session.invalidate();
		}
		response.sendRedirect("/LibraryCrudProject");
	}
	
	private void successPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session = request.getSession();

		String forward = "/success.jsp";
		
		if(session.getAttribute("user") != null) {
			session.setAttribute("newUser", session.getAttribute("user"));
			session.removeAttribute("user");
			RequestDispatcher dispatch = request.getRequestDispatcher(forward);
			dispatch.forward(request, response);
			return;
		}
		response.sendRedirect("/LibraryCrudProject");
		return;
	}
}