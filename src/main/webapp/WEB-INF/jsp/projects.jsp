<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <title></title>
</head>
<body>
<br>

<div class="panel panel-default">
    <div class="panel-heading"><b>Full list of projects</b></div>
    <table class="table table-hover">
        <tr>
            <th>Category</th>
            <th>Project</th>
            <th></th>
            <c:forEach var="c" items="${projects}">
        <tr>
            <form>
                <td onclick="window.location.href='/project/<c:out value="${c.id}"/>'">
                    <a href="/project/<c:out value="${c.id}"/>">
                        <c:out value="${c.category.name}"/>
                    </a>
                </td>

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
            </form>
        </tr>
        </c:forEach>
        </tr>
    </table>
    <!--
    <div class="list-group">
        <c:forEach var="c" items="${projects}">
        <a href="/project/<c:out value="${c.id}"/>"class="list-group-item"><c:out value="${c.name}"/></a>
        </c:forEach>
    </div>
    -->
</div>

</body>
</html>
