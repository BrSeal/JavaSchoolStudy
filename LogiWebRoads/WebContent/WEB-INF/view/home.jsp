<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href='${pageContext.request.contextPath}/resources/css/bootstrap.min.css' rel="stylesheet" type="text/css">
    <title>LogiWeb</title>
</head>

<body>

<nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
    <a class="navbar-brand" href="#">LogiWeb</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navb"
            aria-expanded="true">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div id="navb" class="navbar-collapse collapse hide">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link active" href="#">Home</a>
            </li>
            <li class="nav-item ">
                <a class="nav-link" href="about">About</a>
            </li>
        </ul>
    </div>
    <form:form class="form-inline mt-2 mt-md-0 pull-right" action="${pageContext.request.contextPath}/logout"
               method="post">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Log out</button>
    </form:form>
</nav>

<div id="content">
    <h1>Home page</h1>
    <div><h5>You logged as:<security:authentication property="principal.username"/></h5></div>
    <security:authorize access="hasRole('ADMIN')">
        <div><a class="btn btn-primary" href="${pageContext.request.contextPath}/adminPage">
            Administration Page (Admin)
        </a></div>
    </security:authorize>

    <security:authorize access="hasRole('EMPLOYEE')">
        <div><a class="btn btn-primary" href="${pageContext.request.contextPath}/employeeDesk/">Employee Desk
            (Manager)</a>
        </div>
    </security:authorize>

    <security:authorize access="hasRole('DRIVER')">
        <div><a class="btn btn-primary" href="${pageContext.request.contextPath}/driverDesk/">Drivers Desk (Driver)</a>
        </div>
    </security:authorize>

</div>
<footer class="footer mt-auto py-3"
        style="position: absolute; bottom: 0; width: 100%; background-color: rgba(199,199,199,0.56); padding-left: 15px">
    <div class="container-flexible">
        <span>LogiWeb: Roads</span>
    </div>
</footer>

<script src='${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js'></script>
<script src='${pageContext.request.contextPath}/resources/js/popper.min.js'></script>
<script src='${pageContext.request.contextPath}/resources/js/bootstrap.min.js'></script>
</body>
</html>