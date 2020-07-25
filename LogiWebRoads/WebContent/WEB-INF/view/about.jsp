<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href='<spring:url value="resources/css/bootstrap.min.css"/>' rel="stylesheet" type="text/css">
    <title>LogiWeb</title>
</head>

<body style="background-image: url('<spring:url value="resources/pic/back.jpg"/>'); background-position:50%  -15%">

<nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
    <a class="navbar-brand" href="#">LogiWeb</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navb"
            aria-expanded="true">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div id="navb" class="navbar-collapse collapse hide">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="index">Home</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="about">About</a>
            </li>
        </ul>
    </div>
</nav>

<footer class="footer mt-auto py-3"
        style="position: absolute; bottom: 0; width: 100%; background-color: rgba(199,199,199,0.56); padding-left: 15px">
    <div class="container-flexible">
        <span>LogiWeb: Roads</span>
    </div>
</footer>

<script src='<spring:url value="resources/js/jquery-3.5.1.slim.min.js"/>'></script>
<script src='<spring:url value="resources/js/popper.min.js"/>'></script>
<script src='<spring:url value="resources/js/bootstrap.min.js"/>'></script>
</body>
</html>