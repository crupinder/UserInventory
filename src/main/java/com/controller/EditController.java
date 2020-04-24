package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditServlet")
public class EditController extends HttpServlet{
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws
	ServletException, IOException{
		Integer i = 1;
		ResultSet rs = null;
		int pNo;
		String pName;
		Double pPrice;
		int pQty; 
		
		for( ; ;i++) {
			if(req.getParameter(i.toString())!=null)
				break;
		}

		//try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##rupinder","root");
		try(Connection con = DBConnection.getConnection();
				Statement st = con.createStatement()){
			rs = st.executeQuery("select pno, pname, pprice, pqty from user1 where pno = '" + i + "'" );
			if(rs.next()) {
				pNo = rs.getInt(1);
				pName = rs.getString(2);
				pPrice = rs.getDouble(3);
				pQty = rs.getInt(4);
				req.setAttribute("pName", pName);
				req.setAttribute("pPrice", pPrice);
				req.setAttribute("pQty", pQty);
				req.setAttribute("pNo", pNo);
			}
			RequestDispatcher rd = req.getRequestDispatcher("editTable.jsp");
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
