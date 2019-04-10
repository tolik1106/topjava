<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="resources/js/topjava.common.js" defer></script>
<script type="text/javascript" src="resources/js/topjava.meals.js" defer></script>

<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="container mb-3">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <h3 class="text-center pt-2 text-success"><spring:message code="meal.title"/></h3>
            </div>
        </div>

        <form id="filterForm">
            <div class="row text-secondary bg-light">
                <div class="form-group col-sm-3">
                    <label><spring:message code="meal.startDate"/>:</label>
                    <input class="form-control" type="date" name="startDate" value="${param.startDate}">
                </div>
                <div class="form-group col-sm-3">
                    <label><spring:message code="meal.endDate"/>:</label>
                    <input class="form-control" type="date" name="endDate" value="${param.endDate}">
                </div>
                <div class="form-group col-sm-3">
                    <label><spring:message code="meal.startTime"/>:</label>
                    <input class="form-control" type="time" name="startTime" value="${param.startTime}">
                </div>
                <div class="form-group col-sm-3">
                    <label><spring:message code="meal.endTime"/>:</label>
                    <input class="form-control" type="time" name="endTime" value="${param.endTime}">
                </div>
            </div>
        </form>

        <div class="row mt-2">
            <div class="col text-right">
                <button id="drop" class="btn btn-danger btn-sm" type="submit" onclick="dropFilter()"><spring:message
                        code="meal.resetFilter"/></button>
                <button id="filter" class="btn btn-info btn-sm" type="submit" onclick="filter()"><spring:message
                        code="meal.filter"/></button>
            </div>
        </div>
    </div>
    <hr>
    <button class="btn btn-primary btn-sm" onclick="add()">
        <span class="fa fa-plus"></span>
        <spring:message code="meal.add"/></button>
    <table id="datatable" class="table">
        <thead class="text-dark">
        <tr>
            <th><spring:message code="meal.dateTime"/></th>
            <th><spring:message code="meal.description"/></th>
            <th><spring:message code="meal.calories"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody class="bg-light">
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealTo"/>
            <tr data-mealExcess="${meal.excess}" id="${meal.id}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a class="update"><span class="fa fa-pencil-square"></span></a></td>
                <td><a class="delete"><span class="fa fa-trash-o"></span> </a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><spring:message code="meal.add"/></h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="dateTime" class="col-form-label"><spring:message code="meal.dateTime"/></label>
                        <input type="datetime-local" class="form-control" id="dateTime" name="dateTime"
                               placeholder="<spring:message code="meal.dateTime" />" required>
                    </div>

                    <div class="form-group">
                        <label for="description" class="col-form-label"><spring:message
                                code="meal.description"/></label>
                        <input type="text" class="form-control" id="description" name="description"
                               placeholder="<spring:message code="meal.description"/>" required>
                    </div>

                    <div class="form-group">
                        <label for="calories" class="col-form-label"><spring:message code="meal.calories"/></label>
                        <input type="number" class="form-control" id="calories" name="calories"
                               placeholder="<spring:message code="meal.calories"/>">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="fragments/footer.jsp"/>
</body>
</html>