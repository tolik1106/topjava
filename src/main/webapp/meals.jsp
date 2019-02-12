<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
</head>
<body>
    <h3><a href="index.html">Home</a></h3>
    <hr>
    <h2>Meals</h2>
    <table>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th colspan="2">Action</th>
        </tr>
        <c:forEach items="${mealToList}" var="mealTo">
            <tr style="color: <c:out value="${mealTo.excess? 'red': 'green'}"/>">
                <td><fmt:parseDate value="${mealTo.dateTime}" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDate" type="date"/>
                    <fmt:formatDate value="${parsedDate}" pattern="yyyy-MM-dd HH:mm" type="date"/></td>
                <td>${mealTo.description}</td>
                <td>${mealTo.calories}</td>
                <td><a href="meals?action=edit&mealToId=<c:out value="${mealTo.id}"/>">Edit</a></td>
                <td><a href="meals?action=delete&mealToId=<c:out value="${mealTo.id}"/>">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <p><a href="meals?action=insert">Add meal</a></p>
</body>
</html>
