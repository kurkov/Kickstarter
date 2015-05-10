<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
  <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
  <title>Add new project:</title>
</head>
<body>

<div class="container">
  <h1>Add new project:</h1>

  <div class="row">
    <form action="/servlet/project/add" method="POST">
      <c:if test="${ErrorMessage != null && ErrorMessage != ''}">
        <div class="alert alert-danger" role="alert"><c:out
                value="${ErrorMessage}"/></div>
      </c:if>
      <input type="text" name="projectName" class="form-control"
             value="<c:out value="${project.name}"/>"
             placeholder="Project name" aria-describedby="basic-addon1">
      <br>

      <select name="categoryId" title="categoryId" class="form-control">
        <option value="">Select category</option>
        <c:forEach var="c" items="${categories}">
          <option value="<c:out value="${c.id}"/>"<c:if
                  test="${c.id == categoryId}"> selected</c:if>><c:out
                  value="${c.name}"/></option>
        </c:forEach>
      </select>
      <br>

      <textarea class="form-control" name="projectDescription" rows="3"> <c:out
              value="${project.description}"/> </textarea>


      <input type="hidden" name="projectId"
             value="<c:out value="${project.id}"/>">
      <input type="hidden" name="categoryId"
             value="<c:out value="${categoryId}"/>">
      <br>
      <input class="btn btn-default" type="submit" value="Submit">
    </form>

    <button class="btn btn-xs btn-primary"
            onclick="window.location.href='/servlet/project/'">
      <span class="glyphicon glyphicon-backward"></span>
      Back
    </button>
  </div>
</div>
</body>
</html>


