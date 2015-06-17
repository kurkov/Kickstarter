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

  <div class="container" style="width: 300px;">
    <c:url value="/j_spring_security_check" var="loginUrl"/>
    <form action="${loginUrl}" method="post">
      <h2 class="form-sigin-heading">Pleas sign in</h2>
      <input type="text" class="form-control" name="j_username" placeholder="Username">
      <input type="password" class="form-control" name="j_password" placeholder="Password">
      <button class="btn btn-lg btn-primary btn-block" type="submit">Enter</button>
    </form>
  </div>

</body>
</html>
