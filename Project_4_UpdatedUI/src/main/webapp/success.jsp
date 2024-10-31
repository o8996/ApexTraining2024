<%@page import="jakarta.servlet.RequestDispatcher"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Success</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body>
<%
if(session.getAttribute("firstName") == null){
	RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
	requestDispatcher.forward(request, response);
}
%>

<div class="container:">
	
	<h1>Welcome <%= session.getAttribute("firstName") %>! </h1>
    
	</div>

</body>
</html>