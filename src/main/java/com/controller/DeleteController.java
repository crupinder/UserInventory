package com.controller;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteServlet")
public class DeleteController extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws
	ServletException, IOException{
		Integer i = 1;
		String s;
		for(; ; i++) {
			s = i.toString() + "a";
			if(req.getParameter(s)!=null) {
				System.out.println(s);
				break;
			}
		}
		/*if ((s != null) && (s.length() > 0)) 
		      i = Integer.parseInt(s.substring(0, s.length() - 1));
		else {
			RequestDispatcher rd = req.getRequestDispatcher("user.jsp");
			rd.forward(req,res);
		}*/
		//try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##rupinder","root");
		try(Connection con = DBConnection.getConnection();
				Statement st = con.createStatement()){
			st.execute("delete from user1 where pno = '" + i + "'" );
			RequestDispatcher rd = req.getRequestDispatcher("user.jsp");
			rd.forward(req,res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws
	ServletException, IOException{
		doPost(req, res);
	}
}
