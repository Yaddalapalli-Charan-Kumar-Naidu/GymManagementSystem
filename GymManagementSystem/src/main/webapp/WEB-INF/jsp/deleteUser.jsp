<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete User</title>
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
table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
    margin-bottom: 20px; /* Added margin-bottom for additional space */
}
table, th, td {
    border: 2px solid #ddd;
}
th {
    padding: 12px;
    text-align: left;
    background-color: #333;
    color: white;
}
td {
    padding: 12px;
    text-align: left;
}
tr:nth-child(even) {
    background-color: #f2f2f2;
}
tr:hover {
    background-color: #ddd;
}
.return-link, button {
    display: inline-block;
    margin-top: 20px;
    padding: 10px 20px;
    background-color: #333;
    color: white;
    text-decoration: none;
    border-radius: 4px;
    border: none;
    cursor: pointer;
    font-size: 14px;
    text-align: center;
}
.return-link:hover, button:hover {
    background-color: #555;
}
.button-delete {
    width: 100px; /* Width for the Delete button */
}
.button-return {
    width: 140px; /* Width for the Return button */
}
.button-container {
    text-align: center;
    margin-top: 40px;
}
</style>
</head>
<body>

<header>
    <h1>GYM Management System</h1>
</header>

<div class="container">
    <h2>Delete User</h2>
    
    <form action="/delete-customer" method="post">
        <label for="username">Select User to Delete:</label>
        <select id="username" name="username" required>
            <c:forEach items="${userList}" var="user">
                <option value="${user.username}">${user.username} (${user.email})</option>
            </c:forEach>
        </select>
        <button type="submit" class="button-delete">Delete</button>
    </form>

    <div class="button-container">
        <a class="return-link" href="<c:url value='/index'/>">Return to Dashboard</a>
    </div>
</div>

</body>
</html>
