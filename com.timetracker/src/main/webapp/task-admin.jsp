<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "my" uri = "/WEB-INF/tag/taglib.tld"%>

<html>
<head>
    <c:set var="currentPageName" value="tasks" scope="session"/>
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


    <div class="row" style="padding-top: 6%;">
        <div class="col">
            <div class="row">
                <!-- CREATE CATEGORY START-->
                <div class="col-4">
                    <form class="row g-3" action="/category" method="post">
                        <div class="col-auto">
                            <label for="inputCategory" class="visually-hidden"><fmt:message key="global.category_name"
                                                                                            bundle="${bundle}"/></label>
                           <c:if test="${!sessionScope.ERRORS.containsKey('category')}">
                            <input type="text" name="category" class="form-control" id="inputCategory"
                                   placeholder=<fmt:message key="global.category_name" bundle="${bundle}"/>>
                           </c:if>
                            <c:if test="${sessionScope.ERRORS.containsKey('category')}">
                            <input type="text" name="category" class="form-control is-invalid" id="inputCategory"
                                   placeholder=<fmt:message key="global.category_name" bundle="${bundle}"/>>
                           </c:if>
                        </div>
                        <div class="col-auto">
                            <button type="submit" class="btn btn-primary mb-3"><fmt:message key="global.create_category"
                                                                                            bundle="${bundle}"/></button>
                        </div>
                    </form>
                </div>
                <!-- CREATE CATEGORY END-->
                <!-- CREATE TASK START-->
                <div class="col-8">
                    <form class="row gy-2 gx-3 align-items-center" action="/task" method="post">
                        <div class="col-auto">
                            <label class="visually-hidden" for="taskNameInput"><fmt:message key="global.task"
                                                                                            bundle="${bundle}"/></label>
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
                            <label class="visually-hidden" for="categorySelect"><fmt:message key="global.category"
                                                                                             bundle="${bundle}"/></label>
                            <c:if test="${!sessionScope.ERRORS.containsKey('categoryID')}">
                            <select class="form-select" id="categorySelect" name="categoryID">
                                <option selected value=""><fmt:message key="global.category" bundle="${bundle}"/>...
                                </option>
                                <c:forEach items="${categories}" var="category">
                                    <option value="${category.id}">${category.name}</option>
                                </c:forEach>
                            </select>
                            </c:if>
                            <c:if test="${sessionScope.ERRORS.containsKey('categoryID')}">
                            <select class="form-select is-invalid" id="categorySelect" name="categoryID">
                                <option selected value=""><fmt:message key="global.category" bundle="${bundle}"/>...
                                </option>
                                <c:forEach items="${categories}" var="category">
                                    <option value="${category.id}">${category.name}</option>
                                </c:forEach>
                            </select>
                            </c:if>

                        </div>
                        <div class="col-auto">
                            <label class="visually-hidden" for="userSelect"><fmt:message key="global.user"
                                                                                 bundle="${bundle}"/></label>

                            <c:if test="${!sessionScope.ERRORS.containsKey('userID')}">
                            <select class="form-select" id="userSelect" name="userID">
                                <option selected value=""><fmt:message key="global.user" bundle="${bundle}"/>...
                                </option>
                                <c:forEach items="${users}" var="user">
                                    <option value="${user.id}">${user.name}</option>
                                </c:forEach>
                            </select>
                            </c:if>
                            <c:if test="${sessionScope.ERRORS.containsKey('userID')}">
                            <select class="form-select is-invalid" id="userSelect" name="userID">
                                <option selected value=""><fmt:message key="global.user" bundle="${bundle}"/>...
                                </option>
                                <c:forEach items="${users}" var="user">
                                    <option value="${user.id}">${user.name}</option>
                                </c:forEach>
                            </select>
                        </c:if>

                        </div>
                        <div class="col-auto">
                            <button type="submit" class="btn btn-primary"><fmt:message key="global.create_task"
                                                                                       bundle="${bundle}"/></button>
                        </div>
                    </form>
                </div>
                <!-- CREATE TASK END-->
            </div>
            <!-- SEARCH AND SORT START-->
            <div class="row">
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <form action="/tasks" method="get">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" name="search" placeholder=
                                    <fmt:message key="global.search" bundle="${bundle}"/>
                                            aria-label="Search">
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <div class="input-group mb-3">
                                            <div class="form-check form-check-inline">
                                                <c:choose>
                                                    <c:when test="${orderBy == 'task.name'}">
                                                        <input class="form-check-input" type="radio" name="orderBy"
                                                               id="flexRadioDefault1" value="task.name" checked>
                                                    </c:when>
                                                    <c:when test="${orderBy != 'task.name'}">
                                                        <input class="form-check-input" type="radio" name="orderBy"
                                                               value="task.name"
                                                               id="flexRadioDefault1">
                                                    </c:when>
                                                </c:choose>
                                                <label class="form-check-label" for="flexRadioDefault1">
                                                    <fmt:message key="global.sort_by_task" bundle="${bundle}"/>
                                                </label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <c:choose>
                                                    <c:when test="${orderBy == 'category.name'}">
                                                        <input class="form-check-input" type="radio" name="orderBy"
                                                               value="category.name"
                                                               id="flexRadioDefault2" checked>
                                                    </c:when>
                                                    <c:when test="${orderBy != 'category.name'}">
                                                        <input class="form-check-input" type="radio" name="orderBy"
                                                               value="category.name"
                                                               id="flexRadioDefault2">
                                                    </c:when>
                                                </c:choose>
                                                <label class="form-check-label" for="flexRadioDefault2">
                                                    <fmt:message key="global.sort_by_category" bundle="${bundle}"/>
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col">
                                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                            <button class="btn btn-primary me-md-2" type="submit"><fmt:message
                                                    key="global.search" bundle="${bundle}"/></button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- SEARCH AND SORT END-->

            <!-- TABLE START-->
            <div class="row">
                <div class="col">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col"><fmt:message key="global.id" bundle="${bundle}"/></th>
                            <th scope="col"><fmt:message key="global.task" bundle="${bundle}"/></th>
                            <th scope="col"><fmt:message key="global.category" bundle="${bundle}"/></th>
                            <th scope="col"><fmt:message key="global.time" bundle="${bundle}"/></th>
                            <th scope="col"><fmt:message key="global.user" bundle="${bundle}"/></th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${data}" var="task">
                            <tr>
                                <td>${task.id}</td>
                                <td>${task.name}</td>
                                <td>${task.categoryName}</td>
                                <td>${task.time}</td>
                                <td>${task.userName}</td>
                                <td>
                                    <form class="row gy-2 gx-3 align-items-center" action="/delete-task"
                                          method="post">
                                        <div class="input-group">
                                            <input type="hidden" name="task-id" value="${task.id}">
                                            <button class="btn btn-outline-secondary" type="submit"><fmt:message
                                                    key="global.delete" bundle="${bundle}"/></button>
                                        </div>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- TABLE END-->
            <div class="row">
                <div class="col">
                    <!--PAGINATION START-->
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center">
                            <c:choose>
                                <c:when test="${currentPage <= 0}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#" tabindex="-1" aria-disabled="true"><fmt:message
                                                key="global.previous" bundle="${bundle}"/></a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="/tasks?currentPage=${currentPage - 1}&search=${search}&orderBy=${orderBy}"><fmt:message
                                                key="global.previous" bundle="${bundle}"/></a>
                                    </li>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                                <c:when test="${currentPage >= maxPage -1}">
                                    <li class="page-item disabled">
                                        <a class="page-link" href="#" aria-disabled="true" tabindex="-1"><fmt:message
                                                key="global.next" bundle="${bundle}"/></a>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="/tasks?currentPage=${currentPage + 1}&search=${search}&orderBy=${orderBy}"><fmt:message
                                                key="global.next" bundle="${bundle}"/></a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </nav>
                    <!--PAGINATION END-->
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/foother.jspf" %>
</body>
</html>
