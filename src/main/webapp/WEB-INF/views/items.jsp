<jsp:useBean id="user" scope="request" type="mate.academy.ishop.model.User"/>
<jsp:useBean id="items" scope="request" type="java.util.List<mate.academy.ishop.model.Item>"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Items</title>
</head>
<body>
<h3>Hi ${user.login}</h3>
<table border="2">
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Add</th>
    </tr>
    <c:forEach var="item" items="${items}">
        <tr>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td><a href="addToBucket?item_id=${item.idItem}&user=${user.userId}">Add</a></td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="bucket">GO TO BUCKET</a>
<br>
<a href="showAllOrders">GO TO ORDERS</a>
</body>
</html>