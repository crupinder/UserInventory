package com.controller;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/RegisterServlet")
public class RegisterController extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws
	ServletException, IOException{
		PrintWriter pw = res.getWriter();
		String firstName = req.getParameter("rfn");
		String lastName = req.getParameter("rln");
		String username = req.getParameter("run");
		String password = req.getParameter("rpwd");
		String confPassword = req.getParameter("rcpwd");
		boolean redirect = false;
		
		if(firstName.equals("")) {
			pw.println("<font color = 'red'>First Name cannot be empty<font><br>");
			redirect = true;
		}
			
		if(lastName.equals("")) {
			redirect = true;
			pw.println("<font color = 'red'>Last Name cannot be empty<font><br>");
		}
		
		if(username.equals("")) {
			redirect = true;
			pw.println("<font color = 'red'>Username cannot be empty<font><br>");
		}
		
		if(password.equals("")) {
			redirect = true;
			pw.println("<font color = 'red'>Password cannot be empty<font><br>");
		}
		
		if(confPassword.equals("")) {
			redirect = true;
			pw.println("<font color = 'red'>Confirm password cannot be empty<font><br>");
		}
		
		if(redirect) {
			RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
			rd.include(req, res);
		}
		else if(!password.equals(confPassword)) {
			pw.println("<font color = 'red'>Password does not match with Confirm password field<font><br>");
			RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
			rd.include(req, res);
		}
		else {
			//try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##rupinder","root");
			try(Connection con = DBConnection.getConnection();
					Statement st = con.createStatement()){
				if(!con.getMetaData().getTables(null, null, "LOGININFO", null).next()) 
					st.execute("create table logininfo(firstname varchar(20), lastname varchar(20), username varchar(20), password varchar(20))");
				ResultSet rs = st.executeQuery("select password from logininfo where username = '" + username + "'");
				if(!rs.next()) {
					st.execute("insert into logininfo values('" + firstName + "', '" + lastName + "','" + username + "','" + password + "')");
					pw.println("<font color = 'blue'>Registration successful<font><br>");
					RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
					rd.forward(req, res);
				}
				else {
					pw.println("<font color = 'red'>Username is already taken<font><br>");
					RequestDispatcher rd = req.getRequestDispatcher("register.jsp");
					rd.include(req, res);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
}