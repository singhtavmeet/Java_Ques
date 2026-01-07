<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Delete Employee Page</h2><br>
	
	<form action="deleteEmployee" method="post">
		Enter Employee Id : <input type="number" name="eId">
		<input type="submit" value="Delete">
	</form>	
	
	<a href="home.jsp">Back to Home</a>
</body>
</html>