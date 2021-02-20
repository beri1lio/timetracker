<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "my" uri = "/WEB-INF/tag/taglib.tld"%>

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
        <div class="col"></div>
        <div class="col">
            <div class="card">
                <img src="resources/img/RegistrationBackGround.png" class="card-img-bottom" alt="...">
                <div class="card-body">
                    <p class="card-text">
                        <a class="card-link" href="/authorization">
                            <fmt:message key="global.log_in" bundle="${bundle}"/>
                        </a>
                        <a class="card-link" href="/registration">
                            <fmt:message key="global.sign_up" bundle="${bundle}"/>
                        </a>
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