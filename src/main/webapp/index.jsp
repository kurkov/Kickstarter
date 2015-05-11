<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
  <link href="<c:url value="/bootstrap/css/bootstrap.min.css"/>"
        rel="stylesheet">
  <title></title>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<div class="jumbotron">
  <p><a href="<c:url value="/servlet/category"/>"><h2 align="center">
    Categories</h2></a></p>

</div>


<div class="jumbotron">

  <p><a href="<c:url value="/servlet/project"/>"><h2 align="center">
    Projects</h2></a></p>
</div>


</body>
</html>
