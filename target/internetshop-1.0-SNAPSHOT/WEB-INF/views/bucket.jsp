<jsp:useBean id="bucket" scope="request" type="mate.academy.ishop.model.Bucket"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Bucket</title>
</head>
<body>
<table border="1">
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Delete</th>
    </tr>

    <c:forEach var="item" items="${bucket.itemsList}">
        <tr>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <td><a href="delete?bid=${bucket.bucketId}&itemid=${item.idItem}">Delete</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td><a href="complete?bid=${bucket.bucketId}">Buy</a></td>
    </tr>
</table>
<br>
<a href="../items">Return to the shop</a>
</body>
</html>