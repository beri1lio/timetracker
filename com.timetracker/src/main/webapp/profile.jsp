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
    <!-- CREATE TASK START-->
    <div class="col-8">
        <form class="row gy-2 gx-3 align-items-center" action="/request-create-task" method="post">
            <div class="col-auto">
                <label class="visually-hidden" for="taskNameInput">Task</label>
                <input type="text" name="taskName" class="form-control" id="taskNameInput"
                       placeholder="Task">
            </div>
            <div class="col-auto">
                <label class="visually-hidden" for="categorySelect">Category</label>
                <select class="form-select" id="categorySelect" name="categoryID">
                    <option selected>Category...</option>
                    <c:forEach items="${categories}" var="category">
                        <option value="${category.id}">${category.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-primary">Create task</button>
            </div>
        </form>
    </div>
    <!-- CREATE TASK END-->
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col">Task</th>
            <th scope="col">Time</th>
            <th scope="col"></th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>

        <c:forEach items="${data}" var="task">
            <c:if test="${task.status == 'APPROVED'}">
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

                    <td>
                        <form class="row gy-2 gx-3 align-items-center" action="/request-delete-task" method="post">
                            <div class="input-group">
                                <input type="hidden" name="task-id" value="${task.id}">
                                <button class="btn btn-outline-secondary" type="submit">Delete</button>
                            </div>
                        </form>
                    </td>
                </tr>
            </c:if>
            <c:if test="${task.status != 'APPROVED'}">
                <tr class="table-secondary">
                    <td>${task.name}</td>
                    <td>${task.time}</td>
                    <td>
                        <form class="row gy-2 gx-3 align-items-center" action="/update-time" method="post">
                            <div class="input-group">
                                <input type="hidden" name="task-id" value="${task.id}">
                                <input type="text" name="time" class="form-control" aria-describedby="time" disabled>
                                <button class="btn btn-outline-secondary disabled" type="submit">Update</button>
                            </div>
                        </form>
                    </td>
                    <td>
                        <form class="row gy-2 gx-3 align-items-center" action="/request-delete-task" method="post">
                            <div class="input-group">
                                <input type="hidden" name="task-id" value="${task.id}">
                                <button class="btn btn-outline-secondary disabled" type="submit">Delete</button>
                            </div>
                        </form>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
        </tbody>
    </table>
    <!--PAGINATION START-->
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <c:choose>
                <c:when test="${currentPage <= 0}">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link"
                           href="/profile?currentPage=${currentPage - 1}&search=${search}&orderBy=${orderBy}">Previous</a>
                    </li>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${currentPage >= maxPage -1}">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" aria-disabled="true" tabindex="-1">Next</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <a class="page-link"
                           href="/profile?currentPage=${currentPage + 1}&search=${search}&orderBy=${orderBy}">Next</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
    <!--PAGINATION END-->
</div>
</body>
</html>
