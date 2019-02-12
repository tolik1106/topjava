<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit meal</title>
</head>
<body>
<h3>Edit meal</h3>
<form action="meals" method="post">
    <input type="hidden" name="mealToId" value="${mealTo.id}">
    <label for="dateTime">Date</label>
    <input id="dateTime" type="datetime-local" name="dateTime">
    <label for="description">Description</label>
    <input id="description" type="text" name="description">
    <label for="calories">Calories</label>
    <input id="calories" type="text" name="calories">
    <input type="submit" value="Add">
</form>

</body>
</html>
