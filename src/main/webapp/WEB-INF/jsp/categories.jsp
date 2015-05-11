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
  <div class="panel panel-default">
    <table class="table table-hover">
      <tbody>
      <c:forEach var="c" items="${categories}">
        <tr>
          <form>
            <td onclick="window.location.href='/servlet/category/<c:out
                    value="${c.id}"/>'">
              <a href="/servlet/category/<c:out value="${c.id}"/>">
                <c:out value="${c.name}"/>
              </a>
            </td>

            <td class="text-right text-nowrap">


              <button class="btn btn-xs btn-primary" formmethod="get"
                      formaction='/servlet/category/<c:out
                      value="${c.id}"/>/edit' type="submit">
                <span class="glyphicon glyphicon-pencil"></span>
              </button>


              <button class="btn btn-xs btn-danger" formmethod="post"
                      formaction='/servlet/category/<c:out
                      value="${c.id}"/>/delete' type="submit">
                <span class="glyphicon glyphicon-trash"></span>
              </button>
            </td>

          </form>
        </tr>
      </c:forEach>
      <tr>
        <td class="text-left text-nowrap">
          <button class="btn btn-xs btn-primary"
                  onclick="window.location.href='/servlet/category/add'">
            <span class="glyphicon glyphicon-plus-sign"></span>
            Add new category
          </button>
        </td>
        <td></td>
      </tr>
      </tbody>
    </table>
  </div>
</div>

</body>
</html>
