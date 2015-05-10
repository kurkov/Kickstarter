<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Error</title>
  <link rel="stylesheet"
        href="../../bootstrap/css/bootstrap.min.css">
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp"%>
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
