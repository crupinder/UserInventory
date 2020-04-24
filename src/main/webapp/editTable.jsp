<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Edit</title>
	</head>
	<body>
					<form action="EditUpdate" method="post">
				<input type="hidden" name="pNo" value="${pNo}"/>
				<table>
				
				<tr>
					<td>Product Name : </td><td><input type="text" name="pn" value="${pName}"/><br></td>
				</tr>
				<tr>
					<td>Product Price : </td><td><input type="text" name="pp" value="${pPrice}"/><br></td>
				</tr>
				<tr>
					<td>Quantity : </td><td><input type="text" name="pq" value="${pQty}"/><br></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" name="update" value="Update"/></td>
				</tr></table>
			</form>
		
	</body>
</html>