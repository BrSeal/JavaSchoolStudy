<html>
<head>
    <title>LogiWeb Roads Client</title>
    <link link="css/style.css}" rel="stylesheet">
    <script resource="js/jquery-3.5.1.min.js}" type="text/javascript"></script>
    <script resource="js/main.js}" type="text/javascript"></script>
</head>
<body>
    <div id="buttons">
    <button id="show-get-form">Get</button>
    <button id="show-insert-form">Insert</button>
    </div>
    <form id="get-form">
        Get from DB <input id="get-in" type="text" size="40">
        <button id="button-get">Get data</button>
    </form>
    <form id="insert-form">
        Insert into DB <input id="insert-in" type="text" size="40">
        <button id="button-insert">Get data</button>
    </form>
    <h3>
        <a href="welcome.html">Click here to See Welcome Message... </a>(to
        check Spring MVC Controller... @RequestMapping("/welcome"))
    </h3>
</body>
</html>