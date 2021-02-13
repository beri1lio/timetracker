<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <nav class="navbar navbar-light bg-light">
                <div class="container-fluid">
                    <a class="navbar-brand" href="/">
                        <img src="resources/img/clock.jpg" alt="" width="30" height="24"
                             class="d-inline-block align-top">
                        TimeTracker
                    </a>
                </div>
            </nav>
        </div>
    </div>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col">Task</th>
            <th scope="col">Time</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${userTableData}" var="task">
            <tr>
                <td>${task.name}</td>
                <td>${task.time}</td>
                <td>
                    <form class="row gy-2 gx-3 align-items-center" action="/update-time" method="post">
                        <div class="input-group">
                            <input type="hidden" name="task-id" value="${task.id}">
                            <input type="text" name="time" class="form-control" aria-describedby="time">
                            <button class="btn btn-outline-secondary" type="submit">Update</button>
                        </div>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
