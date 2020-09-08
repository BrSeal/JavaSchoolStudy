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
</nav>

<div class="container fixed-bottom" style="margin-bottom: 50px">
    <div class="row">
        <div class="col-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto text-center form p-4">
            <div class="px-2" style="background-color: #c2c2c2; border-radius:10px">

                <form:form class="form-signin" action="${pageContext.request.contextPath}/authenticate" method="POST">
                    <h1 class="h3 mb-3 font-weight-normal">Please log in</h1>
                    <c:if test="${param.error !=null}">
                        <div class="alert alert-danger">Wrong Username or password</div>
                    </c:if>
                    <c:if test="${param.logout !=null}">
                        <div class="alert alert-success">You successfully logged out</div>
                    </c:if>
                    <label for="inputUsername" class="sr-only">Login</label>
                    <input type="text" id="inputUsername" name="username" class="form-control" placeholder="Username"
                           required=""
                           autofocus="">
                    <label for="inputPassword" class="sr-only">Password</label>
                    <input type="password" name="password" id="inputPassword" class="form-control"
                           placeholder="Password" required="">
                    <div class="checkbox mb-3">
                        <label>
                            <input type="checkbox" value="remember-me"> Remember me
                        </label>
                    </div>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                </form:form>
            </div>
        </div>
    </div>
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