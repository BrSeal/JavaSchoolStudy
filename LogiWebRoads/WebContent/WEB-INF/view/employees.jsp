<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>Employees</title>
</head>
<body>
<div id="wrapper">
    <div id="Header">
        <h2>Employees list</h2>
    </div>
</div>
<div id="container">
    <div id="content">
        <table>
            <tr>
                <th>Login</th>
                <th>Password</th>
            </tr>
            <c:forEach var="employee" items="${employees}">
                <tr>
                    <td>${employee.login}</td>
                    <td>${employee.password}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
