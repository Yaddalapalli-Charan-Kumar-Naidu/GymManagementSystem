<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Slot Price</title>
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
        margin: 100px auto 50px;
        padding: 20px;
        background-color: #fff;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        border-radius: 10px;
    }
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
        margin-bottom: 20px;
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
    .button-update {
        width: 100px;
    }
    .button-return {
        width: 140px;
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
    <h2>Update Slot Price</h2>
    
    <h3>Available Slots</h3>
    <table>
        <thead>
            <tr>
                <th>Slot ID</th>
                <th>Slot Time</th>
                <th>Current Price</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="slot" items="${slots}">
                <tr>
                    <td>${slot.slotId}</td>
                    <td>${slot.slotTime}</td>
                    <td>${slot.pricing}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h3>Update Price</h3>
    <form action="/update-price" method="post">
        <label for="slotId">Slot ID:</label>
        <input type="text" id="slotId" name="slotId" required><br>
        
        <label for="newPrice">New Price:</label>
        <input type="text" id="newPrice" name="newPrice" required><br>
        
        <button type="submit" class="button-update">Update Price</button>
    </form>

    <p>${message}</p>

    <div class="button-container">
        <a class="return-link button-return" href="<c:url value='/index'/>">Return to Dashboard</a>
    </div>
</div>

</body>
</html>
