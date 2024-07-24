<%@ page contentType="text/html;charset=ISO-8859-1" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Item Count</title>
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
    .button-update {
        width: 100px; /* Width for the Update button */
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
    <h2>Update Gym Item Count</h2>
    <form action="${pageContext.request.contextPath}/update" method="post">
        <label for="itemId">Select Item:</label>
        <select name="itemId" id="itemId">
            <c:forEach var="item" items="${items}">
                <option value="${item.itemId}">${item.itemName} - ${item.totalSeat} Available</option>
            </c:forEach>
        </select>
        <br/>
        <label for="count">New Count:</label>
        <input type="number" id="count" name="count" min="0" required/>
        <br/>
        <button type="submit" class="button-update">Update Count</button>
    </form>
    
    <div class="button-container">
        <a class="return-link" href="<c:url value='/index'/>">Return to Dashboard</a>
    </div>
</div>

</body>
</html>
