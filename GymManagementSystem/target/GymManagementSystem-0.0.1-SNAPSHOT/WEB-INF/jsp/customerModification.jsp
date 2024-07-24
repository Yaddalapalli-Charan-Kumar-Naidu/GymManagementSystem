<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Modification</title>
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
    .message {
        padding: 15px;
        margin-bottom: 20px;
        border: 1px solid #ddd;
        border-radius: 5px;
    }
    .success {
        color: green;
        border-color: green;
    }
    .error {
        color: red;
        border-color: red;
    }
    .button-container {
        text-align: center;
        margin-top: 40px;
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
        width: 100px;
    }
    .button-return {
        width: 140px;
    }
</style>
</head>
<body>

<header>
    <h1>GYM Management System</h1>
</header>

<div class="container">
    <h2>Customer Modification</h2>
    
    <c:if test="${not empty successMessage}">
        <div class="message success">
            <c:out value="${successMessage}"/>
        </div>
    </c:if>

    <c:if test="${not empty errorMessage}">
        <div class="message error">
            <c:out value="${errorMessage}"/>
        </div>
    </c:if>

    <h3>Delete User:<span style="color:green">Successful</span></h3>
    <form action="<c:url value='/delete-user'/>" method="get">
        <button type="submit" class="button-delete">Show Users</button>
    </form>

    <!-- Assuming userList is provided in the model -->
    <c:if test="${not empty userList}">
        <h3>Available Users</h3>
        <table>
            <tr>
                <th>User ID</th>
                <th>Username</th>
                <th>Email</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${userList}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.email}</td>
                    <td>
                        <a href="<c:url value='/delete-customer/${user.username}'/>" class="button-delete">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:if>

    <div class="button-container">
        <a class="return-link" href="<c:url value='/index'/>">Return to Dashboard</a>
    </div>
</div>

</body>
</html>
