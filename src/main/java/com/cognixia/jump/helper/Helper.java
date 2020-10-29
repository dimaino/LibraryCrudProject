package com.cognixia.jump.helper;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Helper {

	public static void checkIfLoggedIn(String forward, String redirect, HttpServletRequest request, HttpServletResponse response, HttpSession session) throws ServletException, IOException {
//		session = request.getSession();
		System.out.println(session);
		
		if(session != null) {
			if(session.getAttribute("user") == null) {
				RequestDispatcher dispatch = request.getRequestDispatcher(forward);
				dispatch.forward(request, response);
				return;
			} else {
				response.sendRedirect(redirect);
				return;
			}
		} else {
			response.sendRedirect(redirect);
			return;
		}
	}
	
}
