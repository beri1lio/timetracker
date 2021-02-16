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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
          integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
    <title><fmt:message key="global.users" bundle="${bundle}"/></title>
</head>
<body>
<div class="container">
    <!--HEADER START-->
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
    <!--HEADER END-->
    <div class="row">
        <div class="col">
            <nav class="nav nav-pills flex-column flex-sm-row">
                <a class="flex-sm-fill text-sm-center nav-link " aria-current="page" href="/tasks"><fmt:message key="global.tasks" bundle="${bundle}"/></a>
                <a class="flex-sm-fill text-sm-center nav-link active" href="/users"><fmt:message key="global.users" bundle="${bundle}"/></a>
                <a class="flex-sm-fill text-sm-center nav-link" href="/approve-tasks"><fmt:message key="global.approves" bundle="${bundle}"/></a>
            </nav>
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
<!-- Optional JavaScript; choose one of the two! -->
<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns"
        crossorigin="anonymous"></script>

<!-- Option 2: Separate Popper and Bootstrap JS -->
<!--
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
-->
</body>
</html>
