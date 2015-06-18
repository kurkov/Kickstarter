<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <link href="<c:url value="/bootstrap/css/bootstrap.min.css"/>"
        rel="stylesheet">
  <title></title>
</head>
<body>
  <%@include file="/WEB-INF/jsp/header.jsp" %>

  <c:if test="${param.error != null}">
    <p>Invalid username / password</p>
  </c:if>
  <c:url var="loginUrl" value="/login"/>
  <form action="${loginUrl}" method="post">
    <p><label for="username">User:</label></p>
    <input type="text" id="username" name="username"/>

    <p><label for="password">Password:</label></p>
    <input type="password" id="password" name="password">

    <div>
      <input name="submit" type="submit" value="Login"/>
    </div>
  </form>

</body>
</html>
