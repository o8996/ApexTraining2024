<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<title>Log In</title>
 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body>

<div class="custom-container">

<h1>Log In</h1>

<form method="post" action="loginServlet" class="custom-form">
<label>Username:</label> <input type="text" name="username"> <br>
<label>Password:</label> <input type="password" name="password"><br>
<button type="submit" class="form-button">Submit</button>
</form>

<h3>
<% String error = (String) session.getAttribute("error");
if(error != null) {
	out.print(error);
	session.setAttribute("error",null);
}
%>
</h3>

</div>

</body>
</html>