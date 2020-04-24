package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutServlet")
public class LogoutController extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws
	ServletException, IOException{
		HttpSession session = req.getSession();
		//session.setAttribute("username",null);
		session.removeAttribute("username");
		System.out.println(session.getAttribute("username"));
		RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
		rd.forward(req,res);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws
	ServletException, IOException{
		doPost(req, res);
	}
}
