<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
  <link href="<c:url value="/bootstrap/css/bootstrap.min.css"/>"
        rel="stylesheet">
  <title>Add new project:</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<div class="container">
  <h1>Add new project:</h1>

  <div class="row">
    <form action="/servlet/project/add" method="POST">
      <c:if test="${ErrorMessage != null && ErrorMessage != ''}">
        <div class="alert alert-danger" role="alert"><c:out value="${ErrorMessage}"/></div>
      </c:if>
      <input type="text" name="projectName" class="form-control"
             placeholder="Project name" aria-describedby="basic-addon1">
      <br>

      <c:choose>
        <c:when test="${category.id == null}">
          <select name="categoryId" title="categoryId" class="form-control">
            <option value="">Select category</option>
            <c:forEach var="c" items="${categories}">
              <option value="<c:out value="${c.id}"/>"<c:if
                      test="${c.id == category.id}"> selected</c:if>><c:out
                      value="${c.name}"/></option>
            </c:forEach>
          </select>
        </c:when>
        <c:otherwise>
          <input type="text" name="categoryName" class="form-control" value="<c:out value="${category.name}"/>"
                 placeholder="Category name" aria-describedby="basic-addon1">
        </c:otherwise>
      </c:choose>
      <br>

      <textarea class="form-control" name="projectDescription" rows="3">
      </textarea>

      <input type="hidden" name="categoryId" value="<c:out value="${category.id}"/>">
      <br>
      <input class="btn btn-default" type="submit" value="Submit">
    </form>

    <button class="btn btn-xs btn-primary" onclick="window.location.href='/servlet/category/${category.id}'">
      <span class="glyphicon glyphicon-backward"></span>
      Back
    </button>

  </div>
</div>
</body>
</html>


