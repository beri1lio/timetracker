<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <c:set var="currentPageName" value="authorization" scope="session"/>
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
        <div class="col"></div>
        <div class="col">
            <div class="card">
                <img src="resources/img/RegistrationBackGround.png" class="card-img-bottom" alt="...">
                <div class="card-body">
                    <h5 class="card-title"><fmt:message key="global.authorization" bundle="${bundle}"/></h5>
                    <p class="card-text">
                    <form action="/authorization-user" method="post">
                        <div class="mb-3">
                            <label for="InputLogin" class="form-label"><fmt:message key="global.Enter_login" bundle="${bundle}"/></label>
                            <input type="text" class="form-control" id="InputLogin" name="login">
                        </div>
                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label"><fmt:message key="global.password" bundle="${bundle}"/></label>
                            <input type="password" class="form-control" id="exampleInputPassword1" name="password">
                        </div>
                        <button type="submit" class="btn btn-primary"><fmt:message key="global.submit" bundle="${bundle}"/></button>
                    </form>
                    </p>
                </div>
            </div>
        </div>
        <div class="col"></div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/foother.jspf" %>
</body>
</html>