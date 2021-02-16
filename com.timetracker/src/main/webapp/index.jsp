<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <title><fmt:message key="global.timetracker" bundle="${bundle}"/></title>
</head>
<body>
userID:<c:out value='${sessionScope.userID}'/>
<c:if test="${!sessionScope.isEmpty()}">
<%--    <c:if test="${sessionScope.userID == 1}">--%>
<%--        <a href="/users">Profile</a>--%>
<%--    </c:if>--%>
    <a href="/profile"><fmt:message key="global.profile" bundle="${bundle}"/></a>
</c:if>
<p><a href="/authorization"><fmt:message key="global.log_in" bundle="${bundle}"/></a></p>
<p><a href="/registration"><fmt:message key="global.sign_up" bundle="${bundle}"/></a></p>
<form action="/logout" method="post">
    <button type="submit" class="btn btn-primary"><fmt:message key="global.log_out" bundle="${bundle}"/></button>
</form>
</body>
</html>