<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <link href='${pageContext.request.contextPath}/resources/css/bootstrap.min.css' rel="stylesheet" type="text/css">
    <title>LogiWeb Employee Desk</title>
</head>
<body data-url="${pageContext.request.contextPath}">

<div id="root">
</div>

<script src='${pageContext.request.contextPath}/resources/js/jquery-3.5.1.min.js'></script>
<script src='${pageContext.request.contextPath}/resources/js/popper.min.js'></script>
<script src='${pageContext.request.contextPath}/resources/js/bootstrap.min.js'></script>
<script src="${pageContext.request.contextPath}/resources/js/main.js" crossorigin></script>

</body>
</html>