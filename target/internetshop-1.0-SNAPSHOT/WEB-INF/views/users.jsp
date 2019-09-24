<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="users" scope="request" type="java.util.List<mate.academy.ishop.model.User>"/>
<jsp:useBean id="greeting" scope="request" type="java.lang.String"/>
<%--
  Created by IntelliJ IDEA.
  User: kolmogorov
  Date: 9/17/19
  Time: 7:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
Hi, somebody ${greeting}
Users : ${users}

<table>
    <!-- here should go some titles... -->
    <tr>
        <th>Login</th>
        <th>ID</th>
    </tr>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>
                <c:out value="${user.userId}" />
            </td>
            <td>
                <c:out value="${user.login}" />
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
