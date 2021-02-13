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
    <form action="/registration-user" method="post">
        <div class="mb-3">
            <input type="hidden" class="form-control" id="user-id" name="user-id">
        </div>
        <div class="mb-3">
            <label for="InputLogin" class="form-label">Enter login</label>
            <input type="text" class="form-control" id="InputLogin" name="login">
        </div>
        <div class="mb-3">
            <label for="InputName" class="form-label">Enter name</label>
            <input type="text" class="form-control" id="InputName" name="name">
        </div>
        <div class="mb-3">
            <label class="visually-hidden" for="categorySelect">Role</label>
            <select class="form-select" id="categorySelect" name="category-id">
                <option value="CLIENT">CLIENT</option>
                <option value="ADMIN">ADMIN</option>
            </select>
        </div>
        <button type="submit" class="btn btn-primary">Edit</button>
    </form>
</div>
</body>
</html>
