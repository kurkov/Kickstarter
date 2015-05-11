<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<html>
<head>
  <link href="<c:url value="/bootstrap/css/bootstrap.min.css"/>"
        rel="stylesheet">
  <title></title>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<div class="container">
  <div class="row">
    <br>

    <div class="input-group">
      <form action="/servlet/blogpost/add?projectId=<c:out value="${project.id}"/> "
            method="POST">
        <c:if test="${ErrorMessage != null && ErrorMessage != ''}">
          <div class="alert alert-danger" role="alert">
            <c:out value="${ErrorMessage}"/>
          </div>
        </c:if>
        <input type="text" name="blogPostTitle" class="form-control"
               placeholder="Blog post title"
               aria-describedby="basic-addon1"><br><br>
        <input type="text" name="blogPostText" class="form-control"
               placeholder="Blog post text" aria-describedby="basic-addon1"><br><br>
        <input class="btn btn-default" type="submit" value="Submit">
        <input type="hidden" name="projectId"
               value="<c:out value="${project.id}"/>">
      </form>
    </div>
  </div>
</div>
</body>
</html>
