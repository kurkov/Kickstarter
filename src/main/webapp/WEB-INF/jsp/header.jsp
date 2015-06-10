<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<link href="<c:url value="/bootstrap/css/bootstrap.min.css"/>" rel="stylesheet">

<nav class="navbar navbar-default">
  <div>
    <div>
      <a class="navbar-brand" href="<c:url value="/"/>"><b>[KICKSTARTER]</b></a>
    </div>
    <div class="navbar-collapse">
      <ul class="nav navbar-nav">
        <li><a href="<c:url value="/servlet/category/"/>"><b>Discover</b></a>
        </li>
      </ul>
      <ul class="nav navbar-right">
        Hello, <security:authentication property="principal.username"/>
        <security:authorize access="isAnonymous">
          <button type="button" class="btn btn-default navbar-btn"
                  formmethod="get" formaction="#">Sign up
          </button>
          <button type="button" class="btn btn-default navbar-btn"
                  formmethod="get" formaction="#">Log in
          </button>
        </security:authorize>
        <security:authorize access="isAuthenticated">
          <button type="button" class="btn btn-default navbar-btn"
                  formmethod="get" formaction="#">Log out
          </button>
        </security:authorize>
        <br>
      </ul>
    </div>
  </div>
</nav>
