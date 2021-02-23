<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "my" uri = "/WEB-INF/tag/taglib.tld"%>
<html>
<head>
    <c:set var="currentPageName" value="profile" scope="session"/>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <%@ include file="/WEB-INF/jspf/header.jspf" %>
        </div>
    </div>
    <!-- CREATE TASK START-->
    <div class="col-8">
        <form class="row gy-2 gx-3 align-items-center" action="/request-create-task" method="post">
            <div class="col-auto">
                <label class="visually-hidden" for="taskNameInput"><fmt:message key="global.task" bundle="${bundle}"/></label>
                <c:if test="${!sessionScope.ERRORS.containsKey('taskName')}">
                <input type="text" name="taskName" class="form-control" id="taskNameInput"
                       placeholder=<fmt:message key="global.task" bundle="${bundle}"/>>
                </c:if>
                <c:if test="${sessionScope.ERRORS.containsKey('taskName')}">
                    <input type="text" name="taskName" class="form-control is-invalid" id="taskNameInput"
                           placeholder=<fmt:message key="global.task" bundle="${bundle}"/>>
                </c:if>
            </div>
            <div class="col-auto">
                <label class="visually-hidden" for="categorySelect"><fmt:message key="global.category" bundle="${bundle}"/></label>
                <c:if test="${!sessionScope.ERRORS.containsKey('categoryID')}">
                <select class="form-select" id="categorySelect" name="categoryID">
                    <option selected value=""><fmt:message key="global.category" bundle="${bundle}"/>...</option>
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>
                </c:if>
                <c:if test="${sessionScope.ERRORS.containsKey('categoryID')}">
                <select class="form-select is-invalid" id="categorySelect" name="categoryID">
                    <option selected value=""><fmt:message key="global.category" bundle="${bundle}"/>...</option>
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>
                </c:if>
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-primary"><fmt:message key="global.create_task" bundle="${bundle}"/></button>
            </div>
        </form>
    </div>
    <!-- CREATE TASK END-->
    <!-- TABLE START-->
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="global.task" bundle="${bundle}"/></th>
            <th scope="col"><fmt:message key="global.time" bundle="${bundle}"/></th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${data}" var="task">
            <c:if test="${task.status == 'APPROVED'}">
                <tr>
                    <td>${task.name}</td>
                    <td>${task.time}</td>
                    <td>
                        <form class="row gy-2 gx-3 align-items-center" action="/update-time" method="post">
                            <div class="input-group">
                                <input type="hidden" name="task-id" value="${task.id}">
                                <c:if test="${!sessionScope.ERRORS.containsKey('time')}">
                                <input type="text" name="time" class="form-control" aria-describedby="time">
                                </c:if>
                                <c:if test="${sessionScope.ERRORS.containsKey('time')}">
                                <input type="text" name="time" class="form-control is-invalid" aria-describedby="time">
                                </c:if>
                                <button class="btn btn-outline-secondary" type="submit"><fmt:message key="global.update" bundle="${bundle}"/></button>
                            </div>
                        </form>
                    </td>

                    <td>
                        <form class="row gy-2 gx-3 align-items-center" action="/request-delete-task" method="post">
                            <div class="input-group">
                                <input type="hidden" name="task-id" value="${task.id}">
                                <button class="btn btn-outline-secondary" type="submit"><fmt:message key="global.delete" bundle="${bundle}"/></button>
                            </div>
                        </form>
                    </td>
                </tr>
            </c:if>
            <c:if test="${task.status != 'APPROVED'}">
                <tr class="table-secondary">
                    <td>${task.name}</td>
                    <td>${task.time}</td>
                    <td>
                        <form class="row gy-2 gx-3 align-items-center" action="/update-time" method="post">
                            <div class="input-group">
                                <input type="hidden" name="task-id" value="${task.id}">
                                <input type="text" name="time" class="form-control" aria-describedby="time" disabled>
                                <button class="btn btn-outline-secondary disabled" type="submit"><fmt:message key="global.update" bundle="${bundle}"/></button>
                            </div>
                        </form>
                    </td>
                    <td>
                        <form class="row gy-2 gx-3 align-items-center" action="/request-delete-task" method="post">
                            <div class="input-group">
                                <input type="hidden" name="task-id" value="${task.id}">
                                <button class="btn btn-outline-secondary disabled" type="submit"><fmt:message key="global.delete" bundle="${bundle}"/></button>
                            </div>
                        </form>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
    <!-- TABLE FINISH-->
    <!--PAGINATION START-->
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <c:choose>
                <c:when test="${currentPage <= 0}">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1" aria-disabled="true"><fmt:message key="global.previous" bundle="${bundle}"/></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link"
                           href="/profile?currentPage=${currentPage - 1}&search=${search}&orderBy=${orderBy}"><fmt:message key="global.previous" bundle="${bundle}"/></a>
                    </li>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${currentPage >= maxPage -1}">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" aria-disabled="true" tabindex="-1"><fmt:message key="global.next" bundle="${bundle}"/></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link"
                           href="/profile?currentPage=${currentPage + 1}&search=${search}&orderBy=${orderBy}"><fmt:message key="global.next" bundle="${bundle}"/></a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
    <!--PAGINATION END-->
</div>
<%@ include file="/WEB-INF/jspf/foother.jspf" %>
</body>
</html>
