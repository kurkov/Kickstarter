<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <title></title>
</head>
<body>
<div class="container">
    <br>
    <div class="list-group">
        <c:forEach var="c" items="${projects}">
        <a href="/project/<c:out value="${c.id}"/>" class="list-group-item"><c:out value="${c.name}"/></a>
        </c:forEach>
    </div>
</div>

</body>
</html>
