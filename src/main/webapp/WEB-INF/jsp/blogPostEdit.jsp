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
        <h2>Edit blog post</h2>
      </div>
      <br><br>

      <form action="/servlet/blogpost/edit" method="POST">
        <c:if test="${ErrorMessage != null && ErrorMessage != ''}">
          <div class="alert alert-danger" role="alert">
            <c:out value="${ErrorMessage}"/>
          </div>
        </c:if>

        <input type="text" name="blogPostTitle" class="form-control"
               value="<c:out value="${blogPost.title}"/>"
               placeholder="Blog post title" aria-describedby="basic-addon1"><br><br>

        <textarea name="blogPostText" class="form-control">
          <c:out value="${blogPost.text}"/>
        </textarea><br><br>

        <input class="btn btn-default" type="submit" value="Submit">
        <input type="hidden" name="projectId" value="<c:out value="${project.id}"/>">
      </form>

    </div>
  </div>

</body>
</html>
