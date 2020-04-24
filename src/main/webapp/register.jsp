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
			<form action="RegisterServlet" method="post">
				<tr>
					<td>First Name : </td><td><input type="text" name="rfn"><br></td>
				</tr>
				<tr>
					<td>Last Name : </td><td><input type="text" name="rln"><br></td>
				</tr>
				<tr>
					<td>User Name : </td><td><input type="text" name="run"><br></td>
				</tr>
				<tr>
					<td>Password : </td><td><input type="password" name="rpwd"><br></td>
				</tr>
				<tr>
					<td>Confirm Password : </td><td><input type="password" name="rcpwd"><br></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Register"></td>
				</tr>
			</form>
		</table>
	</body>
</html>