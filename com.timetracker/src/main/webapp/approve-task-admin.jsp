<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <!-- LOCALIZATION START-->
    <c:if test="${sessionScope.locale == null}">
        <fmt:setLocale value="ru"/>
    </c:if>
    <c:if test="${sessionScope.locale != null}">
        <fmt:setLocale value="${sessionScope.locale}"/>
    </c:if>
    <fmt:setBundle basename="locale.global" var="bundle"/>
    <!-- LOCALIZATION END-->

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <title><fmt:message key="global.approves" bundle="${bundle}"/></title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <nav class="navbar navbar-light bg-light">
                <div class="container-fluid">
                    <a class="navbar-brand" href="/">
                        <img src="resources/img/clock.jpg" alt="" width="30" height="24"
                             class="d-inline-block align-top">
                        <fmt:message key="global.timetracker" bundle="${bundle}"/>
                    </a>
                </div>
            </nav>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <nav class="nav nav-pills flex-column flex-sm-row">
                <a class="flex-sm-fill text-sm-center nav-link " aria-current="page" href="/tasks"><fmt:message key="global.tasks" bundle="${bundle}"/></a>
                <a class="flex-sm-fill text-sm-center nav-link " href="/users"><fmt:message key="global.users" bundle="${bundle}"/></a>
                <a class="flex-sm-fill text-sm-center nav-link active" href="/approve-tasks"><fmt:message key="global.approves" bundle="${bundle}"/></a>
            </nav>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="global.id" bundle="${bundle}"/></th>
            <th scope="col"><fmt:message key="global.task" bundle="${bundle}"/></th>
            <th scope="col"><fmt:message key="global.category" bundle="${bundle}"/></th>
            <th scope="col"><fmt:message key="global.user" bundle="${bundle}"/></th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${data}" var="task">
            <c:if test="${task.status == 'NEW'}">
                <tr class="table-success">
                    <td>${task.id}</td>
                    <td>${task.name}</td>
                    <td>${task.categoryName}</td>
                    <td>${task.userName}</td>
                    <td>
                        <form class="row gy-2 gx-3 align-items-center" action="/approve-tasks" method="post">
                            <div class="input-group">
                                <input type="hidden" name="task-id" value="${task.id}">
                                <button class="btn btn-outline-secondary" type="submit"><fmt:message key="global.approve" bundle="${bundle}"/></button>
                            </div>
                        </form>
                    </td>

                    <td>
                        <form class="row gy-2 gx-3 align-items-center" action="/approve-delete-task" method="post">
                            <div class="input-group">
                                <input type="hidden" name="task-id" value="${task.id}">
                                <button class="btn btn-outline-secondary" type="submit"><fmt:message key="global.reject" bundle="${bundle}"/></button>
                            </div>
                        </form>
                    </td>
                </tr>
            </c:if>
            <c:if test="${task.status == 'DELETED'}">
                <tr class="table-danger">
                    <td>${task.id}</td>
                    <td>${task.name}</td>
                    <td>${task.categoryName}</td>
                    <td>${task.userName}</td>
                    <td>
                        <form class="row gy-2 gx-3 align-items-center" action="/approve-delete-task" method="post">
                            <div class="input-group">
                                <input type="hidden" name="task-id" value="${task.id}">
                                <button class="btn btn-outline-secondary" type="submit"><fmt:message key="global.delete" bundle="${bundle}"/></button>
                            </div>
                        </form>
                    </td>
                    <td>
                        <form class="row gy-2 gx-3 align-items-center" action="/approve-tasks" method="post">
                            <div class="input-group">
                                <input type="hidden" name="task-id" value="${task.id}">
                                <button class="btn btn-outline-secondary" type="submit"><fmt:message key="global.reject" bundle="${bundle}"/></button>
                            </div>
                        </form>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
