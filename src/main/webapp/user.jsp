<%@page import="javax.swing.text.Document"%>
<%@page import="com.controller.DBConnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>User</title>
		<style>
		table, th, td {
		  border: 1px solid black;
		  border-collapse: collapse;
		}
		th, td {
		  padding: 5px;
		  text-align: center;    
		}
		</style>
	</head>
	
	<body>
		<table style="width:100%">
		  <tr>
		    <th colspan="5">Welcome</th>
		  </tr>
		  <tr>
		    <th>pno</th>
		    <th>pname</th>
		    <th>price</th>
		    <th>Qty</th>
		    <th>Total</th>
		  </tr>
		  	<%
			  	//try(Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","c##rupinder","root");
					try(Connection con = DBConnection.getConnection();
			  			Statement st = con.createStatement()){
						String tableName = "user1";//(String)request.getAttribute("tablename");
			  		if(!con.getMetaData().getTables(null, null, tableName, null).next()) { //check if the table is already present. Note that the table name is all caps
			  			PreparedStatement ps = con.prepareStatement("create table " + tableName + "(pno int NOT NULL AUTO_INCREMENT, pname varchar(50), pprice real, pqty int, ptotal real, primary key(pno))");
			  			ps.execute();
						st.execute("insert into user1 (pname,pprice,pqty,ptotal) values('Laptop', 500, 0, 0)");
						st.execute("insert into user1 (pname,pprice,pqty,ptotal) values('TV', 1000, 0, 0)");
						st.execute("insert into user1 (pname,pprice,pqty,ptotal) values('Dish Washer', 800, 0, 0)");
						st.execute("insert into user1 (pname,pprice,pqty,ptotal) values('Table', 100, 0, 0)");
					}
			  		ResultSet rs = st.executeQuery("select * from user1");
			  		for(int i=1; rs.next(); i++){
			  			double pPrice = rs.getDouble(3);
			  			int pQty = rs.getInt(4);
			  			double pTotal = rs.getDouble(5);
			  	%>
		  		<tr>
			  		<td><%=i%></td>
			  		<td><%=rs.getString(2) %></td>
			  		<td><%=pPrice %></td>
			  		<td><%= pQty%></td>
			  		<td><%=pTotal %></td>
			  		<td><form action="EditServlet" method="post">
						<input type="submit" name="edit" value="Edit"></form></td>
			  		<td><form action="DeleteServlet" method="post">
			  			<input type="submit" name="delete" value="Delete"></form></td>
			  		<script>
			  			var v = "<%=rs.getInt(1)%>";
			  			var allButtons = document.getElementsByTagName("input");
		  			    for(var x = 0; x < allButtons.length; x++)
		  		    	    if(!allButtons[x].name.localeCompare("edit"))
		  		        		allButtons[x].name = v;
		  		    	    else if(!allButtons[x].name.localeCompare("delete"))
		  		    	    	allButtons[x].name = v.concat("a");
			  		</script>
		  		</tr>
			  	<%	} 
			  		rs = st.executeQuery("select sum(ptotal) from user1"); 
			  		if(rs.next())%>
			  	
		  		<tr>
			  		<td></td>
			  		<td></td>
			  		<td></td>
			  		<td>Total</td>
			  	 	<td><%= rs.getDouble(1) %></td>
			  		<td><form action="LogoutServlet" method="post">
						<input type="submit" name="edit" value="Logout"></form></td>
			  		<td><form action="AddRedirect" method="post">
			  			<input type="submit" name="delete" value="Add"></form></td>
		  		</tr>
		  	<%	}
		  	%>
		</table>
	</body>
</html>