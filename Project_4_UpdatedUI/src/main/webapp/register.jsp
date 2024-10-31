<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body>

	<div class="custom-container">
	
    <h1>Sign Up</h1>
    
    <form action="registerServlet" method="post" class="custom-form">
        <label>First Name:</label> <input type="text" name="firstName" required><br>
        <label>Last Name:</label> <input type="text" name="lastName" required><br>
        <label>Username:</label> <input type="text" name="username" required><br>
        <label>Password:</label> <input type="password" name="password" required><br>
        <button type="submit" class="form-button">Register</button>
    </form>

    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>

    <c:if test="${not empty successMessage}">
        <p style="color:green;">${successMessage}</p>
    </c:if>
    
    </div>
    
</body>
</html>
