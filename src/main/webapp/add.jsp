<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Register</title>
	</head>
	<body>
		<table>
			<form action="AddServlet" method="post">
				<tr>
					<td>Product Name : </td><td><input type="text" name="addPName"><br></td>
				</tr>
				<tr>	
					<td>Product Price : </td><td><input type="text" name="addPPrice"><br></td>
				</tr>
				<tr>
					<td>Quantity : </td><td><input type="text" name="addPQty"><br></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" name="update" value="Add"></td>
				</tr>
			</form>
		</table>
	</body>
</html>