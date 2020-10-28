package com.cognixia.jump.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.dao.LibrarianDao;
import com.cognixia.jump.dao.LibrarianDaoImp;
import com.cognixia.jump.dao.PatronDao;
import com.cognixia.jump.dao.PatronDaoImp;

@WebServlet("/")
public class AccessServlet extends HttpServlet {
	
private static final long serialVersionUID = 1L;
	
	private PatronDao patronDao;
	private LibrarianDao librarianDao;

	@Override
    public void init() {
		patronDao = new PatronDaoImp();
		librarianDao = new LibrarianDaoImp();
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
		String action = request.getServletPath();
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
			default:
				response.sendRedirect("/");
				break;
		}
	}
	
	private void goToSignupForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher("signup.jsp");
		dispatch.forward(request, response);
	}
	
	private void goToSigninForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher("login.jsp");
		dispatch.forward(request, response);
	}
	
	private void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	private void signin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}