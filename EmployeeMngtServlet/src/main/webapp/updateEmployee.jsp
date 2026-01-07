<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Update Employee Page</h2><br>
	
	<form action="updateEmployee" method="post">
	Enter Updated Employee ID : <input type="number" name="eId"><br><br>
	Enter Updated Employee Name : <input type="text" name="eName"><br><br>
	Enter Updated Employee Salary : <input type="number" name="eSalary"><br><br>
	Enter Updated Employee Department : <input type="text" name="eDept"><br><br>
	
	<input type="submit" value="Update">
	
	</form>
	
	<a href="home.jsp">Back to Home</a>
</body>
</html>