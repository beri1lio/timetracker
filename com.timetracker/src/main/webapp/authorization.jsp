<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "my" uri = "/WEB-INF/tag/taglib.tld"%>

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
                            <c:if test="${!sessionScope.ERRORS.containsKey('login')}">
                                <input type="text" class="form-control" id="InputLogin" name="login">
                            </c:if>
                            <c:if test="${sessionScope.ERRORS.containsKey('login')}">
                                <input type="text" class="form-control is-invalid" id="InputLogin" name="login">
                                <fmt:message key="${sessionScope.ERRORS.get('login')}" bundle="${bundle}"/>
                            </c:if>
                        </div>

                        <c:forEach items="${requestScope}" var="p">
                                    <li>${p.key} -> ${p.value}</li>
                            </c:forEach>

                        <div class="mb-3">
                            <label for="exampleInputPassword1" class="form-label"><fmt:message key="global.password" bundle="${bundle}"/></label>
                            <c:if test="${!sessionScope.ERRORS.containsKey('password')}">
                                <input type="password" class="form-control" id="exampleInputPassword1" name="password">
                            </c:if>
                            <c:if test="${sessionScope.ERRORS.containsKey('password')}">
                                <input type="password" class="form-control  is-invalid" id="exampleInputPassword1" name="password">
                                <fmt:message key="${sessionScope.ERRORS.get('password')}" bundle="${bundle}"/>
                            </c:if>
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