<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ page import="java.util.*,com.test.Employee" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
<tr>
    <th>ID</th><th>Name</th><th>Salary</th><th>Department</th>
</tr>

<%
List<Employee> list = (List<Employee>) request.getAttribute("employees");
for(Employee e : list){
%>
<tr>
    <td><%= e.getId() %></td>
    <td><%= e.getName() %></td>
    <td><%= e.getSalary() %></td>
    <td><%= e.getDepartment() %></td>
</tr>
<% } %>
</table>
</body>
</html>