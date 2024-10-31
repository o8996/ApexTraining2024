<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body>

	<div class="container:">
	
	<h1>Welcome</h1>
	<h2>Sign up or Log in to get started</h2>
	
    <!-- Sign Up Button -->
    <form action="register.jsp" method="get">
        <button type="submit">Sign Up</button>
    </form>
    
    <!-- Log In Button -->
    <form action="login.jsp" method="get">
        <button type="submit">Log In</button>
    </form>
    
	</div>
	
</body>
</html>
