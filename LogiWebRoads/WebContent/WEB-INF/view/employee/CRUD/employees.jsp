<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href='../resources/css/bootstrap.min.css' rel="stylesheet" type="text/css">
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
                <a class="nav-link" href="../loginPage">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="../about">About</a>
            </li>
        </ul>
    </div>
</nav>

<div id="content">
    <div id="wrapper">
        <div id="Header">
            <h2>Employees list</h2>
        </div>
    </div>
    <input type="button" value="Add employee" onclick="window.location.href='create';return false;"/>
    <table>
        <tr>
            <th>Id</th>
            <th>Login</th>
            <th>Password</th>
            <th>Action</th>
        </tr>
        <c:forEach var="employee" items="${employees}">
            <c:url var="updateLink" value="/employees/update">
                <c:param name="employeeId" value="${employee.id}"/>
            </c:url>
            <c:url var="deleteLink" value="/employees/delete">
                <c:param name="employeeId" value="${employee.id}"/>
            </c:url>
            <tr>
                <td>${employee.id}</td>
                <td><a href="${pageContext.request.contextPath}/employees/employeeDesk">${employee.login}</a></td>
                <td>${employee.password}</td>
                <td><a href="${updateLink}">Update</a>
                    <a href="${deleteLink}"
                       onclick="if (!(confirm('Please confirm deleting employee id=${employee.id}'))) return false">Delete</a>
                </td>

            </tr>
        </c:forEach>
    </table>
</div>

<footer class="footer mt-auto py-3"
        style="position: absolute; bottom: 0; width: 100%; background-color: rgba(199,199,199,0.56); padding-left: 15px">
    <div class="container-flexible">
        <span>LogiWeb: Roads</span>
    </div>
</footer>

<script src='${pageContext.request.contextPath}/resources/js/jquery-3.5.1.slim.min.js'></script>
<script src='${pageContext.request.contextPath}/resources/js/popper.min.js'></script>
<script src='${pageContext.request.contextPath}/resources/js/bootstrap.min.js'></script>
</body>
</html>