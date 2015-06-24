<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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

    <div>

      <b>Category:</b>
      <c:out value="${category.name}"/>
    </div>
    <br><br>
  </div>

  Projects:
  <div class="panel panel-default">
    <table class="table table-hover">
      <tbody>
      <c:forEach var="c" items="${projects}">
        <tr>
          <form>
            <td onclick="window.location.href='/servlet/project/<c:out value="${c.id}"/>'">
              <a href="/servlet/project/<c:out value="${c.id}"/>">
                <c:out value="${c.name}"/>
              </a>
            </td>

            <td class="text-right text-nowrap">
              <security:authorize access="hasRole('USER')">
                <button class="btn btn-xs btn-primary" formmethod="get"
                        formaction='/servlet/project/<c:out value="${c.id}"/>/edit' type="submit">
                  <span class="glyphicon glyphicon-pencil"></span>
                </button>
              </security:authorize>
              <security:authorize access="hasRole('ADMIN')">
                <button class="btn btn-xs btn-danger" formmethod="post"
                        formaction='/servlet/project/<c:out value="${c.id}"/>/delete' type="submit">
                  <span class="glyphicon glyphicon-trash"></span>
                </button>
              </security:authorize>

            </td>
            <input type="hidden" name="categoryId" value="<c:out value="${category.id}"/>">
          </form>
        </tr>
      </c:forEach>
      <tr>
        <td class="text-right text-nowrap"></td>

        <td class="text-right text-nowrap">
          <security:authorize access="hasRole('USER')">
            <button class="btn btn-xs btn-primary"
                    onclick="window.location.href='/servlet/project/add?categoryId=<c:out value="${category.id}"/>'">
              <span class="glyphicon glyphicon-plus-sign"></span>
              Add
            </button>
          </security:authorize>
        </td>

      </tr>
      </tbody>
    </table>
  </div>
  <button class="btn btn-xs btn-primary"
          onclick="window.location.href='/servlet/category/'">
    <span class="glyphicon glyphicon-backward"></span>
    Categories
  </button>

</div>
</body>

</html>


