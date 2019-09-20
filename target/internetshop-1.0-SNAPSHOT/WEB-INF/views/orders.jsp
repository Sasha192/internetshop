<jsp:useBean id="orders" scope="request" type="java.util.List<mate.academy.ishop.model.Order>"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bucket</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Id</th>
    </tr>
    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.orderId}</td>
            <td><a href="deleteorder?orderid=${order.orderId}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="../items">Return to the shop</a>
</body>
</html>
