<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Feedback</title>
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
        width: 80%;
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
    .return-link {
        display: inline-block;
        margin-top: 20px;
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
    <h2>View Feedback</h2>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Feedback</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="feedback" items="${feedbackList}">
                <tr>
                    <td>${feedback.id}</td>
                    <td>${feedback.name}</td>
                    <td>${feedback.email}</td>
                    <td>${feedback.feedback}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <a class="return-link" href="<c:url value='/index'/>">Return to Dashboard</a>
</div>

</body>
</html>
