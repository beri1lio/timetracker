<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:set var="currentPageName" value="categories" scope="session"/>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <%@ include file="/WEB-INF/jspf/header.jspf" %>
        </div>
    </div>
    <div class="col">
        <c:set var="activePage" value="categories" scope="session"/>
        <%@ include file="/WEB-INF/jspf/admin-navigation.jspf" %>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="global.category" bundle="${bundle}"/></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${data}" var="category">
            <tr>
                <td>${category.name}</td>
                <td>
                    <form class="row gy-2 gx-3 align-items-center" action="/delete-category" method="post">
                        <div class="input-group">
                            <input type="hidden" name="category-id" value="${category.id}">
                            <button class="btn btn-outline-secondary" type="submit"><fmt:message key="global.delete" bundle="${bundle}"/></button>
                        </div>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
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
                           href="/category?currentPage=${currentPage - 1}&search=${search}&orderBy=${orderBy}"><fmt:message key="global.previous" bundle="${bundle}"/></a>
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
                           href="/category?currentPage=${currentPage + 1}&search=${search}&orderBy=${orderBy}"><fmt:message key="global.next" bundle="${bundle}"/></a>
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
