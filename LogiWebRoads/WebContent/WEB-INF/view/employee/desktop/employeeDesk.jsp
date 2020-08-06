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
                <a class="nav-link" href="${pageContext.request.contextPath}/">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/about">About</a>
            </li>
        </ul>
    </div>
</nav>

<div class="container">

    <div id="buttons">
        <button id="orders-btn" onclick="showOrders()">Orders</button>
        <button id="Drivers-btn" onclick="showDrivers()">Drivers</button>
        <button id="Vehicles-btn" onclick="showVehicles()">Vehicles</button>
    </div>
    <div id="add-button-holder" class="row">

    </div>
    <div class="row">
        <div id="content" class="row col-8">

        </div>

        <div id="details" class="col-4">

        </div>
    </div>
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
<script src="${pageContext.request.contextPath}/resources/js/react.development.js" crossorigin></script>
<script src="${pageContext.request.contextPath}/resources/js/react-dom.development.js" crossorigin></script>
<script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/employeeDesc/driverLogic.js" type="text/babel"></script>
<script src="${pageContext.request.contextPath}/resources/js/employeeDesc/vehicleLogic.js" type="text/babel"></script>
<script src="${pageContext.request.contextPath}/resources/js/employeeDesc/orderLogic.js" type="text/babel"></script>
</body>
</html>