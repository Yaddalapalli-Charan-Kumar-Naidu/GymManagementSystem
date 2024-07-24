<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Feedback Submitted</title>
<style>
   body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
}
header {
    background: #333;
    color: #fff;
    padding: 20px 0;
    width: 100%;
    text-align: center;
    position: fixed;
    top: 0;
    left: 0;
}
header h1 {
    margin: 0;
}
.container {
    width: 60%;
    margin: 100px auto 50px; /* Adjusted margin to accommodate fixed header */
    padding: 20px;
    background-color: #fff;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
}
h2 {
    color: #333;
}
.message {
    font-size: 18px;
    color: #333;
    margin-bottom: 20px;
}
.return-link {
    display: inline-block;
    padding: 10px 20px;
    background-color: #333;
    color: white;
    text-decoration: none;
    border-radius: 4px;
    font-size: 14px;
    text-align: center;
}
.return-link:hover {
    background-color: #555;
}
</style>
</head>
<body>

<header>
    <h1>GYM Management System</h1>
</header>

<div class="container">
    <h2>Feedback Submitted</h2>
    
    <p class="message">${message}</p>
    
    <a class="return-link" href="<c:url value='/index'/>">Return to Dashboard</a>
</div>

</body>
</html>
