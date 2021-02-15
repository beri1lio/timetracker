<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <title>Tasks</title>
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

    <div class="row">
        <div class="col">
            <nav class="nav nav-pills flex-column flex-sm-row">
                <a class="flex-sm-fill text-sm-center nav-link active" aria-current="page" href="/tasks">Tasks</a>
                <a class="flex-sm-fill text-sm-center nav-link" href="/users">Users</a>
                <a class="flex-sm-fill text-sm-center nav-link" href="/approve-tasks">Approves</a>
            </nav>
        </div>
    </div>


    <div class="row" style="padding-top: 6%;">
        <div class="col">
            <div class="row">
                <!-- CREATE CATEGORY START-->
                <div class="col">
                    <form class="row g-3" action="/category" method="post">
                        <div class="col-auto">
                            <label for="inputCategory" class="visually-hidden">Category name</label>
                            <input type="text" name="category" class="form-control" id="inputCategory"
                                   placeholder="Category name">
                        </div>
                        <div class="col-auto">
                            <button type="submit" class="btn btn-primary mb-3">Create category</button>
                        </div>
                    </form>
                </div>
                <!-- CREATE CATEGORY END-->
                <!-- CREATE TASK START-->
                <div class="col">
                    <form class="row gy-2 gx-3 align-items-center" action="/task" method="post">
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
                            <label class="visually-hidden" for="userSelect">User</label>
                            <select class="form-select" id="userSelect" name="userID">
                                <option selected>User...</option>
                                <c:forEach items="${users}" var="user">
                                    <c:if test="${user.id != 1}">
                                    <option value="${user.id}">"${user.name}"</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-auto">
                            <button type="submit" class="btn btn-primary">Create task</button>
                        </div>
                    </form>
                </div>
                <!-- CREATE TASK END-->
            </div>
            <!-- SEARCH AND SORT START-->
            <div class="row">
                <div class="col">
                    <div class="card">
                        <div class="card-body">
                            <form action="/tasks" method="get">
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" name="search" placeholder="Search"
                                           aria-label="Search">
                                </div>
                                <div class="row">
                                    <div class="col">
                                        <div class="input-group mb-3">
                                            <div class="form-check form-check-inline">
                                                <c:choose>
                                                    <c:when test="${orderBy == 'task.name'}">
                                                        <input class="form-check-input" type="radio" name="orderBy"
                                                               id="flexRadioDefault1" value="task.name" checked>
                                                    </c:when>
                                                    <c:when test="${orderBy != 'task.name'}">
                                                        <input class="form-check-input" type="radio" name="orderBy"
                                                               value="task.name"
                                                               id="flexRadioDefault1">
                                                    </c:when>
                                                </c:choose>
                                                <label class="form-check-label" for="flexRadioDefault1">
                                                    Sort by task
                                                </label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <c:choose>
                                                    <c:when test="${orderBy == 'category.name'}">
                                                        <input class="form-check-input" type="radio" name="orderBy"
                                                               value="category.name"
                                                               id="flexRadioDefault2" checked>
                                                    </c:when>
                                                    <c:when test="${orderBy != 'category.name'}">
                                                        <input class="form-check-input" type="radio" name="orderBy"
                                                               value="category.name"
                                                               id="flexRadioDefault2">
                                                    </c:when>
                                                </c:choose>
                                                <label class="form-check-label" for="flexRadioDefault2">
                                                    Sort by category
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col">
                                        <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                            <button class="btn btn-primary me-md-2" type="submit">Search</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- SEARCH AND SORT END-->

            <!-- TABLE START-->
            <div class="row">
                <div class="col">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Task</th>
                            <th scope="col">Category</th>
                            <th scope="col">Time</th>
                            <th scope="col">User</th>
                            <th scope="col"></th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${data}" var="task">
                                <tr>
                                    <td>${task.id}</td>
                                    <td>${task.name}</td>
                                    <td>${task.categoryName}</td>
                                    <td>${task.time}</td>
                                    <td>${task.userName}</td>
                                    <td>
                                        <form class="row gy-2 gx-3 align-items-center" action="/delete-task"
                                              method="post">
                                            <div class="input-group">
                                                <input type="hidden" name="task-id" value="${task.id}">
                                                <button class="btn btn-outline-secondary" type="submit">Delete</button>
                                            </div>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- TABLE END-->
            <div class="row">
                <div class="col">
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
                                           href="/tasks?currentPage=${currentPage - 1}&search=${search}&orderBy=${orderBy}">Previous</a>
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
                                           href="/tasks?currentPage=${currentPage + 1}&search=${search}&orderBy=${orderBy}">Next</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </nav>
                    <!--PAGINATION END-->
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Optional JavaScript; choose one of the two! -->
<!-- Option 1: jQuery and Bootstrap Bundle (includes Popper) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
        integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>


<!-- Option 2: Separate Popper and Bootstrap JS -->
<!--
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>
-->
</body>
</html>
