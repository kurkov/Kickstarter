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

      <b>Category:</b>
      <c:out value="${categoryItem.name}"/>
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
          <td onclick="window.location.href='/project/<c:out value="${c.id}"/>'">
            <a href="/project/<c:out value="${c.id}"/>">
              <c:out value="${c.name}"/>
            </a>
          </td>

          <td class="text-right text-nowrap">


            <button class="btn btn-xs btn-primary" formmethod="get"
                    formaction='/project/<c:out
                      value="${c.id}"/>/edit' type="submit">
              <span class="glyphicon glyphicon-pencil"></span>
            </button>


            <button class="btn btn-xs btn-danger" formmethod="post"
                    formaction='/project/<c:out
                      value="${c.id}"/>/delete' type="submit">
              <span class="glyphicon glyphicon-trash"></span>
            </button>


          </td>
          <input type="hidden" name="categoryId"
                 value="<c:out value="${categoryItem.id}"/>">
        </form>
        </tr>
      </c:forEach>
      <tr>
        <td class="text-right text-nowrap">


        </td>
        <td class="text-right text-nowrap">
          <button class="btn btn-xs btn-primary"
                  onclick="window.location.href='/project/add?categoryId=<c:out
                    value="${categoryItem.id}"/>'">
            <span class="glyphicon glyphicon-plus-sign"></span>
            Add
          </button>
        </td>

      </tr>
      </tbody>
    </table>
  </div>
  <button class="btn btn-xs btn-primary"
          onclick="window.location.href='/category/'">
    <span class="glyphicon glyphicon-backward"></span>
    Categories
  </button>

</div>
</body>

</html>


