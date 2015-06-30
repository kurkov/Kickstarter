<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
  <script src="<c:url value="/bootstrap/jquery/jquery.min.js"/>"></script>
  <!-- Latest compiled and minified CSS -->
  <link href="<c:url value="/bootstrap/css/bootstrap.min.css"/>"
        rel="stylesheet">
  <!-- Latest compiled and minified JavaScript-->
  <script src="<c:url value="/bootstrap/js/bootstrap.min.js"/>"></script>

  <title></title>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<div class="container" role="navigation">
  <br>

  <div class="jumbotron">
    <h2><c:out value="${project.name}"/></h2>
    <h4><c:out value="${project.description}"/></h4>

    <p><a class="btn btn-primary btn-lg" href="http://google.com"
          target="_blank" role="button">Learn more</a></p>
  </div>

  <br>

  <security:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
    <form>
      <button class="btn btn-primary" formmethod="get"
              formaction="/servlet/project/<c:out value='${project.id}'/>/edit" type="submit">
        <span class="glyphicon glyphicon-pencil"></span>
        Edit project
      </button>
    </form>
  </security:authorize>

  <security:authorize access="hasRole('ROLE_ADMIN')">
    <form>
      <button class="btn btn-danger" formmethod="post"
              formaction="/servlet/project/<c:out value='${project.id}'/>/delete" type="submit">
        <span class="glyphicon glyphicon-trash"></span>
        Delete project
      </button>
      <input type="hidden" name="categoryId" value="<c:out value="${project.category.id}"/>">
    </form>
  </security:authorize>

</div>
</body>
</html>
