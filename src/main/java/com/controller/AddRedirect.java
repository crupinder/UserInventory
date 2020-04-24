package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddRedirect")
public class AddRedirect extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws
	ServletException, IOException{
		RequestDispatcher rd = req.getRequestDispatcher("add.jsp");
		rd.forward(req,res);
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws
	ServletException, IOException{
		doPost(req, res);
	}
}
