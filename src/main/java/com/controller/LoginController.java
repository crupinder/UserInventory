package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginController extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws
		ServletException, IOException{
		HttpSession session = req.getSession();
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		String uname = req.getParameter("un");
		String pwd = req.getParameter("pwd");
		ResultSet rs = null;
		
		if(session.getAttribute("username")==null) {
			try (Connection con = DBConnection.getConnection();
					Statement st = con.createStatement()){
				if(!con.getMetaData().getTables(null, null, "LOGININFO", null).next()) //check if the table logininfo exists (note that the table name is all caps
					st.execute("create table logininfo(firstname varchar(20), lastname varchar(20), username varchar(20), password varchar(20))");
				
				rs = st.executeQuery("select password from logininfo where username = '" + uname + "'");
				if(!rs.next()) {
					pw.println("<font color = 'red'>Invalid Username<font>");
					RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
					rd.include(req, res);
				}
				else if(!rs.getString("password").equals(pwd)) {
					pw.println("<font color = 'red'>Invalid Password<font>");
					RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
					rd.include(req, res);
				}
				else {
					session.setAttribute("username", uname);
					//PreparedStatement ps = con.prepareStatement("select tablename from logininfo where username = ?");
					//ps.setString(1, uname);
					//rs = ps.executeQuery();
					//if(rs.next()) {
						//req.setAttribute("tablename", rs.getString(1));
						//RequestDispatcher rd = req.getRequestDispatcher("user.jsp");
						res.sendRedirect("user.jsp");
						//rd.forward(req,res);
					//}
				}
			}catch(Exception e) { e.printStackTrace();}	
		}
		else {
			//session.removeAttribute("username");
			System.out.println("Not null");
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req,res);
		}
	}
}
