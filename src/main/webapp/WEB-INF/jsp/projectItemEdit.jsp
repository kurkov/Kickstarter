<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
  <link rel="stylesheet"
        href="../../bootstrap/css/bootstrap.min.css">
  <title></title>
</head>
<body>

<div class="container">
  <div class="row">

    <div>
      <b>Edit project:</b>
    </div>
    <br>

    <form action="/servlet/project/<c:out value="${project.id}"/>/edit"
          method="POST">
      <c:if test="${ErrorMessage != null &&ErrorMessage != ''}">
        <div class="alert alert-danger" role="alert"><c:out
                value="${ErrorMessage}"/></div>
      </c:if>
      <input type="text" name="projectName" class="form-control"
             value="<c:out value="${project.name}"/>"
             placeholder="Project name" aria-describedby="basic-addon1">

      <textarea class="form-control" name="projectDescription" rows="3"> <c:out
              value="${project.description}"/> </textarea>
      <input type="hidden" name="projectId"
             value="<c:out value="${project.id}"/>">
      <input type="hidden" name="categoryId"
             value="<c:out value="${categoryId}"/>">

      <input class="btn btn-default" type="submit" value="Submit">
    </form>

    <button class="btn btn-xs btn-primary"
            onclick="window.location.href='/servlet/project/<c:out
            value="${categoryId}"/>'">
      <span class="glyphicon glyphicon-backward"></span>
      Back
    </button>
  </div>
</div>
</body>
</html>


