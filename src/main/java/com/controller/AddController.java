package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddServlet")
public class AddController extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws
	ServletException, IOException{
		String pName = req.getParameter("addPName");
		Double pPrice = Double.parseDouble(req.getParameter("addPPrice"));
		Integer pQty = Integer.parseInt(req.getParameter("addPQty"));
		Double pTotal = pQty * pPrice;
		//try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##rupinder","root");
		try (Connection con = DBConnection.getConnection();
				Statement st = con.createStatement()){
			PreparedStatement stmt = con.prepareStatement("insert into user1 (pname,pprice,pqty,ptotal) values(?,?,?,?)");
			stmt.setString(1, pName);
			stmt.setDouble(2, pPrice);
			stmt.setInt(3, pQty);
			stmt.setDouble(4, pTotal);
			stmt.executeUpdate();

			RequestDispatcher rd = req.getRequestDispatcher("user.jsp");
			rd.forward(req,res);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
