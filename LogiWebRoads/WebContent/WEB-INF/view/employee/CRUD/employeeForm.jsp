<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href='${pageContext.request.contextPath}/resources/css/bootstrap.min.css' rel="stylesheet" type="text/css">
    <title>LogiWeb Add new Employee</title>
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
                <a class="nav-link" href="home">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="about">About</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/">Employee list</a>
            </li>
        </ul>
    </div>
    <form:form class="form-inline mt-2 mt-md-0 pull-right" action="${pageContext.request.contextPath}/logout"
               method="post">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Log out</button>
    </form:form>
</nav>

<div id="content">
    <h3>Save new employee form</h3>
    <form:form action="save" modelAttribute="employeeDTO" method="POST">
        <form:hidden path="id"/>
        <table>
            <tbody>
            <tr>
                <td><label>Login</label></td>
                <td><form:input path="login"/></td>
            </tr>
            <tr>
                <td><label>Password</label></td>
                <td><form:input path="password"/></td>
            </tr>
            <tr>
                <td><input type="submit" value="Save"/></td>
                <td><input type="button" value="Back to list"
                           onclick="location.href='${pageContext.request.contextPath}/employees/';return false;"/></td>

            </tr>
            </tbody>
        </table>


    </form:form>
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