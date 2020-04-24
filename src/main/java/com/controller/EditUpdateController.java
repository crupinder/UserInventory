package com.controller;

import java.io.IOException;
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

@WebServlet("/EditUpdate")
public class EditUpdateController extends HttpServlet {
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws
	ServletException, IOException{
		Integer pNo = Integer.parseInt(req.getParameter("pNo"));
		String pName = req.getParameter("pn");
		Double pPrice = Double.parseDouble(req.getParameter("pp"));
		Integer pQty = Integer.parseInt(req.getParameter("pq"));
		Double pTotal = pPrice * pQty;
		//ResultSet rs = null;
		
		//try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##rupinder","root");
		try(Connection con = DBConnection.getConnection();
				Statement st = con.createStatement()){
			PreparedStatement stmt = con.prepareStatement("update user1 set pname=?, pprice=?, pqty=?, ptotal = ? where pno = ?");
			stmt.setString(1, pName);
			stmt.setDouble(2, pPrice);
			stmt.setInt(3, pQty);
			stmt.setDouble(4, pTotal);
			stmt.setInt(5, pNo);
			stmt.executeUpdate();
			RequestDispatcher rd = req.getRequestDispatcher("user.jsp");
			rd.forward(req,res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
