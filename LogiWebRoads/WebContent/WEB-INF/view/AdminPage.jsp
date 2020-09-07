<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href='resources/css/bootstrap.min.css' rel="stylesheet" type="text/css">
    <title>LogiWeb</title>
</head>

<body style="background-image: url(resources/pic/back.jpg); background-position:50%  -15%">

<nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
    <a class="navbar-brand" href="#">LogiWeb</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navb"
            aria-expanded="true">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div id="navb" class="navbar-collapse collapse hide">
        <ul class="navbar-nav">
            <li class="nav-item ">
                <a class="nav-link active" href="#">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="about">About</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="employees/">Employee list</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="employees/">Login page</a>
            </li>
        </ul>

        <form:form class="form-inline mt-2 mt-md-0" action="${pageContext.request.contextPath}/logout" method="post">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form:form>
    </div>
</nav>

<div class="container fixed-bottom" style="margin-bottom: 50px">
    <h1>Admin page</h1>
</div>
<footer class="footer mt-auto py-3"
        style="position: absolute; bottom: 0; width: 100%; background-color: rgba(199,199,199,0.56); padding-left: 15px">
    <div class="container-flexible">
        <span>LogiWeb: Roads</span>
    </div>
</footer>

<script src='resources/js/jquery-3.5.1.min.js'></script>
<script src='resources/js/popper.min.js'></script>
<script src='resources/js/bootstrap.min.js'></script>
</body>
</html>