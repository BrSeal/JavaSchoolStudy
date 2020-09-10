<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link href='${pageContext.request.contextPath}/resources/css/bootstrap.min.css' rel="stylesheet" type="text/css">
    <title>LogiWeb</title>
</head>

<body data-url="${pageContext.request.contextPath}" data-status="${dto.status.ordinal()}">

<nav class="navbar navbar-expand-md bg-dark navbar-dark sticky-top">
    <a class="navbar-brand" href="#">LogiWeb</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navb"
            aria-expanded="true">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div id="navb" class="navbar-collapse collapse hide">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="../home">Home</a>
            </li>
            <li class="nav-item active">
                <a class="nav-link" href="../about">About</a>
            </li>
        </ul>
    </div>
    <form:form class="form-inline mt-2 mt-md-0 pull-right" action="${pageContext.request.contextPath}/logout"
               method="post">
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Log out</button>
    </form:form>
</nav>

<div class="container">
    <div class="row">
        <div id="content" class="col-8 p-0">

            <h5>You logged as: <security:authentication property="principal.username"/></h5>
            <div><b>Personal id: ${dto.id}</b></div>
            <div><b>First name: ${dto.firstName}</b></div>
            <div><b>Last name: ${dto.lastName}</b></div>
            <div><b>Status: ${dto.status}</b></div>
            <div><b>Hours worked: ${dto.hoursWorked}</b></div>

            <%-- Current order --%>
            <div>
                <b>
                    Current order:
                    <c:if test="${dto.orderId==0}">
                        None
                    </c:if>
                    <c:if test="${dto.orderId!=0}">
                        ${dto.orderId}
                    </c:if>
                </b>
            </div>

            <%-- If order exists --%>
            <c:if test="${dto.orderId!=0}">

                <%-- Vehicle --%>
                <div><b>Vehicle:${dto.vehicleRegNum}</b></div>

                <%--Additional driver in order --%>
                <div>
                    <b>
                        Additional drivers in order:
                        <c:if test="${dto.drivers.size()==0}">
                            None
                        </c:if>
                        <c:if test="${dto.drivers.size()>0}">
                            <ul>
                                <c:forEach var="driver" items="${dto.drivers}">
                                    <li>${driver.id} ${driver.firstName} ${driver.lastName}</li>
                                </c:forEach>
                            </ul>
                        </c:if>
                    </b>
                </div>
                <br/>


                <%--Current waypoint--%>
                <div>
                    <h4>Current waypoint</h4>
                   <div> <label><b>City: ${dto.currentTarget.city.name}</b></label></div>
                   <div> <label><b>Cargo name: ${dto.currentTarget.cargo.name}</b></label></div>
                   <div> <label><b>Cargo weight: ${dto.currentTarget.cargo.weight}</b></label></div>
                   <div> <label><b>Action: ${dto.currentTarget.type}</b></label></div>

                    <form:form
                            action="${pageContext.request.contextPath}/cargo/updateStatus/"
                            method="post"
                            modelAttribute="cargo">
                        <form:select path="status">
                            <form:option value="1">Transporting</form:option>
                            <form:option value="2">Delivered</form:option>
                        </form:select>
                        <input class="btn btn-secondary" type="submit" value="Complete">
                    </form:form>

                </div>

                <%-- Waypoints --%>
                <div>
                    <b>
                        Waypoints:
                    </b>
                    <table class="table table-sm">
                        <thead class="thead-light">
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Cargo</th>
                            <th scope="col">City</th>
                            <th scope="col">Type</th>
                            <th scope="col">Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="waypoint" items="${dto.waypoints}">
                            <tr>
                                <td>${waypoint.pathIndex}</td>
                                <td>${waypoint.cargo.name}</td>
                                <td>${waypoint.city.name}</td>
                                <td>${waypoint.type}</td>
                                <td>${waypoint.done?"Completed":"Not completed"}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

            </c:if>


        </div>
        <div id="details" class="col-4 p-0">


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
<script src="https://unpkg.com/react@16/umd/react.development.js" crossorigin></script>
<script src="https://unpkg.com/react-dom@16/umd/react-dom.development.js" crossorigin></script>
<script src="https://unpkg.com/@babel/standalone@7.11.6/babel.min.js" crossorigin></script>

<script type="text/babel" src="${pageContext.request.contextPath}/resources/js/src/driverDesk/main.js"></script>
</body>
</html>