<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
  <link href="<c:url value="/bootstrap/css/bootstrap.min.css"/>"
        rel="stylesheet">
  <title></title>
</head>
<body>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<div class="container">
  <h2>Registration</h2>
    <div class="row">
      <form:form method="post" modelAttribute="user" enctype="multipart/form-data">

        <table>
          <tr>
            <td><form:label path = "name">Name for login: </form:label></td>
            <td><form:input path="name"/><br>
              <form:errors path="name" cssClass="error"/>
            </td>
          </tr><br>
          <tr>
            <td><form:label path="password">Password: </form:label></td>
            <td><form:input path="password"/>
              <form:errors path="password" cssClass="error"/>
            </td>
          </tr><br>
          <tr>
            <td><form:label path="email">Email: </form:label></td>
            <td><form:input path="email"/>
              <form:errors path="email" cssClass="error"/>
            </td>
          </tr><br>
          <tr>
            <td><input name="commit" type="submit" value="Create account"></td>
          </tr>
        </table>

      </form:form>
    </div>
</div>
</body>
</html>