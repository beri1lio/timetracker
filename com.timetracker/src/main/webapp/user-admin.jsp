<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "my" uri = "/WEB-INF/tag/taglib.tld"%>

<html>
<head>
    <c:set var="currentPageName" value="users" scope="session"/>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<div class="container">
    <!--HEADER START-->
    <div class="row">
    <div class="row">
        <div class="col">
            <%@ include file="/WEB-INF/jspf/header.jspf" %>
        </div>
    </div>
    <!--HEADER END-->
    <div class="row">
        <div class="col">
            <c:set var="activePage" value="users" scope="session"/>
            <%@ include file="/WEB-INF/jspf/admin-navigation.jspf" %>
        </div>
    </div>

    <div class="row" style="padding-top: 6%;">
        <div class="col">
            <!--SEARCH AND SORT START-->
            <div class="card">
                <div class="card-body">
                    <form action="/users" method="get">
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" name="search" placeholder=<fmt:message key="global.search" bundle="${bundle}"/> aria-label="Search">
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="input-group mb-3">
                                    <div class="form-check form-check-inline">
                                        <c:choose>
                                            <c:when test="${orderBy == 'NAME'}">
                                                <input class="form-check-input" type="radio" name="orderBy"
                                                       id="flexRadioDefault1" value="NAME" checked>
                                            </c:when>
                                            <c:when test="${orderBy != 'NAME'}">
                                                <input class="form-check-input" type="radio" name="orderBy" value="NAME"
                                                       id="flexRadioDefault1">
                                            </c:when>
                                        </c:choose>
                                        <label class="form-check-label" for="flexRadioDefault1">
                                            <fmt:message key="global.sort_by_name" bundle="${bundle}"/>
                                        </label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <c:choose>
                                            <c:when test="${orderBy == 'LOGIN'}">
                                                <input class="form-check-input" type="radio" name="orderBy" value="LOGIN"
                                                       id="flexRadioDefault2" checked>
                                            </c:when>
                                            <c:when test="${orderBy != 'LOGIN'}">
                                                <input class="form-check-input" type="radio" name="orderBy" value="LOGIN"
                                                       id="flexRadioDefault2">
                                            </c:when>
                                        </c:choose>
                                        <label class="form-check-label" for="flexRadioDefault2">
                                            <fmt:message key="global.sort_by_login" bundle="${bundle}"/>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="col">
                                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                    <button class="btn btn-primary me-md-2" type="submit"><fmt:message key="global.search" bundle="${bundle}"/></button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <!--SEARCH AND SORT END-->
            <!--TABLE START-->
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col"><fmt:message key="global.id" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="global.login" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="global.name" bundle="${bundle}"/></th>
                    <th scope="col"><fmt:message key="global.role" bundle="${bundle}"/></th>
                    <th scope="col"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${data}" var="user">
                    <c:if test="${user.id != sessionScope.userID}">
                    <tr>
                        <td>${user.id}</td>
                        <td>${user.login}</td>
                        <td>${user.name}</td>
                        <td>${user.role}</td>

                        <td>
                            <form class="row gy-2 gx-3 align-items-center" action="/delete-user" method="post">
                                <div class="input-group">
                                    <input type="hidden" name="user-id" value="${user.id}">
                                    <button class="btn btn-outline-secondary" type="submit"><fmt:message key="global.delete" bundle="${bundle}"/></button>
                                </div>
                            </form>
                        </td>
                    </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
            <!--TABLE END-->
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
                                <a class="page-link" href="/users?currentPage=${currentPage - 1}&search=${search}&orderBy=${orderBy}"><fmt:message key="global.previous" bundle="${bundle}"/></a>
                            </li>
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${currentPage >= maxPage -1}">
                            <li class="page-item disabled">
                                <a class="page-link" href="#" aria-disabled="true" tabindex="-1" ><fmt:message key="global.next" bundle="${bundle}"/></a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="page-item">
                                <a class="page-link" href="/users?currentPage=${currentPage + 1}&search=${search}&orderBy=${orderBy}"><fmt:message key="global.next" bundle="${bundle}"/></a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </nav>
            <!--PAGINATION END-->
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/foother.jspf" %>
</body>
</html>
