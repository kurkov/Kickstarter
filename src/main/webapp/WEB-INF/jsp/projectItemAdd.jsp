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
  <div class="row">

    <div>
      <b>Add new project:</b>
    </div>
    <br>

    <form action="/project/add" method="POST">
      <c:if test="${ErrorMessage != null &&ErrorMessage != ''}">
        <div class="alert alert-danger" role="alert"><c:out
                value="${ErrorMessage}"/></div>
      </c:if>
      <input type="text" name="projectName" class="form-control"
             value="<c:out value="${projectItem.name}"/>"
             placeholder="Project name" aria-describedby="basic-addon1">
      <br>

      <textarea class="form-control" name="projectDescription" rows="3"> <c:out
              value="${projectItem.description}"/> </textarea>
      <input type="hidden" name="projectId"
             value="<c:out value="${projectItem.id}"/>">
      <input type="hidden" name="categoryId"
             value="<c:out value="${categoryId}"/>">

      <input class="btn btn-default" type="submit" value="Submit">
    </form>

    <button class="btn btn-xs btn-primary"
            onclick="window.location.href='/category/<c:out value="${categoryId}"/>'">
      <span class="glyphicon glyphicon-backward"></span>
      Back
    </button>
  </div>
</div>
</body>
</html>


