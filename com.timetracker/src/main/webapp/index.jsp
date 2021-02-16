<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <c:set var="currentPageName" value="index" scope="session"/>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <%@ include file="/WEB-INF/jspf/header.jspf" %>
        </div>
    </div>
    <div class="row" style="padding-top: 9%;">
        <div class="col">
            <div class="card">
                <img src="resources/img/RegistrationBackGround.png" class="card-img-bottom" alt="...">
                <div class="card-body">
                    <h5 class="card-title"><fmt:message key="global.registration" bundle="${bundle}"/></h5>
                    <p class="card-text">
                        <c:if test="${!sessionScope.isEmpty()}">
                            <fmt:message key="global.userID" bundle="${bundle}"/>:<c:out value='${sessionScope.userID}'/>
                        <c:choose>
                        <c:when test="${sessionScope.userID == 1}">
                        <a href="/users"><fmt:message key="global.profile" bundle="${bundle}"/></a>
                        </c:when>
                        <c:otherwise>
                        <a href="/profile"><fmt:message key="global.profile" bundle="${bundle}"/></a>
                        </c:otherwise>
                        </c:choose>
                        </c:if>
                        <a class="card-link" href="/authorization"><fmt:message key="global.log_in" bundle="${bundle}"/>
                        <a class="card-link" href="/registration"><fmt:message key="global.sign_up" bundle="${bundle}"/>
                        <form action="/logout" method="post">
                            <button type="submit" class="btn btn-primary"><fmt:message key="global.log_out" bundle="${bundle}"/></button>
                        </form>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/jspf/foother.jspf" %>
</body>
</html>