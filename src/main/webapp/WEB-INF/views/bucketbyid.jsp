<jsp:useBean id="max" scope="request" type="java.lang.Integer"/>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Internet-shop</title>
</head>
<body>
<h3 style="color: crimson">Welcome to the internet-shop!</h3>
<form action="bucket" method="post">
    <p>Please enter your bucket u want</p>
    <hr>
    <label for="bucket"><b>BucketID</b></label>
    <input type="number" placeholder="bucket" name="bucket" id="bucket" min="0" max="${max}" required>
    <hr>
    <button type="SUBMIT">Enter</button>
</form>
</body>
</html>
