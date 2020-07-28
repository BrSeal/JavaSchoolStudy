<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href='../resources/css/bootstrap.min.css' rel="stylesheet" type="text/css">
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
                <a class="nav-link" href="../index">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="../about">About</a>
            </li>
        </ul>
    </div>
</nav>

<div id="content">
    <h3>Save new employee form</h3>
   <form:form action="saveEmployee" modelAttribute="employee" method="POST">
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
                <td> <input type="button" value="Back to list" onclick="location.href='../employees/';return false;"/></td>

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

<script src='../resources/js/jquery-3.5.1.slim.min.js'></script>
<script src='../resources/js/popper.min.js'></script>
<script src='../resources/js/bootstrap.min.js'></script>
</body>
</html>