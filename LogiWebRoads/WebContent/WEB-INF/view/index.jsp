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
            <li class="nav-item ">
                <a class="nav-link active" href="index">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="about">About</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container fixed-bottom" style="margin-bottom: 50px">
    <div class="row">
        <div class="col-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto text-center form p-4">
            <div class="px-2">
                <form class="form-signin">
                    <h1 class="h3 mb-3 font-weight-normal">Please log in</h1>
                    <label for="inputEmail" class="sr-only">Email address</label>
                    <input type="email" id="inputEmail" class="form-control" placeholder="E-mail" required=""
                           autofocus="">
                    <label for="inputPassword" class="sr-only">Password</label>
                    <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="">
                    <div class="checkbox mb-3">
                        <label>
                            <input type="checkbox" value="remember-me"> Remember me
                        </label>
                    </div>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
                </form>
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

<script src='<spring:url value="resources/js/jquery-3.5.1.slim.min.js"/>'></script>
<script src='<spring:url value="resources/js/popper.min.js"/>'></script>
<script src='<spring:url value="resources/js/bootstrap.min.js"/>'></script>
</body>
</html>