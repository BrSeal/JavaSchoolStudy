<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
            <li class="nav-item active">
                <a class="nav-link" href="#">Employee Desk</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/loginPage">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/about">About</a>
            </li>
        </ul>
    </div>
</nav>

<div id="content" class="container">
    <!-- TODO добавить динамический вывод инфы на страничку-->
    <div class="row">
        <div id="orders" class="col-6">
            <h1>Orders</h1>
            <table>
                <tr>
                    <th>Id</th>
                    <th>Creation time</th>
                    <th>Status</th>
                </tr>
                <c:forEach var="order" items="${orders}">
                <c:url var="detailsLink" value="/orders/details">
                    <c:param name="orderId" value="${order.id}"/>
                </c:url>
                <tr>
                    <td>${order.id}</td>
                    <td>${order.creationDate}</td>
                    <td>${order.status}</td>
                </tr>
            </table>
        </div>
        <div  id="drivers" class="col-sm">
            <h1>Drivers</h1>
        </div>
        <div  id="vehicles" class="col-sm">
            <h1>Vehicles</h1>
        </div>
    </div>
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