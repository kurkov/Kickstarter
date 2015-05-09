<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Error</title>
  <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <div class="panel panel-danger">
    <div class="panel-heading">Error</div>
    <div class="panel-body">
      Message: <c:out value="${error}"/>
    </div>
  </div>
</div>
</body>
</html>
