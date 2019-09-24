<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Access denied</title>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.user == null}">
        <p>You entered as guest!</p>
        <br/>
    </c:when>
    <c:otherwise>
        You entered as ${sessionScope.user.name}
        <br/>
    </c:otherwise>
</c:choose>
<h3 style="color: crimson">Sorry, this page is denied for you :( </h3>
</body>
</html>