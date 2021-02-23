<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "my" uri = "/WEB-INF/tag/taglib.tld"%>
<html>
<head>
    <c:set var="currentPageName" value="approve-tasks" scope="session"/>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <%@ include file="/WEB-INF/jspf/header.jspf" %>
        </div>
    </div>
    <div class="row">
        <div class="col">
            <%@ include file="/WEB-INF/jspf/admin-navigation.jspf" %>
        </div>
    </div>
    <!-- TABLE START -->
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
    <!-- TABLE END -->
</div>
<%@ include file="/WEB-INF/jspf/foother.jspf" %>
</body>
</html>
