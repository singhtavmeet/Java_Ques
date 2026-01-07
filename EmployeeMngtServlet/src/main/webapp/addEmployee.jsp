<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Add Employee Page</h2><br>
	
	<form action="addEmployee" method="post">
	Enter Employee ID : <input type="number" name="eId"><br><br>
	Enter Employee Name : <input type="text" name="eName"><br><br>
	Enter Employee Salary : <input type="number" name="eSalary"><br><br>
	Enter Employee Department : <input type="text" name="eDept"><br><br>
	
	<input type="submit" value="Add">
	
	</form>
	
	<a href="home.jsp">Back to Home</a>
</body>
</html>