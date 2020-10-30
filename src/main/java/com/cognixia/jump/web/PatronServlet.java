package com.cognixia.jump.web;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
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
import com.cognixia.jump.model.Book;
import com.cognixia.jump.model.BookCheckout;
import com.cognixia.jump.model.Librarian;
import com.cognixia.jump.model.Patron;

@WebServlet("/Patron/*")
public class PatronServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private BookDao bookDao;
	private BookCheckoutDao checkoutDao;
	private HttpSession session;

	@Override
    public void init() {
		bookDao = new BookDaoImp();
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
				System.out.println("checkout");
				checkoutBook(request, response);
				break;
			case "/return":
				returnBook(request, response);
				break;
			case "/history":
				goToMyBookHistory(request, response);
				break;
			case "/settings":
				goToPatronSettings(request, response);
				break;
			case "/edit":
				edit(request, response);
				break;
			default:
				goToPatronDashboard(request, response);
				break;
		}
	}
	
	private void goToPatronDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("goToPatronDashboard - Patron");
		session = request.getSession();
		
		if(Helper.userCheck(request, session)) {
			Patron pat = (Patron) session.getAttribute("user");
			List<BookCheckout> checkoutBooks = checkoutDao.getAllCurrentCheckoutsByPatronId(pat.getPatron_id());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			
			for(BookCheckout bc: checkoutBooks) {
				
				String startDate = df.format(bc.getDue_date());
				String endDate = df.format(new Date());

				if(startDate.compareTo(endDate) > 0) {
			         bc.setOverdue(false);
			      } else if(startDate.compareTo(endDate) < 0 || startDate.compareTo(endDate) == 0) {
			         bc.setOverdue(true);
			      }
				
				bc.setBook(bookDao.getBookByISBN(bc.getIsbn()));
			}
			request.setAttribute("checkoutBooks", checkoutBooks);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/patronDashboard.jsp");
			dispatch.forward(request, response);	
		}
		response.sendRedirect("/LibraryCrudProject/Access/signinPage");
		return;
	}
	
	private void checkoutBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("checkoutBook - Patron");
		session = request.getSession();
		
		if(Helper.userCheck(request, session)) {
			Patron pat = (Patron) session.getAttribute("user");
			String isbn = request.getParameter("isbn");
			Book book = bookDao.getBookByISBN(isbn);
			book.setRented(true);
			bookDao.checkoutBook(book);

			LocalDate startDate = LocalDate.now();
			LocalDate endDate = LocalDate.now();
			BookCheckout bookCheckout = new BookCheckout(0, pat.getPatron_id(), isbn, java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate.plusWeeks(1)), null);
			checkoutDao.addBookCheckout(bookCheckout);
			response.sendRedirect("/LibraryCrudProject/Patron");
			return;
		}
		response.sendRedirect("/LibraryCrudProject/Access/signinPage");
		return;
	}
	
	private void returnBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("returnBook - Patron");
		session = request.getSession();
		
		if(Helper.userCheck(request, session)) {
			String isbn = request.getParameter("isbn");
			bookDao.returnBook(bookDao.getBookByISBN(isbn));
			checkoutDao.returnBook(new java.sql.Date(System.currentTimeMillis()), Integer.parseInt(request.getParameter("checkout_id")));
			response.sendRedirect("/LibraryCrudProject/Patron");
			return;
		}
		response.sendRedirect("/LibraryCrudProject/Access/signinPage");
		return;
	}
	
	private void goToMyBookHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("goToMyBookHistory - Patron");
		session = request.getSession();
		
		if(Helper.userCheck(request, session)) {
			Patron pat = (Patron) session.getAttribute("user");
			List<BookCheckout> bookHistory = checkoutDao.getAllPastCheckoutsByPatronId(pat.getPatron_id());
			
			for(BookCheckout bc: bookHistory) {
				bc.setBook(bookDao.getBookByISBN(bc.getIsbn()));
			}
			
			request.setAttribute("bookHistory", bookHistory);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("/patronHistory.jsp");
			dispatch.forward(request, response);	
			return;
		}
		response.sendRedirect("/LibraryCrudProject/Access/signinPage");
		return;
		
	}
	
	private void goToPatronSettings(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("goToPatronSettings - Patron");
		session = request.getSession();
		
		if(Helper.userCheck(request, session)) {
			RequestDispatcher dispatch = request.getRequestDispatcher("/patronEdit.jsp");
			dispatch.forward(request, response);	
			return;
		}
		response.sendRedirect("/LibraryCrudProject/Access/signinPage");
		return;
	}
	
	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("edit - Patron");
		session = request.getSession();
		
		if(Helper.userCheck(request, session)) {
			Patron pat = (Patron) session.getAttribute("user");
			
			// TODO: Load in all information for the current page.
			
			response.sendRedirect("/LibraryCrudProject/Patron");
			return;
		}
		response.sendRedirect("/LibraryCrudProject/Access/signinPage");
		return;
	}
}