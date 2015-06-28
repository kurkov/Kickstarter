<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<link href="<c:url value="/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">

<nav class="navbar navbar-default">
  <div>
    <div>
      <a class="navbar-brand" href="<c:url value="/servlet/home"/>"><b>[KICKSTARTER]</b></a>
    </div>
    <div class="navbar-collapse">
      <ul class="nav navbar-nav">
        <li><a href="<c:url value="/servlet/category/"/>"><b>Discover</b></a>
        </li>
      </ul>
      <ul class="nav navbar-right">
        <security:authorize access="!isAuthenticated()">
          <p><a class="btn btn-lg btn-success" href="<c:url value="/servlet/login"/>" role="button">Log in</a></p>
          <button type="submit" class="btn btn-default navbar-btn"
                  formmethod="get" formaction="/servlet/user"> Sign up
          </button>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
          <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout"/>" role="button">Log
            out</a></p>
        </security:authorize>
        <br>
      </ul>
    </div>
  </div>
</nav>
