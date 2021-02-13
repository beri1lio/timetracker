<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
userID:<c:out value='${sessionScope.userID}'/>
<c:if test="${!sessionScope.isEmpty()}">
    <a href="/profile">Profile</a>
</c:if>
<p><a href="/authorization">Log in</a></p>
<p><a href="/registration">Sign up</a></p>
<form action="/logout" method="post">
    <button type="submit" class="btn btn-primary">Log out</button>
</form>
</body>
</html>