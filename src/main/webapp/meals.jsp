<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .excess {
            color: red;
        }

        .row {
            display: inline-block;
            width: 90%;
            min-width: 650px;
            max-width: 900px;
            text-align: center;
            margin: 10px 5%;
            height: 100px;
            border-radius: 10px;
            border: 1px solid #b4bbc2;
            color: #7f7f7f;
            padding: 1%;
            font-size: 18px;
            font-family: Arial, sans-serif;
        }

        .date {
            display: inline-block;
            position: relative;
            width: 48%;
            height: 50%;
            margin: 5px 1%;
        }

        .input_date {
            display: inline-block;
            position: relative;
            width: 45%;
            min-width: 146px;
            text-align: center;
            margin-right: 5px;

        }

        .time {
            display: inline-block;
            position: relative;
            width: 47%;
            height: 50%;
            margin: 5px 1%;
        }
        .input_time {
            display: inline-block;
            position: relative;
            width: 45%;
            min-width: 100px;
            text-align: center;
            margin-right: 5px;
        }

        .filter {
            position: relative;
            display: block;
            margin-left: 80%;
            margin-top: 1%;
        }

        label {
            display: block;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Meals</h2>
    <div class="row">
        <form method="post" action="meals">
            <div class="date">
                <div class="input_date">
                    <label for="startDate">From date</label>
                    <input name="startDate" id="startDate" type="date">
                </div>

                <div class="input_date">
                    <label for="endDate">To date</label>
                    <input name="endDate" id="endDate" type="date">
                </div>
            </div>
            <div class="time">
                <div class="input_time">
                    <label for="startTime">From time</label>
                    <input name="startTime" id="startTime" type="time">
                </div>
                <div class="input_time">
                    <label for="endTime">To time</label>
                    <input name="endTime" id="endTime" type="time">
                </div>
            </div>
            <button class="filter" type="submit">Filter</button>
        </form>
    </div>
    <div>
        <a href="meals?action=create">Add Meal</a>
    </div>
    <hr/>
    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealTo"/>
            <tr class="${meal.excess ? 'excess' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>