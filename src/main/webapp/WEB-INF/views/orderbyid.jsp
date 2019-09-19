<jsp:useBean id="max" scope="request" type="java.lang.Integer"/>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Internet-shop</title>
</head>
<body>
<h3 style="color: crimson">Welcome to the internet-shop!</h3>
<form action="orders" method="post">
    <p>Please enter order u want</p>
    <hr>
    <label for="order"><b>OrderID</b></label>
    <input type="number" placeholder="order" name="order" id="order" min="0" max="${max}" required>
    <hr>
    <button type="SUBMIT">Enter</button>
</form>
</body>
</html>
